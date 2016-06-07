package qa.project.github;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.jcabi.github.*;
import com.sun.org.apache.xpath.internal.operations.String;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by user on 07.06.2016.
 */
public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("7b2c2e1060462c48305b353076d8e0290d43ab0f");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("NPolina", "java_projects")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<java.lang.String, java.lang.String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
     }
   }
}
