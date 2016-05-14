package qa.project.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 07.05.2016.
 */
public class ContactInformationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().
                    withFirstname("Nazarova").withLastname("Polina")
                    .withAddress("Test1, test2 3/3 test-1234")
                    .withFirstEmail(" nazarova.polina@gmail.com").withThirdEmail("test12_te@gmail.com")
                    .withHomePhone("+(11)-11").withWorkPhone(" 3 33").withGroup("test11"));
        }
    }

    @Test
    public void testContactInformation(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData fullContactInfo = app.contact().infoContactDetails(contact);
        ContactData modifyContactFromDetailsForm = app.contact().modifyDetailsInfoForm(contact);

        assertThat(cleaned(fullContactInfo.getDetailsInfo()), equalTo(mergeAllContactInfo(modifyContactFromDetailsForm)));
    }

    private String mergeAllContactInfo(ContactData contact) {
        return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork(),
                contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining(""));
    }

    public static String cleaned(String infoContact){
        return infoContact.replaceAll("\\s", "").replaceAll("[-:()HMW]", "");
    }
}
