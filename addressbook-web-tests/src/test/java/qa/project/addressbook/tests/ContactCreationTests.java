package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() {

        if(! app.group().isThereAGroup()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
