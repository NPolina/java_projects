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
        type(By.name("email"),contactData.getFirstEmail());
        type(By.name("email2"),contactData.getSecondEmail());
        type(By.name("email3"),contactData.getThirdEmail());
        type(By.name("address"),contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

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
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allphones = element.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllPhones(allphones).withAllEmails(allmails));
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
        String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().
                withFirstname(firstname).withLastname(lasttname).withAddress(address)
                .withHomePhone(home_phone).withPhone(mobile_phone).withWorkPhone(work_phone)
                .withFirstEmail(firstEmail).withSecondEmail(secondEmail).withThirdEmail(thirdEmail);

    }

    public ContactData infoContactDetails(ContactData contact) {
        String content;
        viewContactInformationById(contact.getId());
        content = wd.findElement(By.xpath(".//div[@id='content']")).getText();
        if(isElementPresent(By.xpath(".//i")) && isElementPresent(By.xpath(".//*[@id='content']//*[contains(@href,'http')]"))){
            String group = wd.findElement(By.xpath(".//i")).getText();
            List<WebElement> el = wd.findElements(By.xpath(".//*[@id='content']//*[contains(@href,'http')]"));
            for (WebElement i : el){
                content = content.replace(i.getText(), "");
            }
            content = content.replace(group, "");
        }
        wd.navigate().back();
        return new ContactData().withDetailsInfo(content);

    }

    public void viewContactInformationById(int id) {
        WebElement element = wd.findElement(By.id("" + id));
        element.findElement(By.xpath("//img[@title='Details']")).click();
    }

    public void modifyContactDetails(){
        wd.findElement(By.name("modifiy")).click();
    }

    public ContactData modifyDetailsInfoForm(ContactData contact) {
        viewContactInformationById(contact.getId());
        modifyContactDetails();
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lasttname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home_phone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile_phone = wd.findElement(By.name("mobile")).getAttribute("value");
        String work_phone = wd.findElement(By.name("work")).getAttribute("value");
        String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().
                withFirstname(firstname).withLastname(lasttname).withAddress(address)
                .withHomePhone(home_phone).withPhone(mobile_phone).withWorkPhone(work_phone)
                .withFirstEmail(firstEmail).withSecondEmail(secondEmail).withThirdEmail(thirdEmail);
    }
}
