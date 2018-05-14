package utilities.browser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import helpers.WaitHelper;
import io.netty.handler.codec.http.HttpRequest;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;

public abstract class Browser {
    private static final Logger LOGGER = LoggerFactory.getLogger(Browser.class);
    private static final DateFormat SCREENSHOT_FILE_FORMAT = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss.SSS");
    public static final int DEFAULT_WAIT_TIMEOUT = 10; // WebDriverWait default timeout in seconds
    private static File screenshotDir;
    private static URL hubUrl;

    private static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalName = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalDevice = new ThreadLocal<>();
    private static ThreadLocal<Boolean> threadLocalIsMobile = new ThreadLocal<>();
    private static BrowserMobProxyServer BrowserMobServer = new BrowserMobProxyServer(); // BrowserMobProxy staff
    private static InetAddress BrowserMobHost; // BrowserMobProxy staff


    static {
        System.setProperty("webdriver.chrome.driver", "/Users/ilvirdimuhametov/IdeaProjects/chromedriver");
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        threadLocalWebDriver.set(driver);
        threadLocalName.set("UNDEFINED BROWSER");
        threadLocalDevice.set("");
        threadLocalIsMobile.set(false);
    }

    public static void start() throws Exception {
        start(System.getProperty("BROWSER", "UNDEFINED BROWSER"));
    }

    public static void start(String name) throws Exception {
        start(name, "");
    }

