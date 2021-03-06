package qa.project.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

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

    public int getId() {
        return id;
    }

    public String getPhoneHome() { return phone_home; }

    public String getPhoneWork() { return phone_work; }

    public String getAddress() { return address; }

    public Groups getGroups() {
        return new Groups(groups);
    }

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

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
