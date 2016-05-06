package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by user on 16.04.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina").withPhone("373112233").withEmail("nazarova.polina@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

    @Test(enabled = false)
    public void testContactsDeletionAll(){
        app.goTo().homePage();
        app.contact().selectAllContacts();
        app.contact().submitContactDeletion();
        int after = app.contact().getContactCount();
        Assert.assertEquals(after, 0);
    }
}
