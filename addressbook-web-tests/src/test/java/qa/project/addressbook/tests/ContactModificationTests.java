package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 16.04.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification(){
        app.goTo().gotoHome();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactToEdit(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
