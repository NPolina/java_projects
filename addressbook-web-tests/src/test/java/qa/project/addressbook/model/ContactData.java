package qa.project.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")

public class ContactData {

    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String phone_home;
    private String phone_mobile;
    @Expose
    private String phone_work;
    private String firstEmail;
    @Expose
    private String secondEmail;
    @Expose
    private String thirdEmail;
    private String phones;
    private String emails;
    @Expose
    private String address;
    private String detailsInfo;
    private File photo;
    @Expose
    private String group;

    public File getPhoto() { return photo; }

    public String getDetailsInfo() { return detailsInfo; }

    public String getPhones() { return phones; }

    public String getFirstname() { return firstname; }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneMobile() {
        return phone_mobile;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() { return secondEmail; }

    public String getThirdEmail() { return thirdEmail; }

    public String getEmails() { return emails; }

    public String getGroup() { return group;}

    public int getId() {
        return id;
    }

    public String getPhoneHome() { return phone_home; }

    public String getPhoneWork() { return phone_work; }

    public String getAddress() { return address; }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withHomePhone(String phone) {
        this.phone_home = phone;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone_mobile = phone;
        return this;
    }

    public ContactData withAllPhones(String phones) {
        this.phones = phones;
        return this;
    }

    public ContactData withWorkPhone(String phone) {
        this.phone_work = phone;
        return this;
    }

    public ContactData withFirstEmail(String email) {
        this.firstEmail = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withAllEmails(String emails) {
        this.emails = emails;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withDetailsInfo(String detailsInfo) {
        this.detailsInfo = detailsInfo;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }


}
