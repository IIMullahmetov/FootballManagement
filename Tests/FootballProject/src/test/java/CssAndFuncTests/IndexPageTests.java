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


public class IndexPageTests extends BaseTestCookies {
    @Test(description = "Verify styles of Nav-bar")
    public void tVerifyStylesOfNavBar() {
        Browser.openUrl(BaseUrl + IndexUrl);
        WaitHelper.sleep(2);
        WebElement navbar = driver.findElement(By.xpath("//header"));
        WebElement logo = driver.findElement(By.xpath("//div[@class='col-md-2 logo_div']/h1"));
        WebElement signIn = driver.findElement(By.xpath("/html/body/header/div/div/div[2]/button"));
        WebElement signUp = driver.findElement(By.xpath("/html/body/header/div/div/div[3]/button"));

        Verification.softVerifyValue("Nav-bar should be displayed", navbar.isDisplayed(), sAssert);
        Verification.softVerifyValue("Logo should be displayed", logo.isDisplayed(), sAssert);
        Verification.softVerifyValue("Sign in should be displayed", signIn.isDisplayed(), sAssert);
        Verification.softVerifyValue("Sign up should be displayed", signUp.isDisplayed(), sAssert);

        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        CssPropertiesObj cssInFact = new CssPropertiesObj(navbar);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of nav-bar");

        Report.logHeading("Logo");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("36px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("39.6px");
        cssExpectedStyle.setFontWeight("500");

        cssInFact = new CssPropertiesObj(logo);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of logo");


        Report.logHeading("Sign In");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(signIn);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of Sign In inactive state");

        jMouseOver(signIn);
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssInFact = new CssPropertiesObj(signIn);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of Sign In active state");


        Report.logHeading("Sign Up");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(signUp);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of Sign Up inactive state");

        jMouseOver(signUp);
        cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
        cssInFact = new CssPropertiesObj(signIn);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of Sign Up active state");
    }

    @Test(description = "Verify visibilities and styles of tables of matches")
    public void tVerifyStyleOfTablesOfMatches() {
        Browser.openUrl(BaseUrl + IndexUrl);
        WaitHelper.sleep(2);
        List<WebElement> headersOfTables = driver.findElements(By.xpath("//div[@class='col-md-12 league_tour_div']/div[@class='row']/div[@class='col-md-12 league_tour_header_div']"));
        List<WebElement> subHeadersOfTables = driver.findElements(By.xpath("//div[@class='col-md-12 league_tour_div']/div[@class='row']/div[@class='col-md-12 league_tour_header_div']/h5"));
        List<WebElement> linksOfTables = driver.findElements(By.xpath("//div[@class='col-md-12 league_tour_div']/div[@class='row']/div[@class='col-md-12 league_tour_header_div']/a"));
        List<WebElement> matchesInARow = driver.findElements(By.xpath("(//div[@class='col-md-12 league_tour_div'])[2]/div[@class='row'][2]/div[@class='col-md-4 match_div']"));
        List<WebElement> resultsOfMatches = driver.findElements(By.xpath("//div[@class='col-md-12 league_tour_div']/div[@class='row']/div[@class='col-md-4 match_div']//span"));

        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();

        Report.logHeading("Check styles of each header in tables");
        for (WebElement element : headersOfTables) {
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");
            CssPropertiesObj cssInFact = new CssPropertiesObj(element);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of header");
        }

        Report.logHeading("Check styles of each subHead in tables");
        for (WebElement element : subHeadersOfTables) {
            cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("15.4px");
            cssExpectedStyle.setFontWeight("500");
            CssPropertiesObj cssInFact = new CssPropertiesObj(element);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of subhead");
        }

        Report.logHeading("Check styles of each link in tables");
        for (WebElement element : linksOfTables) {
            cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");
            CssPropertiesObj cssInFact = new CssPropertiesObj(element);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of link");
        }

        Report.logHeading("Check styles of each result of match");
        for (WebElement element : resultsOfMatches) {
            cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("700");
            CssPropertiesObj cssInFact = new CssPropertiesObj(element);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of result");
        }

        Verification.softVerifyValue("Amount of matches in rows < 4 ", matchesInARow.size() < 4, sAssert);
    }

    @Test(description = "Verify visibilities and styles of main news")
    public void tVerifyStyleOfMainNews() {
        Browser.openUrl(BaseUrl + IndexUrl);
        WaitHelper.sleep(2);

        WebElement mainNewsWrapper = driver.findElement(By.xpath("//div[@class='main_news_div']"));
        WebElement headline = driver.findElement(By.xpath("//div[@class='main_news_div']//h3"));
        List<WebElement> mainNews = driver.findElements(By.xpath("//div[@class='main_news_div']//p"));

        Verification.softVerifyValue("Wrapper main news should be displayed", mainNewsWrapper.isDisplayed(), sAssert);
        Verification.softVerifyValue("Headline main news should be displayed", headline.isDisplayed(), sAssert);

        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setFontSize("20px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("22px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");


        Verification.softVerifyValue("Main nes size should be < 4", mainNews.size() < 4, sAssert);

        for (WebElement element : mainNews) {
            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("12px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("17.1429px");
            cssExpectedStyle.setFontWeight("700");

            cssInFact = new CssPropertiesObj(element);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of main news");
        }
    }

    @Test(description = "Verify visibilities and styles of stat")
    public void tVerifyStyleOfStat() {
        Browser.openUrl(BaseUrl + IndexUrl);
        WaitHelper.sleep(2);

        WebElement statWrapper = driver.findElement(By.xpath("//div[@class='col-md-4 stat_div']"));
        WebElement headline = driver.findElement(By.xpath("//div[@class='col-md-4 stat_div']//h3"));
        WebElement dropdown = driver.findElement(By.xpath("//*[@id='stat_turnir_select']"));
        //List<WebElement> groups = driver.findElements(By.xpath("//table[@class='table group_table']"));

        Verification.softVerifyValue("Wrapper stat should be displayed", statWrapper.isDisplayed(), sAssert);
        Verification.softVerifyValue("Headline stat should be displayed", headline.isDisplayed(), sAssert);
        Verification.softVerifyValue("Select menu dropdown stat should be displayed", dropdown.isDisplayed(), sAssert);

        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setFontSize("24px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("26.4px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        for (int i = 2; i < 6; i++) {
            Report.logHeading("Check group " + (i - 1));

            WebElement group = driver.findElement(By.xpath("(//table[@class='table group_table'])[" + i + "]"));
            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");

            cssInFact = new CssPropertiesObj(group);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of group #" + (i - 1));
        }
    }

    @Test(description = "Verify visibilities and styles of footer")
    public void tVerifyStyleOfFooter() {
        Browser.openUrl(BaseUrl + IndexUrl);
        WaitHelper.sleep(2);

        WebElement footerWrapper = driver.findElement(By.xpath("//footer"));
        WebElement cenz = driver.findElement(By.xpath("//h4[@class='cenz']"));
        WebElement feedback = driver.findElement(By.xpath("//div[@class='col-md-4 col-md-offset-8 footer_main_div']/h4"));
        List<WebElement> links = driver.findElements(By.xpath("//div[@class='col-md-4 col-md-offset-8 footer_main_div']/a"));

        Verification.softVerifyValue("Footer wrapper should be displayed", footerWrapper.isDisplayed(), sAssert);
        Verification.softVerifyValue("Cenz should be displayed", cenz.isDisplayed(), sAssert);
        Verification.softVerifyValue("Cenz should be '2018 - 2018 | Для лиц старше 18 лет'",
                cenz.getText().equalsIgnoreCase("2018 - 2018 | Для лиц старше 18 лет"), sAssert);
        Verification.softVerifyValue("Feedback should be displayed", feedback.isDisplayed(), sAssert);
        Verification.softVerifyValue("Select menu dropdown stat should be 'Мы в социальных сетях'",
                feedback.getText().contains("Мы в социальных сетях"), sAssert);

        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(246, 248, 255, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("19.8px");
        cssExpectedStyle.setFontWeight("500");

        CssPropertiesObj cssInFact = new CssPropertiesObj(cenz);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of cenz");

        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(48, 202, 133, 1)");
        cssExpectedStyle.setFontSize("18px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("19.8px");
        cssExpectedStyle.setFontWeight("500");

        cssInFact = new CssPropertiesObj(feedback);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of feedback");

        boolean check = false;
        for (WebElement element : links) {
            String text = element.getText().toLowerCase();
            if (text.contains("вконтакте") || text.contains("одноклассники") || text.contains("facebook")) {
                check = true;
            } else {
                check = false;
            }

            cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");

            cssInFact = new CssPropertiesObj(element);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of link");
        }

        Verification.softVerifyValue("Links should be corrected", check, sAssert);
    }

}
