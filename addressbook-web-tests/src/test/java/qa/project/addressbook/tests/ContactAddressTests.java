package qa.project.addressbook.tests;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by user on 07.05.2016.
 */
public class ContactAddressTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina").withAddress("Test1, test2 3/3\n" + "<br/>\n" + "test-1234").withGroup("test1"));
        }
    }

    @Test
    public  void testContactAddress(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData addressFromEditContactForm = app.contact().infoFromEditForm(contact);
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(addressFromEditContactForm.getAddress())));
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s", "");
    }
}
