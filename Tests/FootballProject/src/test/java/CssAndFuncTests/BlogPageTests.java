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


public class BlogPageTests extends BaseTestCookies {
    @Test(description = "Verify nav-bar visibility")
    public void tVerifyNavBarVisibility() {
        Browser.openUrl(BaseUrl + BlogUrl);
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

    @Test(description = "Verify visibilities and styles of information in blog page")
    public void tVerifyStylesOfPInformationInBlogPage() {
        Browser.openUrl(BaseUrl + BlogUrl);
        WaitHelper.sleep(2);

        WebElement blogInfoWrap = driver.findElement(By.xpath("//div[@class='col-md-11  blog_info']"));
        WebElement date = driver.findElement(By.xpath("//div[@class='col-md-11  blog_info']/p"));
        WebElement headline = driver.findElement(By.xpath("//div[@class='col-md-11  blog_info']/h3"));

        Verification.softVerifyValue("Blog info wrap should be displayed", blogInfoWrap.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of date");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(196, 196, 196, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(date);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of date");

        Report.logHeading("Verify styles of headline");

        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("24px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("26.4px");
        cssExpectedStyle.setFontWeight("700");

        cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");


    }


    @Test(description = "Verify visibilities and styles of subscribe button")
    public void tVerifyStylesOfSubscribeButton() {
        Browser.openUrl(BaseUrl + BlogUrl);
        WaitHelper.sleep(2);

        WebElement subscribe = driver.findElement(By.xpath("//div[@class='col-md-11  blog_info']/button"));

        Verification.softVerifyValue("Subscribe button should be displayed", subscribe.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of button inactive state");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 1)");
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("23.94px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(subscribe);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of inactive state button");


        mouseMove(subscribe);
        WaitHelper.sleep(3);
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");

        cssInFact = new CssPropertiesObj(subscribe);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of active state button");
    }

    @Test(description = "Verify visibilities and styles of create blog button")
    public void tVerifyStylesOfCreateBlogButton() {
        Browser.openUrl(BaseUrl + BlogUrl);
        WaitHelper.sleep(2);

        WebElement createBlog = driver.findElement(By.xpath("//button[@class='btn btn-lg btn-block']"));

        Verification.softVerifyValue("Create Blog button should be displayed", createBlog.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of button inactive state");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("23.94px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(createBlog);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of inactive state button");


        mouseMove(createBlog);
        WaitHelper.sleep(3);
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");

        cssInFact = new CssPropertiesObj(createBlog);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of active state button");
    }

    @Test(description = "Verify visibilities and styles of send button")
    public void tVerifyStylesOfSendButton() {
        Browser.openUrl(BaseUrl + BlogUrl);
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

    @Test(description = "Verify visibilities and styles of recently in blogs block")
    public void tVerifyStylesOfRecentlyInBlogs() {
        Browser.openUrl(BaseUrl + BlogUrl);
        WaitHelper.sleep(2);

        WebElement headline = driver.findElement(By.xpath("//div[@class='col-md-3 blogs']//h3"));
        List<WebElement> links = driver.findElements(By.xpath("//ul[@class='blogs_list list-unstyled']//a"));
        List<WebElement> dates = driver.findElements(By.xpath("//ul[@class='blogs_list list-unstyled']//p"));

        Verification.softVerifyValue("Headline should be 'Свежее в блогах'", headline.getText().contains("Свежее в блогах"), sAssert);

        Report.logHeading("Verify styles of headline");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("24px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("26.4px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        Report.logHeading("Verify styles of links");
        for (WebElement link : links) {
            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
            cssExpectedStyle.setColor("rgba(66, 139, 202, 1)");
            cssExpectedStyle.setFontSize("16px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("22.8571px");
            cssExpectedStyle.setFontWeight("400");

            cssInFact = new CssPropertiesObj(link);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of link");
        }

        Report.logHeading("Verify styles of dates");
        for (WebElement date : dates) {
            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 0)");
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");

            cssInFact = new CssPropertiesObj(date);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of date");
        }
    }

    @Test(description = "Verify visibilities and styles of post")
    public void tVerifyStylesOfPost() {
        Browser.openUrl(BaseUrl + BlogUrl);
        WaitHelper.sleep(2);

        WebElement headline = driver.findElement(By.xpath("//h2[@class='post_name']"));
        WebElement desc = driver.findElement(By.xpath("//p[@class='post_body']"));

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
    }

    @Test(description = "Verify visibilities and styles of block of comments")
    public void tVerifyStylesOfBlockOfComments() {
        Browser.openUrl(BaseUrl + BlogUrl);
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

    @Test(description = "Verify size of list of comments and amount of comments")
    public void tVerifySizeAndAmountOfComments() {
        Browser.openUrl(BaseUrl + BlogUrl);
        WaitHelper.sleep(2);

        WebElement amountOfComments = driver.findElement(By.xpath("//div[@class='col-md-12 comments_block']/h5"));
        List<WebElement> listOfComments = driver.findElements(By.xpath("//ul[@class='comments_list list-unstyled']//li"));

        int amount = Integer.parseInt(amountOfComments.getText().replaceAll("\\D+", ""));
        Report.logHeading(String.valueOf(amount));
        Verification.softVerifyValue("Size of comments == amount of comments on page",
                amount == listOfComments.size(), sAssert);
    }
}
