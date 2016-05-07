package qa.project.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import qa.project.addressbook.model.ContactData;
import qa.project.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by user on 16.04.2016.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("home"),contactData.getPhoneHome());
        type(By.name("mobile"),contactData.getPhoneMobile());
        type(By.name("work"),contactData.getPhoneWork());
        type(By.name("email"),contactData.getEmail());

        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void returnHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public void submitAddContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitAddContact();
        returnHomePage();
    }

    public void selectContactToEdit(int id) {
        WebElement checkbox = wd.findElement(By.id("" + id));
        checkbox.findElement(By.xpath("//img[@title='Edit']")).click();
    }

    public void selectContactById(int id){
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }

    public void modify(ContactData contact) {
        selectContactToEdit(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnHomePage();
    }

    public void delete(ContactData contact) throws InterruptedException {
        selectContactById(contact.getId());
        submitContactDeletion();
        Thread.sleep(5000);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement element : elements){
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String allphones = element.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allphones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        selectContactToEdit(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lasttname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home_phone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile_phone = wd.findElement(By.name("mobile")).getAttribute("value");
        String work_phone = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withFirstname(firstname).withLastname(lasttname).withHomePhone(home_phone).withPhone(mobile_phone).withWorkPhone(work_phone);

    }
}
