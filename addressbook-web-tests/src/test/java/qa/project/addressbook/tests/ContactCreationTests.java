package qa.project.addressbook.tests;

import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        if(! app.getGroupHelper().isThereAGroup()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1"), true);
    }

}
