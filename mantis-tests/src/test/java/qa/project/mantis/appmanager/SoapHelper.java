package qa.project.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import qa.project.mantis.model.Issue;
import qa.project.mantis.model.Project;
import qa.project.mantis.model.Resolution;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user on 02.06.2016.
 */
public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app){
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public Set<Resolution> getResolutions() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ObjectRef[] resolutions = mc.mc_enum_resolutions("administrator", "root");
        return Arrays.asList(resolutions).stream().map((p) -> new Resolution().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public Set<Issue> getIssuesInProject(Project project) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData[] issues = mc.mc_project_get_issues("administrator", "root", BigInteger.valueOf(project.getId()), BigInteger.valueOf(1), BigInteger.valueOf(2));
        return Arrays.asList(issues).stream().map((p) -> new Issue().withId(p.getId().intValue()).withSummary(p.getSummary())
                .withDescription(p.getDescription()).withProject(new Project().withId(p.getProject().getId().intValue()).withName(p.getProject().getName()))
                .withResolution(new Resolution().withId(p.getResolution().getId().intValue()).withName(p.getResolution().getName())))
                .collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("mantis.connect")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                          .withName(createdIssueData.getProject().getName()));
    }

    public Issue getIssueByID(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
        return new Issue().withId(issueData.getId().intValue()).withSummary(issueData.getSummary())
                .withDescription(issueData.getDescription())
                .withProject(new Project().withId(issueData.getId().intValue()).withName(issueData.getProject().getName()))
                .withResolution(new Resolution().withId(issueData.getResolution().getId().intValue()).withName(issueData.getResolution().getName()));
    }
}
