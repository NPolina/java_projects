package qa.project.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")

public class ContactData{

    @XStreamOmitField
    @Id
    private int id = Integer.MAX_VALUE;

    @Expose
    private String firstname;

    @Expose
    private String lastname;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String phone_home;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String phone_mobile;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String phone_work;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail;

    @Transient
    private String phones;

    @Transient
    private String emails;

    @Expose
    @Type(type = "text")
    @Column(name = "address")
    private String address;

    @Transient
    private String detailsInfo;

    @Expose
    @Transient
    private String photo;

    @Expose
    @Transient
    private String group;

    public String getPhoto() { return photo; }

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

    public ContactData withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (phone_home != null ? !phone_home.equals(that.phone_home) : that.phone_home != null) return false;
        if (phone_mobile != null ? !phone_mobile.equals(that.phone_mobile) : that.phone_mobile != null) return false;
        if (phone_work != null ? !phone_work.equals(that.phone_work) : that.phone_work != null) return false;
        if (firstEmail != null ? !firstEmail.equals(that.firstEmail) : that.firstEmail != null) return false;
        if (secondEmail != null ? !secondEmail.equals(that.secondEmail) : that.secondEmail != null) return false;
        if (thirdEmail != null ? !thirdEmail.equals(that.thirdEmail) : that.thirdEmail != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone_home != null ? phone_home.hashCode() : 0);
        result = 31 * result + (phone_mobile != null ? phone_mobile.hashCode() : 0);
        result = 31 * result + (phone_work != null ? phone_work.hashCode() : 0);
        result = 31 * result + (firstEmail != null ? firstEmail.hashCode() : 0);
        result = 31 * result + (secondEmail != null ? secondEmail.hashCode() : 0);
        result = 31 * result + (thirdEmail != null ? thirdEmail.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone_home='" + phone_home + '\'' +
                ", phone_mobile='" + phone_mobile + '\'' +
                ", phone_work='" + phone_work + '\'' +
                ", firstEmail='" + firstEmail + '\'' +
                ", secondEmail='" + secondEmail + '\'' +
                ", thirdEmail='" + thirdEmail + '\'' +
                ", address='" + address + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

}
