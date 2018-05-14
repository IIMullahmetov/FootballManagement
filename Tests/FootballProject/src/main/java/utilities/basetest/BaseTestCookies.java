package utilities.basetest;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import utilities.browser.Browser;


public class BaseTestCookies extends BaseTest {
    public String BaseUrl = "http://localhost:63343";
    
    public String AuthUrl = "authorization.html";
    public String BlogUrl = "blog.html";
    public String IndexUrl = "index.html";
    public String MatchUrl = "match.html";
    public String NewsUrl = "news.html";
    public String ProfileUrl = "profile.html";
    public String TeamUrl = "team.html";
    
    public WebDriver driver;
    @BeforeMethod
    protected void addSuppressingModalsCookies(){
        Browser.openUrl(BaseUrl);
        driver = Browser.getWebDriver();
        driver.manage().window().fullscreen();
        WaitHelper.sleep(2);
        Browser.executeScript("localStorage.setItem(\"tour_end\",\"yes\");");
    }
}
