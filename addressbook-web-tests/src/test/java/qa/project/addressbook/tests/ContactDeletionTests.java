package qa.project.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 16.04.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
    }

    @Test
    public void testContactsDeletionAll(){
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().submitContactDeletion();
    }
}
