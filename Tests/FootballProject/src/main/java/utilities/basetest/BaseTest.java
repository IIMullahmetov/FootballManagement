package utilities.basetest;

import helpers.WaitHelper;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.browser.Browser;
import utilities.logger.Report;

import java.util.Collections;
import java.util.List;

public class BaseTest extends Assert {

    private static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    protected SoftAssert sAssert;


    @BeforeMethod
    public void beforeMethod() throws Exception {
        java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("").setUseParentHandlers(false);
        java.util.logging.LogManager.getLogManager().reset();

        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for (Logger logger : loggers) {
            logger.setLevel(Level.OFF);
        }
        sAssert = new SoftAssert();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) throws Exception {
        java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("").setUseParentHandlers(false);
        java.util.logging.LogManager.getLogManager().reset();

        Browser.takeScreenshot();
        Browser.quit();
    }

    public WebDriver getWebDriver() {
        return threadLocalWebDriver.get();
    }

    public void jMouseOver(WebElement element){
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";

        ((JavascriptExecutor)Browser.getWebDriver()).executeScript(javaScript, element);
        WaitHelper.sleep(5);
    }

    public void mouseMove(WebElement element) {
        Actions builder = new Actions(Browser.getWebDriver());
        builder.moveToElement(element).build().perform();
    }

}
