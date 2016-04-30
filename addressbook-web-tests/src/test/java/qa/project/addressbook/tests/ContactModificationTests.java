package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

/**
 * Created by user on 16.04.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHome();
        int before = app.getContactHelper().getContactCount();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1"));
        }
        app.getContactHelper().selectContactToEdit();
        app.getContactHelper().fillContactForm(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
