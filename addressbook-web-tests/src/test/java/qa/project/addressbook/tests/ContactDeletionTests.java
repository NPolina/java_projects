package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

/**
 * Created by user on 16.04.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHome();
        int before = app.getContactHelper().getContactCount();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

    @Test
    public void testContactsDeletionAll(){
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().submitContactDeletion();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, 0);
    }
}
