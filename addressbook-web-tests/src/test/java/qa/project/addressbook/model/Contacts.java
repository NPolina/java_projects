package qa.project.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 06.05.2016.
 */
public class Contacts extends ForwardingSet<ContactData> {
    private Set<ContactData> delegate;

    public Contacts(Contacts contacts){this.delegate = new HashSet<ContactData>(contacts.delegate());}

    public Contacts(){this.delegate = new HashSet<>();}

    public Contacts withAdded(ContactData contact){
        Contacts contacts = new Contacts();
        contacts.add(contact);
        return contacts;
    }
    
    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts();
        contacts.remove(contact);
        return contacts;
    }

    @Override
    protected Set<ContactData> delegate() {
        return null;
    }
}
