package qa.project.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by user on 16.04.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.db().contacts().size() == 0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina").withPhone("373112233").withFirstEmail("nazarova.polina@gmail.com")
                    .withGroup("test 0").withPhoto("src/test/resources/smile.png"));
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));

    }

    @Test(enabled = false)
    public void testContactsDeletionAll(){
        app.goTo().homePage();
        app.contact().selectAllContacts();
        app.contact().submitContactDeletion();
        int after = app.contact().getContactCount();
        assertEquals(after, 0);
    }
}
