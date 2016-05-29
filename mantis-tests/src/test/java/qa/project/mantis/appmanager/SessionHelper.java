package qa.project.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 15.04.2016.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.className("button"));
    }
}
