package qa.project.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by user on 16.04.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() == 0){
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina").withPhone("373112233").withEmail("nazarova.polina@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
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
