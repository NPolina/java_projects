package qa.project.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by user on 27.05.2016.
 */
public class RegistrationTests extends TestBase {

    //нужен если подключается встроенный почтовый сервер wiser
    //@BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);

        //отправка писем через встроенный почтовый сервер
       // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);

        //отправка писем через внешний сервер telnet
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.contains(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    //нужен если подключается встроенный почтовый сервер wiser
    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
