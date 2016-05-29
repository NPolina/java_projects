package qa.project.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.mantis.appmanager.HttpSession;
import qa.project.mantis.model.MailMessage;
import qa.project.mantis.model.UserData;
import qa.project.mantis.model.Users;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 28.05.2016.
 */
public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailSever(){
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException, InterruptedException {
        Users users = app.db().users();
        UserData userResetPassword = users.iterator().next();
        String newPassword = "newpassword";

        app.adminSession().login();
        app.goTo().manageUsersPage();
        app.user().resetPassword(userResetPassword);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, userResetPassword.getEmail());
        app.user().newPassword(confirmationLink, newPassword, userResetPassword);

        HttpSession session = app.newSession();
        assertTrue(session.login(userResetPassword.getUsername(), userResetPassword.getPassword()));
        assertTrue(session.isLoggedInAs(userResetPassword.getUsername()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.contains(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
