package qa.project.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by user on 16.04.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina").withPhone("373112233")
                    .withFirstEmail("nazarova.polina@gmail.com").withGroup("test 0").withPhoto("src/test/resources/smile.png"));
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId((modifiedContact).getId()).withFirstname("Nazarova").withLastname("Polina")
                .withHomePhone(modifiedContact.getPhoneHome()).withPhone("222222222").withWorkPhone("3333333333")
                .withFirstEmail("nazpo1@gmail.com").withSecondEmail(modifiedContact.getSecondEmail()).withThirdEmail("nazpo2@mail.ru")
                .withAddress(modifiedContact.getAddress()).withPhoto("src/test/resources/smile.png");
        app.goTo().homePage();
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
      }
}
