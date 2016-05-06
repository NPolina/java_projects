package qa.project.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.contact().list();
        app.goTo().addContactPage();
        ContactData contact = new ContactData()
                .withFirstname("Nazarova").withLastname("Polina").withPhone("0373112233").withEmail("nazarova.polina@gmail.com").withGroup("test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
