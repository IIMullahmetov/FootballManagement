package CssAndFuncTests;

import helpers.Obj.CssPropertiesObj;
import helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.basetest.BaseTestCookies;
import utilities.browser.Browser;
import utilities.logger.Report;
import utilities.verifications.Verification;

import java.util.List;


public class ProfilePageTests extends BaseTestCookies {

    @Test(description = "Verify nav-bar visibility")
    public void tVerifyNavBarVisibility() {
        Browser.openUrl(BaseUrl + ProfileUrl);
        WaitHelper.sleep(2);
        WebElement navbar = driver.findElement(By.xpath("//header"));
        WebElement logo = driver.findElement(By.xpath("//div[@class='col-md-2 logo_div']/h1"));
        WebElement signIn = driver.findElement(By.xpath("/html/body/header/div/div/div[2]/button"));
        WebElement signUp = driver.findElement(By.xpath("/html/body/header/div/div/div[3]/button"));

        Verification.softVerifyValue("Nav-bar should be displayed", navbar.isDisplayed(), sAssert);
        Verification.softVerifyValue("Logo should be displayed", logo.isDisplayed(), sAssert);
        Verification.softVerifyValue("Sign In should be displayed", signIn.isDisplayed(), sAssert);
        Verification.softVerifyValue("Sign In should be 'Войти'", signIn.getText().contains("Войти"), sAssert);
        Verification.softVerifyValue("Sign Up should be 'Зарегистрироваться'", signUp.getText().contains("Зарегистрироваться"), sAssert);

    }

    @Test(description = "Verify visibilities and styles of information of user")
    public void tVerifyStylesOfInformationOfUser() {
        Browser.openUrl(BaseUrl + ProfileUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-10 profile-info col-md-offset-1 panel panel-default']"));
        List<WebElement> headlines = driver.findElements(By.xpath("//div[@class='col-md-10 profile-info col-md-offset-1 panel panel-default']//span"));
        List<WebElement> descriptions = driver.findElements(By.xpath("//div[@class='col-md-10 profile-info col-md-offset-1 panel panel-default']//p"));

        Report.logHeading("Verify styles of wrap");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(253, 253, 253, 1)");
        cssExpectedStyle.setBorderColor("rgb(221, 221, 221)");

        CssPropertiesObj cssInFact = new CssPropertiesObj(wrap);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of wrap");

        Report.logHeading("Verify styles of headlines");
        for (WebElement head : headlines) {
            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("18px");
            cssExpectedStyle.setFontFamily("fantasy");
            cssExpectedStyle.setLineHeight("25.7143px");
            cssExpectedStyle.setFontWeight("400");

            cssInFact = new CssPropertiesObj(head);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");
        }

        Report.logHeading("Verify styles of descriptions");
        for (WebElement desc : descriptions) {
            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("18px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("25.7143px");
            cssExpectedStyle.setFontWeight("400");

            cssInFact = new CssPropertiesObj(desc);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of description");
        }
    }


    @Test(description = "Verify visibilities and styles of edit button")
    public void tVerifyStylesOfEditButton() {
        Browser.openUrl(BaseUrl + ProfileUrl);
        WaitHelper.sleep(2);

        WebElement edit = driver.findElement(By.xpath("//button[@class='btn btn-lg']"));

        Verification.softVerifyValue("Edit button should be displayed", edit.isDisplayed(), sAssert);
        Verification.softVerifyValue("Edit button should be 'Редактировать'", edit.getText().contains("Редактировать"), sAssert);

        Report.logHeading("Verify styles of button inactive state");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("23.94px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(edit);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of inactive state button");

        mouseMove(edit);
        WaitHelper.sleep(3);
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");

        cssInFact = new CssPropertiesObj(edit);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of active state button");
    }

    @Test(description = "Verify image in profile page")
    public void tVerifyImageInProfilePage() {
        Browser.openUrl(BaseUrl + ProfileUrl);
        WaitHelper.sleep(2);

        WebElement image = driver.findElement(By.xpath("//img[@class='profile_photo']"));

        Dimension expectedSize = new Dimension(150, 145);

        Verification.softVerifyValue("Image src shouldn't be empty", !image.getAttribute("src").isEmpty(), sAssert);
        Verification.softVerifyValue("Image size should be ", image.getSize().height == expectedSize.height
                && image.getSize().width == expectedSize.width, sAssert);
    }

    @Test(description = "Verify visibilities and styles of name and username")
    public void tVerifyStylesOfNameAndUsername() {
        Browser.openUrl(BaseUrl + ProfileUrl);
        WaitHelper.sleep(2);

        WebElement name = driver.findElement(By.xpath("//div[@class='col-md-7 head-col-2']//h2"));
        WebElement username = driver.findElement(By.xpath("//div[@class='col-md-7 head-col-2']//p"));

        Verification.softVerifyValue("Name should be displayed", name.isDisplayed(), sAssert);
        Verification.softVerifyValue("Username should be displayed", username.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of Name");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("30px");
        cssExpectedStyle.setFontFamily("fantasy");
        cssExpectedStyle.setLineHeight("33px");
        cssExpectedStyle.setFontWeight("500");

        CssPropertiesObj cssInFact = new CssPropertiesObj(name);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of name");

        Report.logHeading("Verify styles of Username");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("25.7143px");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(username);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of username");
    }

    @Test(description = "Verify visibilities and styles of activity user")
    public void tVerifyStylesOfActivityUser() {
        Browser.openUrl(BaseUrl + ProfileUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-3 head-col-3']"));
        List<WebElement> headlines = driver.findElements(By.xpath("//div[@class='col-md-3 head-col-3']//p"));
        List<WebElement> amounts = driver.findElements(By.xpath("//div[@class='col-md-3 head-col-3']//span[@class='quantity']"));

        Verification.softVerifyValue("Activity Wrap should be displayed", wrap.isDisplayed(), sAssert);

        boolean correct = false;
        Report.logHeading("Verify styles of headlines");
        for (WebElement headline : headlines) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("18px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("25.7143px");
            cssExpectedStyle.setFontWeight("400");

            CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

            correct = headline.getText().toLowerCase().contains("комментарии") ||
                    headline.getText().toLowerCase().contains("записи в блоге");
        }
        Verification.softVerifyValue("'Комментарии' 'Записи в блоге' should be displayed", correct &&
                headlines.size() == 2, sAssert);

        Report.logHeading("Verify styles of amounts");
        for (WebElement amount : amounts) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("18px");
            cssExpectedStyle.setFontFamily("fantasy");
            cssExpectedStyle.setLineHeight("25.7143px");
            cssExpectedStyle.setFontWeight("400");

            CssPropertiesObj cssInFact = new CssPropertiesObj(amount);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of amount");
        }
    }

}
