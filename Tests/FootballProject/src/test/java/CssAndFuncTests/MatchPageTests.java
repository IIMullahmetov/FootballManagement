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


public class MatchPageTests extends BaseTestCookies {
    @Test(description = "Verify nav-bar visibility")
    public void tVerifyNavBarVisibility() {
        Browser.openUrl(BaseUrl + MatchUrl);
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

    @Test(description = "Verify images in score table")
    public void tVerifyImagesInScoreTable() {
        Browser.openUrl(BaseUrl + MatchUrl);
        WaitHelper.sleep(2);

        List<WebElement> images = driver.findElements(By.xpath("//div[contains(@class,'team_logo_block')]//img"));

        Dimension expectedSize = new Dimension(120, 120);

        for (WebElement element : images) {
            Verification.softVerifyValue("Image src shouldn't be empty", !element.getAttribute("src").isEmpty(), sAssert);
            Verification.softVerifyValue("Image size should be ", element.getSize().height == expectedSize.height
                    && element.getSize().width == expectedSize.width, sAssert);
        }

        Verification.softVerifyValue("Amount of images should be 2", images.size() == 2, sAssert);
    }

    @Test(description = "Verify visibilities and styles of players and scores")
    public void tVerifyStylesOfPlayersAndScores() {
        Browser.openUrl(BaseUrl + MatchUrl);
        WaitHelper.sleep(2);

        List<WebElement> goals = driver.findElements(By.xpath("//div[contains(@class,'team_gols_block')]//h1"));
        List<WebElement> players = driver.findElements(By.xpath("//div[contains(@class,'team_gols_block')]//ul[@class='list-unstyled']//li"));

        Verification.softVerifyValue("Amount of goals elements should be 2", goals.size() == 2, sAssert);

        int counterOfNought = 0;
        Report.logHeading("Verify styles of goals");
        for (WebElement goal : goals) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(51, 51, 51, 1)");
            cssExpectedStyle.setFontSize("64px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("70.4px");
            cssExpectedStyle.setFontWeight("700");

            CssPropertiesObj cssInFact = new CssPropertiesObj(goal);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of goal");
            if (goal.getText().equals("0")) {
                counterOfNought++;
            }
        }

        Report.logHeading("Verify styles of players");
        if (counterOfNought != 2) {
            for (WebElement player : players) {
                CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
                cssExpectedStyle.setColor("rgba(17, 75, 153, 1)");
                cssExpectedStyle.setFontSize("14px");
                cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
                cssExpectedStyle.setLineHeight("20px");
                cssExpectedStyle.setFontWeight("400");

                CssPropertiesObj cssInFact = new CssPropertiesObj(player);
                cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of player");
            }
        }

        for (WebElement goal : goals) {
            if (goal.getText().equals("0")) {
                Verification.softVerifyValue("Lists pf players should be < 2", players.size() < 2, sAssert);
            }
        }
    }


    @Test(description = "Verify visibilities and styles of date and other inf")
    public void tVerifyStylesOfDateAndOtherInf() {
        Browser.openUrl(BaseUrl + MatchUrl);
        WaitHelper.sleep(2);

        WebElement dateWrapper = driver.findElement(By.xpath("//div[@class='col-md-4 match_info_block']"));
        WebElement date = driver.findElement(By.xpath("//div[@class='col-md-4 match_info_block']/p[@class='match_time']"));
        WebElement stadium = driver.findElement(By.xpath("//div[@class='col-md-4 match_info_block']/p[@class='stadium_name']"));

        Verification.softVerifyValue("Date wrapper should be displayed", dateWrapper.isDisplayed(), sAssert);

        Report.logHeading("Verify styles of date");
        CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(196, 196, 196, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        CssPropertiesObj cssInFact = new CssPropertiesObj(date);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of date");

        Report.logHeading("Verify styles of stadium");
        cssExpectedStyle = new CssPropertiesObj();
        cssExpectedStyle.setColor("rgba(196, 196, 196, 1)");
        cssExpectedStyle.setFontSize("14px");
        cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
        cssExpectedStyle.setLineHeight("20px");
        cssExpectedStyle.setFontWeight("400");

        cssInFact = new CssPropertiesObj(stadium);
        cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of stadium");
    }

    @Test(description = "Verify visibilities and styles of navigation")
    public void tVerifyStylesOfNavigation() {
        Browser.openUrl(BaseUrl + MatchUrl);
        WaitHelper.sleep(2);

        WebElement statBlock = driver.findElement(By.xpath("//div[@class='col-md-4 match_info_block']"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='col-md-12 match_stat_block']//ul//li/a"));

        Verification.softVerifyValue("Stat block should be displayed", statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Buttons size should be 3", buttons.size() == 3, sAssert);

        Report.logHeading("Verify styles of buttons");
        for (WebElement button : buttons) {
            CssPropertiesObj cssExpectedStyle = new CssPropertiesObj();
            cssExpectedStyle.setColor("rgba(255, 255, 255, 1)");
            cssExpectedStyle.setFontSize("14px");
            cssExpectedStyle.setFontFamily("\"Helvetica Neue\", Helvetica, Arial, sans-serif");
            cssExpectedStyle.setLineHeight("20px");
            cssExpectedStyle.setFontWeight("400");

            CssPropertiesObj cssInFact = new CssPropertiesObj(button);
            cssInFact.compareWithAsert(cssExpectedStyle, "Verify style of button");
        }

    }

    @Test(description = "Verify functionality of navigation")
    public void tFunctionalityOfNavigation() {
        Browser.openUrl(BaseUrl + MatchUrl);
        WaitHelper.sleep(2);

        WebElement statBlock = driver.findElement(By.xpath("//*[@id='stat_panel']"));
        WebElement newsBlock = driver.findElement(By.xpath("//*[@id='news_panel']"));
        WebElement teamBlock = driver.findElement(By.xpath("//*[@id='teams_panel']"));
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='col-md-12 match_stat_block']//ul//li"));
        WebElement stat = buttons.stream().filter(x -> x.getText().contains("Статистика")).findFirst().get();
        WebElement news = buttons.stream().filter(x -> x.getText().contains("Новости")).findFirst().get();
        WebElement team = buttons.stream().filter(x -> x.getText().contains("Составы")).findFirst().get();

        Verification.softVerifyValue("Stat block default should be displayed", statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("News block default should not be displayed", !newsBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Team block default should not be displayed", !teamBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Buttons size should be 3", buttons.size() == 3, sAssert);
        Verification.softVerifyValue("Stat button should be activated", stat.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("News button should be not activated", !news.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Team button should be not activated", !team.getAttribute("class").equals("active"), sAssert);

        CssPropertiesObj cssActive = new CssPropertiesObj();
        cssActive.setBackgroundColor("rgba(15, 194, 114, 1)");
        CssPropertiesObj cssInActive = new CssPropertiesObj();
        cssInActive.setBackgroundColor("rgba(0, 0, 0, 0)");

        CssPropertiesObj cssInFact = new CssPropertiesObj(stat.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssActive, "Verify style of active stat button");

        cssInFact = new CssPropertiesObj(news.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive news button");

        cssInFact = new CssPropertiesObj(team.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive team button");

        news.click();
        WaitHelper.sleep(5);

        Verification.softVerifyValue("Stat block default should not be displayed", !statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("News block default should be displayed", newsBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Team block default should not be displayed", !teamBlock.isDisplayed(), sAssert);

        Verification.softVerifyValue("Stat button should be not activated", !stat.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("News button should be activated", news.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Team button should be not activated", !team.getAttribute("class").equals("active"), sAssert);

        cssInFact = new CssPropertiesObj(stat.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive stat button");

        cssInFact = new CssPropertiesObj(news.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of active news button");

        cssInFact = new CssPropertiesObj(team.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive team button");

        team.click();
        WaitHelper.sleep(5);

        Verification.softVerifyValue("Stat block default should not be displayed", !statBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("News block default should not be displayed", !newsBlock.isDisplayed(), sAssert);
        Verification.softVerifyValue("Team block default should be displayed", teamBlock.isDisplayed(), sAssert);

        Verification.softVerifyValue("Stat button should be not activated", !stat.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("News button should be not activated", !news.getAttribute("class").equals("active"), sAssert);
        Verification.softVerifyValue("Team button should be activated", team.getAttribute("class").equals("active"), sAssert);

        cssInFact = new CssPropertiesObj(stat.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive stat button");

        cssInFact = new CssPropertiesObj(news.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssInActive, "Verify style of inactive news button");

        cssInFact = new CssPropertiesObj(team.findElement(By.xpath("//a")));
        cssInFact.compareWithAsert(cssActive, "Verify style of active team button");
    }
}
