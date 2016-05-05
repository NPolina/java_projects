package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by user on 16.04.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() throws InterruptedException {
        app.goTo().gotoHome();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().submitContactDeletion();
        Thread.sleep(5000);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

    @Test(enabled = false)
    public void testContactsDeletionAll(){
        app.goTo().gotoHome();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().submitContactDeletion();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, 0);
    }
}
