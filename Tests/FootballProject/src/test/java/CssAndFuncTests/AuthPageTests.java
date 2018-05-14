package CssAndFuncTests;

import helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.basetest.BaseTestCookies;
import utilities.browser.Browser;
import utilities.verifications.Verification;

public class AuthPageTests extends BaseTestCookies {
    @Test(description = "Verify functionality SignIn button")
    public void tVerifyFuncSignIn() {
        Browser.openUrl(BaseUrl + AuthUrl);
        WaitHelper.sleep(10);
        WebElement signInButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
        WebElement modalWindow = driver.findElement(By.xpath("//*[@id='myModal']"));
        WebElement closeTopBtn = driver.findElement(By.xpath("//button[@class=' btn close']"));
        WebElement closeBottomBtn = driver.findElement(By.xpath("//button[@class='btn btn-default']"));

        Verification.softVerifyValue("SignIn should be displayed", signInButton.isDisplayed(), sAssert);
        Verification.softVerifyValue("Modal window should not be displayed", !modalWindow.isDisplayed(), sAssert);

        signInButton.click();
        WaitHelper.sleep(3);
        Verification.softVerifyValue("Modal window should be displayed", modalWindow.isDisplayed(), sAssert);

        closeBottomBtn.click();
        WaitHelper.sleep(2);
        Verification.softVerifyValue("Modal window should not be displayed", !modalWindow.isDisplayed(), sAssert);

        signInButton.click();
        WaitHelper.sleep(3);
        Verification.softVerifyValue("Modal window should be displayed", modalWindow.isDisplayed(), sAssert);

        closeTopBtn.click();
        WaitHelper.sleep(2);
        Verification.softVerifyValue("Modal window should not be displayed", !modalWindow.isDisplayed(), sAssert);
    }
}