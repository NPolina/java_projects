package qa.project.mantis.model;

import com.beust.jcommander.Parameter;

/**
 * Created by user on 02.06.2016.
 */
public class Project {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public String  getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
