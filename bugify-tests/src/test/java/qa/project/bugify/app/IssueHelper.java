package qa.project.bugify.app;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import qa.project.bugify.models.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by user on 03.06.2016.
 */
public class IssueHelper {

    private ApplicationManager app;

    public IssueHelper(ApplicationManager app){
        this.app = app;
    }


    public Set<Issue> getIssueByID(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    private Executor getExecutor(){
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public Set<Issue> getIssueByStatusFilter() throws IOException {
        String filter = getExecutor().execute(Request.Get("http://demo.bugify.com/api/filters/1/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(filter);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
