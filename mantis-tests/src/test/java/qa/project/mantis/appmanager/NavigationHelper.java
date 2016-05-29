package qa.project.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by user on 28.05.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper (ApplicationManager app){
        super(app);
    }

    public void manageUsersPage() {
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void logout(){
        wd.findElement(By.linkText("Logout")).click();
    }
}
