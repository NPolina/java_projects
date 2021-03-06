package qa.project.mantis.model;

/**
 * Created by user on 02.06.2016.
 */
public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;
    private Resolution resolution;


    public Issue withResolution(Resolution resolution) {
        this.resolution = resolution;
        return this;
    }

    public Resolution getResolution() {

        return resolution;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", project=" + project +
                ", resolution=" + resolution +
                '}';
    }
}