    public static void startProxy() throws Exception {
        BrowserMobServer = new BrowserMobProxyServer();
        BrowserMobHost = InetAddress.getLocalHost();
        BrowserMobServer.addRequestFilter(new RequestFilter() {
            @Override
            public io.netty.handler.codec.http.HttpResponse filterRequest(HttpRequest httpRequest, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
                httpRequest.headers().add("Cookie", "hideModal=true");
                httpRequest.headers().add("Cookie", "crDonationAlertbannerClosed=true");
                httpRequest.headers().add("Cookie", "bbGivingDay=closed");
                httpRequest.headers().add("Cookie", "croModalCreativeClosed=true");
                httpRequest.headers().add("Cookie", "fsrsshown=1");
                httpRequest.headers().add("Cookie", "surveyed=true");
                httpRequest.headers().add("Cookie", "suppressForeSeeModal=true");
                return null;
            }

        });
        if (!BrowserMobServer.isStarted()) {
            LOGGER.info("Proxy start server");
            BrowserMobServer.setTrustAllServers(true);
            BrowserMobServer.setConnectTimeout(5, TimeUnit.MINUTES);
            BrowserMobServer.setRequestTimeout(5, TimeUnit.MINUTES);
            BrowserMobServer.blacklistRequests("(.)*(facebook)(.)*", 200);
            BrowserMobServer.blacklistRequests("(.)*(images)(.)*", 200);
            BrowserMobServer.blacklistRequests("(.)*(f1.media.brightcove.com)(.)*", 200);
            BrowserMobServer.blacklistRequests("(.)*(cdn5.userzoom)(.)*", 200);
            BrowserMobServer.start(0, BrowserMobHost);

        }

        BrowserMobServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT);
        BrowserMobServer.newHar("");
        LOGGER.info("Proxy successfully started");
    }

    public static void restartProxy() throws Exception {
        BrowserMobServer.start(0, BrowserMobHost);
        LOGGER.info("Proxy successfully restarted");
    }


    public static void start(String name, String device) throws Exception {
        Browser.threadLocalName.set(name);
        Browser.threadLocalDevice.set(device);
        threadLocalIsMobile.set(!device.isEmpty());
       java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("").setUseParentHandlers(false);
        LogManager.getLogManager().reset();
        String sHubUrl = System.getProperty("HUB_URL", "");
        if (!sHubUrl.isEmpty()) {
            hubUrl = new URL(sHubUrl);
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, String> bsEnvCapabilities;
        Iterator it;

        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/main/resources/browserstack/config.json"));
        JSONObject environments = (JSONObject) config.get("environments");

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("key");
        }

        String bsUrl = String.format("http://%s:%s@%s/wd/hub", username, accessKey, config.get("server"));

        if (name.toUpperCase().startsWith("BS_")) {
            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                }
            }
        }

        LOGGER.info("Open browser {}{}", name, device.isEmpty() ? "" : " (" + device + ")");

        capabilities = device.isEmpty() ? Capability.chrome() : Capability.chrome(device);
        threadLocalWebDriver.set((null == hubUrl) ? new ChromeDriver(capabilities)
            : new RemoteWebDriver(hubUrl, capabilities));
        if (!"FF".equalsIgnoreCase(name)) {
            threadLocalWebDriver.get().manage().window().maximize();
        }
        threadLocalWebDriver.get().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (!"IE".equalsIgnoreCase(name)) {
            threadLocalWebDriver.get().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }
        threadLocalWebDriver.get().manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

        // workaround for an issue in chromedriver 2.32 when "element is not clickable at point..."
        // it scrolls to an element before to click in device emulator mode
        if (isMobile()) {
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(threadLocalWebDriver.get());
            eventDriver.register(new WebDriverListener());
            threadLocalWebDriver.set(eventDriver);
        }
    }

    public static WebDriver getWebDriver() {
        return threadLocalWebDriver.get();
    }

    public static String getName() {
        return threadLocalName.get();
    }

    public static String getDevice() {
        return threadLocalDevice.get();
    }

    public static boolean isMobile() {
        return threadLocalIsMobile.get();
    }

    public static void openUrl(String url) {
        LOGGER.info("Open URL: {}", url);

        threadLocalWebDriver.get().get(url);

    }

    public synchronized static void openUrlProxy(String url) {
        LOGGER.info("Open URL: {}", url);
        try {
            threadLocalWebDriver.get().get(url);
            WaitHelper.waitForPageLoading();
        } catch (TimeoutException e) {
            LOGGER.info("Trying to refresh page");
            threadLocalWebDriver.get().navigate().refresh();
            WaitHelper.sleep(1);
            threadLocalWebDriver.get().navigate().refresh();
        }
    }


    public static void scrollTo(WebElement element) {
        if (element.isDisplayed()) {
            executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    public static void resize(Dimension size) {
        threadLocalWebDriver.get().manage().window().setSize(size);
    }

    public static Object executeScript(String js, Object... args) {
        JavascriptExecutor executor = (JavascriptExecutor) threadLocalWebDriver.get();
        try {
            return executor.executeScript(js, args);
        } catch (Exception e) {
            LOGGER.warn("Couldn't execute script:\n{}", e.getMessage());
            return null;
        }
    }

    public static void quit() {
        if (null == threadLocalWebDriver.get()) {
            return;
        }
        try {
            LOGGER.info("Close browser {}{}", threadLocalName.get(),
                !isMobile() ? "" : " (" + threadLocalDevice.get() + ")");
            threadLocalWebDriver.get().quit();
            threadLocalWebDriver.set(null);
        } catch (Exception e) {
            LOGGER.error("Couldn't close browser:\n{}", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setElementValueByJS(WebElement webElement, String value) {
        String js = String.format("arguments[0].value='%s'", value);
        executeScript(js, webElement);
    }

    public static void refreshPage() {
        String url = threadLocalWebDriver.get().getCurrentUrl();
        LOGGER.info("Refresh page: {}", url);
        threadLocalWebDriver.get().navigate().refresh();
    }

    public static void takeScreenshot() {
        LOGGER.debug("Take screenshot");
        Date date = Calendar.getInstance().getTime();
        String timestamp = SCREENSHOT_FILE_FORMAT.format(date);
        try {
            File tmpFile = ((TakesScreenshot) threadLocalWebDriver.get()).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(screenshotDir + File.separator + timestamp + ".png");
            LOGGER.debug("Screenshot path: {}", screenshotFile.getAbsolutePath());
            FileUtils.copyFile(tmpFile, screenshotFile);
            String screenshotFileName = screenshotFile.getName();
            LOGGER.info("<a href='./screenshots/{}'><img src='./screenshots/{}' height='100%' width='100%'/></a>",
                screenshotFileName, screenshotFileName);
        } catch (Exception e) {
            LOGGER.warn("Unable to capture screenshot: \n" + e.getMessage());
        }
    }

    public static void takeFullScreenshot() {
        LOGGER.debug("Take full screenshot");
        java.util.logging.Logger.getLogger("org.openqa.selenium.remote.Augmenter").setLevel(Level.OFF);
        Date date = Calendar.getInstance().getTime();
        String timestamp = SCREENSHOT_FILE_FORMAT.format(date);
        try {
            File file = new File(screenshotDir + File.separator + timestamp + ".png");
            BufferedImage image = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(threadLocalWebDriver.get()).getImage();
            ImageIO.write(image, "png", file);
            String fileName = file.getName();
            LOGGER.info("<a href='./screenshots/{}'><img src='./screenshots/{}' height='100%' width='100%'/></a>",
                fileName, fileName);
        } catch (Exception e) {
            LOGGER.warn("Unable to capture full screenshot: \n" + e.getMessage());
        }
    }

    public static void setScreenshotDir(File screenshotDir) {
        Browser.screenshotDir = screenshotDir;
    }

    public static File getScreenshotDir() {
        return screenshotDir;
    }

    public static String getNodeIpAddress() {
        if (null == hubUrl) {
            return null;
        }
        String hostName = hubUrl.getHost();
        int port = hubUrl.getPort();

        try {
            HttpHost host = new HttpHost(hostName, port);
            HttpClient client = HttpClients.createDefault();
            SessionId sessionId = ((RemoteWebDriver) threadLocalWebDriver.get()).getSessionId();
            String sUrl = String.format("http://%s:%s/grid/api/testsession?session=%s", hostName, port, sessionId);
            URL sessionUrl = new URL(sUrl);
            LOGGER.debug("Session URL: {}", sessionUrl);
            BasicHttpEntityEnclosingRequest request =
                new BasicHttpEntityEnclosingRequest("POST", sessionUrl.toExternalForm());
            HttpResponse response = client.execute(host, request);
            JsonObject jsonResponse = extractObject(response);
            JsonElement proxyId = jsonResponse.get("proxyId");
            URL nodeURL = new URL(proxyId.getAsString());
            return nodeURL.getHost();
        } catch (Exception e) {
            LOGGER.debug("Couldn't get Selenium node IP address:\n{}", e.getMessage());
        }
        return null;
    }

    private static JsonObject extractObject(HttpResponse resp) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            stringBuilder.append(line);
        }
        rd.close();
        JsonParser parser = new JsonParser();
        JsonObject objToReturn = (JsonObject) parser.parse(stringBuilder.toString());
        System.out.println(objToReturn.toString());
        System.out.println(objToReturn.get("proxyId"));
        return objToReturn;
    }

    public static void switchToNewWindow() {
        String parentWindowHandle = getWebDriver().getWindowHandle();
        Set<String> windowHandles = getWebDriver().getWindowHandles();
        windowHandles.stream().filter(handle -> !parentWindowHandle.equals(handle)).forEachOrdered(handle -> {
            getWebDriver().switchTo().window(handle);
            LOGGER.info("Switched to new browser tab [{}]", getWebDriver().getCurrentUrl());
        });
    }

    public static String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }

    public static String getPageSource() {
        return getWebDriver().getPageSource();
    }

    public static void back() {
        LOGGER.info("Navigate back");
        getWebDriver().navigate().back();
    }

    public static void forward() {
        LOGGER.info("Navigate forward");
        getWebDriver().navigate().forward();
    }

    public static void refresh() {
        LOGGER.info("Refresh page");
        getWebDriver().navigate().refresh();
    }

    public static BrowserMobProxyServer getServer() {
        return BrowserMobServer;
    }

    public static String getTitle() {
        return getWebDriver().getTitle();
    }

    public static void switchToDefaultContent() {
        getWebDriver().switchTo().defaultContent();
    }

    public static WebDriverWait waiting() throws TimeoutException {
        return waiting(DEFAULT_WAIT_TIMEOUT);
    }

    public static WebDriverWait waiting(int seconds) throws TimeoutException {
        return new WebDriverWait(getWebDriver(), seconds);
    }

    public static Actions actions() {
        return new Actions(getWebDriver());
    }
}
