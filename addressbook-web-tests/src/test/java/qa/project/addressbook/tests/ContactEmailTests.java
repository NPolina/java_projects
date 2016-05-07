package qa.project.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by user on 07.05.2016.
 */
public class ContactEmailTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina")
                    .withFirstEmail(" nazarova.polina@gmail.com").withThirdEmail("test12_test@test.dd").withGroup("test1"));
        }
    }

    @Test
    public void testContactEmails(){
        app.goTo().homePage();
        ContactData contact  = app.contact().all().iterator().next();
        ContactData contactEmailFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(mergeEmails(contactEmailFromEditForm), equalTo(contact.getEmails()));
    }

    private String mergeEmails(ContactData contact){
        return Arrays.asList(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::clenead)
                .collect(Collectors.joining("\n"));
    }

    public static String clenead(String email){
        return email.replaceAll("\\s", "");
    }

}
