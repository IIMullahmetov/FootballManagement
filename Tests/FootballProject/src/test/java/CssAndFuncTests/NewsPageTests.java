package CssAndFuncTests;

import helpers.Obj.CssPropertiesObj;
import helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.basetest.BaseTestCookies;
import utilities.browser.Browser;
import utilities.logger.Report;
import utilities.verifications.Verification;

import java.util.List;


public class NewsPageTests extends BaseTestCookies {
    @Test(description = "Verify nav-bar visibility")
    public void tVerifyNavBarVisibility() {
        Browser.openUrl(BaseUrl + NewsUrl);
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

    @Test(description = "Verify visibilities and styles of news post")
    public void tVerifyStylesOfNewsPost() {
        Browser.openUrl(BaseUrl + NewsUrl);
        WaitHelper.sleep(2);

        WebElement headline = driver.findElement(By.xpath("//h2[@class='news_name']"));
        WebElement desc = driver.findElement(By.xpath("//p[@class='news_body']"));
        WebElement date = driver.findElement(By.xpath("//h5[@class='news_time']"));

        Report.logHeading("Verify styles of headline");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("30px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("33px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        Report.logHeading("Verify styles of description");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20x");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(desc);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of description");

        Report.logHeading("Verify styles of date");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("15.4px");
        cssExpectedStyle.setFontWeight("500");

        cssInFact = new CssPropertiesObj(date);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of date");
    }

    @Test(description = "Verify visibilities and styles news of block of comments")
    public void tVerifyStylesOfNewsBlockOfComments() {
        Browser.openUrl(BaseUrl + NewsUrl);
        WaitHelper.sleep(2);

        WebElement wrapComments = driver.findElement(By.xpath("//div[@class='col-md-12 comments_block']"));
        WebElement amountOfComments = driver.findElement(By.xpath("//div[@class='col-md-12 comments_block']/h5"));
        WebElement textarea = driver.findElement(By.xpath("//div[@class='col-md-12 comments_block']//textarea"));
        WebElement headline = driver.findElement(By.xpath("//h4[@class='comments_h4']"));

        Verification.softVerifyValue("Headline should be 'Комментарии'", headline.getText().equals("Комментарии"), sAssert);
        Verification.softVerifyValue("Comments wrapper should be displayed", wrapComments.isDisplayed(), sAssert);
        Verification.softVerifyValue("Textarea placeholder should be 'Что думаете о происходящем?'",
                textarea.getAttribute("placeholder").equals("Что думаете о происходящем?"), sAssert);

        Report.logHeading("Verify styles of headline");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("19.8px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        Report.logHeading("Verify styles of amount of comments");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("15.4px");
        cssExpectedStyle.setFontWeight("700");

        cssInFact = new CssPropertiesObj(amountOfComments);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of amount of comments");

        CssPropertiesObj cssExpectedStyleTextarea = new CssPropertiesObj();
        cssExpectedStyleTextarea.setLineHeight("20px");
        cssExpectedStyleTextarea.setFontSize("14px");

        cssInFact = new CssPropertiesObj(textarea);
        cssInFact.compareWithAsert(cssExpectedStyleTextarea, "Verify style of Textarea");
    }


    @Test(description = "Verify visibilities and styles of send button news page")
    public void tVerifyStylesOfSendButtonNewsPage() {
        Browser.openUrl(BaseUrl + NewsUrl);
        WaitHelper.sleep(2);

        WebElement send = driver.findElement(By.xpath("//button[@class='btn btn-lg send_cmnt_btn']"));

        Verification.softVerifyValue("Send button should be displayed", send.isDisplayed(), sAssert);
        Verification.softVerifyValue("Send button should be 'Отправить'", send.getText().contains("Отправить"), sAssert);

        Report.logHeading("Verify styles of button inactive state");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("23.94px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(send);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of inactive state button");

        mouseMove(send);
        WaitHelper.sleep(3);
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");

        cssInFact = new CssPropertiesObj(send);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of active state button");
    }

    @Test(description = "Verify size of list of comments and amount of comments news page")
    public void tVerifySizeAndAmountOfCommentsNewsPage() {
        Browser.openUrl(BaseUrl + NewsUrl);
        WaitHelper.sleep(2);

        WebElement amountOfComments = driver.findElement(By.xpath("//div[@class='col-md-12 comments_block']/h5"));
        List<WebElement> listOfComments = driver.findElements(By.xpath("//ul[@class='comments_list list-unstyled']//li"));

        int amount = Integer.parseInt(amountOfComments.getText().replaceAll("\\D+", ""));
        Report.logHeading(String.valueOf(amount));
        Verification.softVerifyValue("Size of comments == amount of comments on page",
                amount == listOfComments.size(), sAssert);
    }

    @Test(description = "Verify visibilities and styles of blogs at news page")
    public void tVerifyStylesOfBlogsAtNewsPage() {
        Browser.openUrl(BaseUrl + NewsUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-3 blogs']"));
        WebElement headline = driver.findElement(By.xpath("//div[@class='col-md-3 blogs']//h2"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='col-md-3 blogs']//a"));

        Verification.softVerifyValue("Blogs wrap should be displayed", wrap.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of headline");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("30px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("33px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        Report.logHeading("Verify styles of buttons");
        for (WebElement button : buttons) {
            cssExpectedStyle.setBackgroundColor("rgba(196, 196, 196, 1)");
            cssExpectedStyle.setColor("rgba(32, 33, 37, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("700");

            cssInFact = new CssPropertiesObj(button);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of button");
        }

    }

    @Test(description = "Verify visibilities and styles of main news")
    public void tVerifyStylesOfMainNews() {
        Browser.openUrl(BaseUrl + NewsUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-3 news_div']"));
        WebElement headline = driver.findElement(By.xpath("//div[@class='col-md-3 news_div']//h3"));
        List<WebElement> links = driver.findElements(By.xpath("//div[@class='col-md-3 news_div']//p"));

        Verification.softVerifyValue("Main news wrap should be displayed", wrap.isDisplayed(), sAssert);
        Verification.softVerifyValue("Amount of links should be 12", links.size() == 12, sAssert);

        Report.logHeading("Verify styles of headline main news");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setFontSize("24px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("26.4px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        Report.logHeading("Verify styles of links");
        for (WebElement link : links) {
            cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("12px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("17.1429px");
            cssExpectedStyle.setFontWeight("700");

            cssInFact = new CssPropertiesObj(link);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of link");
        }
    }


}
