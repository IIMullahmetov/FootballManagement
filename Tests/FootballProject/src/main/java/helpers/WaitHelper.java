package helpers;

import utilities.browser.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.*;

public class WaitHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(WaitHelper.class);

    /**
     * Method to wait for Expected Page source text to be displayed.
     */
    public static void waitForPageSourceText(final String sExpText, int timeOutInSeconds) {
        new WebDriverWait(Browser.getWebDriver(), timeOutInSeconds)
            .until((ExpectedCondition<Boolean>) driver -> driver != null && driver.getPageSource().contains(sExpText));
    }

    public static boolean waitForPageURL(String sURLTxt, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), timeOutInSeconds);
            wait.until(ExpectedConditions.urlContains(sURLTxt));
            LOGGER.debug("Page URL contains {}", sURLTxt);
            return true;
        } catch (Exception e) {
            LOGGER.debug("Page URL {} Not displayed", sURLTxt);
            LOGGER.debug("Page URL is {}", Browser.getWebDriver().getCurrentUrl());
            return false;
        }
    }

    /**
     * Method to wait for Page title
     */
    public static boolean waitForPageTitle(String sTitle) {
        return waitForPageTitle(sTitle, 30);
    }

    /**
     * Method to wait for Page title
     */
    public static boolean waitForPageTitle(String sTitle, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), timeOutInSeconds);
            wait.until(ExpectedConditions.titleContains(sTitle));
            LOGGER.debug("Page with Title {} is displayed", sTitle);
            return true;
        } catch (Exception e) {
            LOGGER.debug("Page with Title {} Not displayed", sTitle);
            LOGGER.debug("Page had Title {}", Browser.getWebDriver().getTitle());
            return false;
        }
    }

    public static void waitForNumberOfWindowsToEqual(final int numberOfWindows, int timeOutInSeconds) {
        new WebDriverWait(Browser.getWebDriver(), timeOutInSeconds).until((ExpectedCondition<Boolean>) driver ->
            (driver != null ? driver.getWindowHandles().size() : 0) == numberOfWindows);
    }

    public static void waitForPageLoading() {
        final WebDriver wd = Browser.getWebDriver();
        WebDriverWait wait = new WebDriverWait(wd, 20);
        try {
            wait.until(
                (ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) Browser.getWebDriver()).executeScript(
                    "return document.readyState").toString().equals("complete"));
            if ( Browser.getTitle().contains("Page not found error")) {
                throw new Exception("404 exception. Page not found error");
            }
        } catch (Exception ex) {
            System.err.print(ex.getLocalizedMessage());
            ex.printStackTrace();

        }

    }

    public void waitForAJAXCallsHaveCompleted() {
        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), 10);
        try {
            wait.until(
                (ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) Browser.getWebDriver()).executeScript(
                    "return jQuery.active == 0").equals(true));
        } catch (Exception ex) {
            System.err.print(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception ignored) {

        }
    }

    private boolean CheckFor404() {
        return Browser.getTitle().contains("Page not found error");
    }
}
