package qa.project.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

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
                    .withFirstname("Nazarova").withLastname("Polina").withHomePhone("111").withPhone("222").withWorkPhone("333").withEmail("test@test.dd").withGroup("test"));
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getPhone_home(), equalTo(clenead(contactFromEditForm.getPhone_home())));
        assertThat(contact.getPhone_mobile(), equalTo(clenead(contactFromEditForm.getPhone_mobile())));
        assertThat(contact.getPhone_work(), equalTo(clenead(contactFromEditForm.getPhone_work())));
    }

    public String clenead(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
