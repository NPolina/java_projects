package qa.project.bugify.tests;

import org.openqa.selenium.remote.BrowserType;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import qa.project.bugify.app.ApplicationManager;
import qa.project.bugify.models.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by user on 15.04.2016.
 */
public class TestBase {


    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> issue = app.issue().getIssueByID(issueId);
        Issue selectedIssue = issue.iterator().next();
        Set<Issue> resolvedIssues = app.issue().getIssueByStatusFilter();
        for (Issue i : resolvedIssues)
            if (selectedIssue.getId() == i.getId()) {
                return false;
            }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
