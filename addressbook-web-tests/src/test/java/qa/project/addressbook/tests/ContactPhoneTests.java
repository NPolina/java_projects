package qa.project.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by user on 07.05.2016.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData()
                    .withFirstname("Nazarova").withLastname("Polina").withHomePhone("111").withPhone("222").withWorkPhone("333").withFirstEmail("test@test.dd").withGroup("test"));
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getPhones(), equalTo(mergePhones(contactFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::clenead)
                .collect(Collectors.joining("\n"));
    }

    public static String clenead(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
