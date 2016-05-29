package qa.project.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 28.05.2016.
 */
public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegate;


    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
}
