package qa.project.addressbook.tests;

import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Nazarova", "Polina", "373112233", "nazarova.polina@gmail.com", "test1"), true);
        app.getContactHelper().submitAddContact();
        app.getContactHelper().gotoHomePage();
    }

}
