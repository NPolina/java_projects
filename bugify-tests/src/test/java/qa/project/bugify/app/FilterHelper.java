package qa.project.bugify.app;

import org.omg.CORBA.BooleanHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 04.06.2016.
 */
public class FilterHelper {

    private ApplicationManager app;
    public WebDriver wd;

    public FilterHelper(ApplicationManager app){
        this.app = app;
        this.wd = app.getDriver();
    }

    public void loginBugify(){
        wd.get(app.getProperty("web.baseUrl"));
        login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    }

    public void login(String username, String password) {
        wd.findElement(By.name("username")).sendKeys(username);
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.xpath(".//input[@type='submit']")).click();
    }

    public boolean isFilter(){
        wd.get(app.getProperty("web.filters"));
        List<WebElement> element = wd.findElements(By.xpath("//td/a"));
        if(element.size() > 0){
            return true;
        }
        else return false;
    }

    public void createFilterByClosedIssues(){
        wd.get(app.getProperty("web.closedFilterIssues"));
        wd.findElement(By.xpath("//*[@href='#save']")).click();
        wd.findElement(By.name("save_filter[name]")).sendKeys("Closed issues");
        wd.findElement(By.xpath("//*[@value='Save Filter']")).click();

    }
}
