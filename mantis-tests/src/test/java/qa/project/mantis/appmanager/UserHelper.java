package qa.project.mantis.appmanager;

import org.openqa.selenium.By;
import qa.project.mantis.model.UserData;
import qa.project.mantis.tests.TestBase;

/**
 * Created by user on 28.05.2016.
 */
public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app){
        super(app);
    }

    public void selectUserById(int id){
        wd.findElement(By.xpath("//*[@href='manage_user_edit_page.php?user_id=" + id + "']")).click();
    }

    public void resetButtonClick(){
        wd.findElement(By.cssSelector("[value~=Password]")).click();
    }

    public void resetPassword(UserData user) {
        selectUserById(user.getId());
        resetButtonClick();

    }

    public UserData newPassword(String confirmationLink, String password, UserData user){
        UserData changeUser = user.withPassword(password).withId(user.getId()).withEmail(user.getEmail()).setUsername(user.getUsername());
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
        return changeUser;
    }
}
