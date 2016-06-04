package qa.project.bugify.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.project.bugify.models.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by user on 03.06.2016.
 */
public class RestTests  extends TestBase {

    @BeforeMethod
    public void verifyFilter() throws IOException {
        app.filter().loginBugify();
        if(!app.filter().isFilter()){
            //создание фильтра по зарытым репортам в Bugify
            app.filter().createFilterByClosedIssues();
        }
    }

    @Test
    public void testNotFixedIssue() throws IOException {
        Set<Issue> issues = app.issue().getIssues();
        Issue issue = issues.iterator().next();
        skipIfNotFixed(issue.getId());
    }

    @Test
    public void testFixedIssue() throws IOException {
        skipIfNotFixed(8);
        System.out.println("Issue is resolved!");
    }
}
