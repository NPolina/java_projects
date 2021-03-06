package qa.project.bugify.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 15.04.2016.
 */
public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private IssueHelper issueHelper;
    private FilterHelper filterHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
         String target = System.getProperty("target", "local");
         properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if(wd != null){
            wd.quit();
        }
    }

    public String getProperty(String key){
       return properties.getProperty(key);
    }

    public WebDriver getDriver() {
        if (wd == null){
            if(Objects.equals(browser, BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            } else if(Objects.equals(browser, BrowserType.CHROME)){
                wd = new ChromeDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public IssueHelper issue(){
        if(issueHelper == null){
            issueHelper = new IssueHelper(this);
        }
        return issueHelper;
    }

    public FilterHelper filter(){
        if(filterHelper == null){
            filterHelper = new FilterHelper(this);
        }
        return filterHelper;
    }
}
