package qa.project.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by user on 15.04.2016.
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
        return;
        }
            click(By.linkText("groups"));
    }

    public void gotoAddContactPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                && isElementPresent(By.name("submit"))){
            return;
        }
        click(By.linkText("add new"));
    }

    public void gotoHome(){
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }
}
