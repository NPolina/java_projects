package qa.project.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;
import qa.project.addressbook.model.GroupData;
import qa.project.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 23.05.2016.
 */
public class DeleteContactFromGroupTests extends TestBase{

    @BeforeMethod
    private void ensurePreconditionsGroupExists() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test 0"));
        }
    }

    @BeforeMethod
    private void ensurePreconditionsContactExists(){
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina").withAddress("Test1, test2 3/3 test-1234")
                    .withFirstEmail(" nazarova.polina@gmail.com").withThirdEmail("test12_te@gmail.com")
                    .withHomePhone("+(11)-11").withWorkPhone(" 373 00 333 666").withPhoto("src/test/resources/smile.png"));
        }
    }

    @Test
    public void testDeleteContactFromGroup(){
        app.goTo().homePage();
        Contacts contactsBefore = app.db().contacts();
        Groups groupsBefore = app.db().groups();
        GroupData groupForContactDelete = groupsBefore.iterator().next();
        ContactData deletedContactFromGroup = contactsBefore.iterator().next();
        Contacts allContacts = groupForContactDelete.getContacts();

        if(app.contact().contactsFromGroupPage(groupForContactDelete).size() == 0)
        {
            app.contact().addContactInGroup(deletedContactFromGroup, groupForContactDelete);
            app.contact().deleteContactFromGroup(deletedContactFromGroup, groupForContactDelete);
            assertThat(app.contact().contactsFromGroupPage(groupForContactDelete).size(), equalTo(allContacts.size()));
        }
        else{
            deletedContactFromGroup = app.contact().contactsFromGroupPage(groupForContactDelete).iterator().next();
            app.contact().deleteContactFromGroup(deletedContactFromGroup, groupForContactDelete);
            assertThat(app.contact().contactsFromGroupPage(groupForContactDelete).size(), equalTo(allContacts.size()-1));
        }
        Groups groupsAfter = app.db().groups();
        assertThat(groupsAfter.iterator().next().getContacts(), equalTo(allContacts.without(deletedContactFromGroup)));

    }
}
