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


public class TeamPageTests extends BaseTestCookies {

    @Test(description = "Verify nav-bar visibility")
    public void tVerifyNavBarVisibility() {
        Browser.openUrl(BaseUrl + TeamUrl);
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


    @Test(description = "Verify image in team page")
    public void tVerifyImageInTeamPage() {
        Browser.openUrl(BaseUrl + TeamUrl);
        WaitHelper.sleep(2);

        WebElement image = driver.findElement(By.xpath("//div[@class='col-md-2 team_logo']//img"));

        Dimension expectedSize = new Dimension(93, 136);
        Verification.softVerifyValue("Image src shouldn't be empty", !image.getAttribute("src").isEmpty(), sAssert);
        Verification.softVerifyValue("Image size should be ", image.getSize().height == expectedSize.height
                && image.getSize().width == expectedSize.width, sAssert);
    }

    @Test(description = "Verify visibilities and styles of team information")
    public void tVerifyStylesOfTeamInformation() {
        Browser.openUrl(BaseUrl + TeamUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-3 team_info_head']"));
        WebElement headline = driver.findElement(By.xpath("//div[@class='col-md-3 team_info_head']//h2"));
        WebElement name = driver.findElement(By.xpath("//div[@class='col-md-3 team_info_head']//p[1]"));
        WebElement imageTeam = driver.findElement(By.xpath("//div[@class='col-md-3 team_info_head']//p[2]//img"));
        WebElement coach = driver.findElement(By.xpath("//div[@class='col-md-3 team_info_head']//p[3]"));

        Verification.softVerifyValue("Information team should be displayed", wrap.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of headline");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
        cssExpectedStyle.setFontSize("30px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("33px");
        cssExpectedStyle.setFontWeight("700");

        CssPropertiesObj cssInFact = new CssPropertiesObj(headline);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of headline");

        Report.logHeading("Verify styles of name");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(128, 128, 128, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(name);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of name");

        Report.logHeading("Verify styles of coach");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(128, 128, 128, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(coach);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of coach");

        Dimension expectedSize = new Dimension(21, 14);
        Report.logHeading(String.valueOf(imageTeam.getSize().height));
        Report.logHeading(String.valueOf(imageTeam.getSize().width));
        Verification.softVerifyValue("Image team src shouldn't be empty", !imageTeam.getAttribute("src").isEmpty(), sAssert);
        Verification.softVerifyValue("Image team size should be ", imageTeam.getSize().height == expectedSize.height
                && imageTeam.getSize().width == expectedSize.width, sAssert);
    }


    @Test(description = "Verify visibilities and styles of recently matches team")
    public void tVerifyStylesOfRecentlyMatchesTeam() {
        Browser.openUrl(BaseUrl + TeamUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-3 team_last_maches']"));
        List<WebElement> otherInf = driver.findElements(By.xpath("//div[@class='col-md-3 team_last_maches']//div[@class='col-md-12 match_block']/p"));
        List<WebElement> scores = driver.findElements(By.xpath("//div[@class='col-md-3 team_last_maches']//div[@class='col-md-12 match_block']/h4"));

        Verification.softVerifyValue("Lastmatches should be displayed", wrap.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of other information");
        for (WebElement inf : otherInf) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");

            CssPropertiesObj cssInFact = new CssPropertiesObj(inf);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of information");
        }

        Report.logHeading("Verify styles of scores");
        for (WebElement score : scores) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("18px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("19.8px");
            cssExpectedStyle.setFontWeight("500");

            CssPropertiesObj cssInFact = new CssPropertiesObj(score);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of score");
        }
    }

    @Test(description = "Verify visibilities and styles of achievements team")
    public void tVerifyStylesOfAchievementsTeam() {
        Browser.openUrl(BaseUrl + TeamUrl);
        WaitHelper.sleep(2);

        WebElement wrap = driver.findElement(By.xpath("//div[@class='col-md-4 short_stat']"));
        List<WebElement> achievements = driver.findElements(By.xpath("//ul[@class='list-inline achievments_list']//li"));

        Verification.softVerifyValue("Stat wrap should be displayed", wrap.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of achievements");
        for (WebElement achieve : achievements) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setBackgroundColor("rgba(0, 0, 0, 1)");
            cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");

            CssPropertiesObj cssInFact = new CssPropertiesObj(achieve);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of achieve");
        }
    }

    @Test(description = "Verify visibilities and styles of best bombardier")
    public void tVerifyStylesOfBestBombardier() {
        Browser.openUrl(BaseUrl + TeamUrl);
        WaitHelper.sleep(2);

        WebElement name = driver.findElement(By.xpath("//p[@class='best_goleador']"));
        WebElement image = driver.findElement(By.xpath("//p[@class='best_goleador']//img"));

        Dimension expectedSize = new Dimension(36, 36);
        Report.logHeading(String.valueOf(image.getSize().height));
        Report.logHeading(String.valueOf(image.getSize().width));
        Verification.softVerifyValue("Image team src shouldn't be empty", !image.getAttribute("src").isEmpty(), sAssert);
        Verification.softVerifyValue("Image team size should be ", image.getSize().height == expectedSize.height
                && image.getSize().width == expectedSize.width, sAssert);


        Report.logHeading("Verify styles of name");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(51, 51, 51, 1))");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(name);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of name bombardier");
    }

    @Test(description = "Verify functionality of navigation team page")
    public void tFunctionalityOfNavigationTeamPage() {
        Browser.openUrl(BaseUrl + TeamUrl);
        WaitHelper.sleep(2);

        WebElement statBlock = driver.findElement(By.xpath("//*[@id='stat_panel']"));
        WebElement calendarBlock = driver.findElement(By.xpath("//*[@id='calendar_panel']"));
        WebElement teamBlock = driver.findElement(By.xpath("//*[@id='team_panel']"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='col-md-12 team_stat_block']//ul//li"));
        WebElement stat = buttons.stream().filter(x -> x.getText().contains("Статистика")).findFirst().get();
        WebElement calendar = buttons.stream().filter(x -> x.getText().contains("Календарь")).findFirst().get();
        WebElement team = buttons.stream().filter(x -> x.getText().contains("Состав")).findFirst().get();

        Verification.softVerifyValue("Stat block default should not be displayed", !statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Calendar block default should be displayed", calendarBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Team block default should not be displayed", !teamBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Buttons size should be 3", buttons.size() == 3, sAssert);
        Verification.softVerifyValue("Stat button should be not activated", !stat.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Calendar button should be activated", calendar.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Team button should be not activated", !team.getAttribute("class").equals("active"), sAssert);

        CssPropertiesObj cssActive = new CssPropertiesObj();
        cssActive.setBackgroundColor("rgba(15, 194, 114, 1)");
        CssPropertiesObj cssInActive = new CssPropertiesObj();
        cssInActive.setBackgroundColor("rgba(0, 0, 0, 0)");

        CssPropertiesObj cssInFact = new CssPropertiesObj(stat.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssActive, "Verify style of inactive stat button");

        cssInFact = new CssPropertiesObj(calendar.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssActive, "Verify style of active Calendar button");

        cssInFact = new CssPropertiesObj(team.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssActive, "Verify style of inactive team button");

        stat.click();
        WaitHelper.sleep(5);

        Verification.softVerifyValue("Stat block default should be displayed", !statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Calendar block default should not be displayed", !calendarBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Team block default should not be displayed", !teamBlock.isDisplayed(), sAssert);

        Verification.softVerifyValue("Stat button should be activated", stat.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Calendar button should be not activated", !calendar.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Team button should be not activated", !team.getAttribute("class").equals("active"), sAssert);

        cssInFact = new CssPropertiesObj(stat.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive stat button");

        cssInFact = new CssPropertiesObj(calendar.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of active Calendar button");

        cssInFact = new CssPropertiesObj(team.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive team button");

        team.click();
        WaitHelper.sleep(5);

        Verification.softVerifyValue("Stat block default should not be displayed", !statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Calendar block default should not be displayed", !calendarBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Team block default should be displayed", teamBlock.isDisplayed(), sAssert);

        Verification.softVerifyValue("Stat button should be not activated", !stat.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Calendar button should be not activated", !calendar.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Team button should be activated", team.getAttribute("class").equals("active"), sAssert);

        cssInFact = new CssPropertiesObj(stat.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive stat button");

        cssInFact = new CssPropertiesObj(calendar.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive Calendar button");

        cssInFact = new CssPropertiesObj(team.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of active team button");
    }

}
