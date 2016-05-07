package qa.project.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;
import qa.project.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(! app.group().isThereAGroup()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2"));
        }
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.goTo().addContactPage();
        ContactData contact = new ContactData()
                .withFirstname("Nazarova").withLastname("Polina").withHomePhone("111").withPhone("222").withWorkPhone("333").withFirstEmail("nazarova.polina@gmail.com").withGroup("test1");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt()))));
    }

}
