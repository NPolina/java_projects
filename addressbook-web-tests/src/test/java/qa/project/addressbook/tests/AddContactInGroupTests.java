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
 * Created by user on 22.05.2016.
 */
public class AddContactInGroupTests extends TestBase{

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
    public void testAddContactInGroup()  {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData groupForAddContact = groups.stream().iterator().next();
        ContactData addContact = contacts.iterator().next();
        Contacts allContacts = groupForAddContact.getContacts();

        if(app.contact().existsInGroup(groupForAddContact, addContact))
        {
            app.goTo().groupPage();
            app.group().create(groupForAddContact.withName(String.format("test %s", groups.size())));
            app.goTo().homePage();
            app.contact().addContactInGroup(addContact, groupForAddContact);
            assertThat(app.contact().contactsFromGroupPage(groupForAddContact).size(), equalTo(1));
        }
        else{
            app.contact().addContactInGroup(addContact, groupForAddContact);
            assertThat(app.contact().contactsFromGroupPage(groupForAddContact).size(), equalTo(allContacts.size() + 1));
        }
        Groups groupsAfter = app.db().groups();
        assertThat(groupsAfter.iterator().next().getContacts(),
                equalTo(app.contact().contactsFromGroupPage(groupForAddContact).withAdded(addContact)));
    }
}
