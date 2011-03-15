package com.company.fisheye_plugin;

import com.atlassian.fisheye.spi.admin.data.GitRepositoryData;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.atlassian.fisheye.spi.admin.services.RepositoryConfigException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RepoCreatorServlet extends HttpServlet {

    private final RepositoryAdminService ras;

    @Autowired
    public RepoCreatorServlet(RepositoryAdminService r) {
        this.ras = r;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String project_name = request.getParameter("project_name");
        String repo_name = request.getParameter("repo_name");
        String repo_url = request.getParameter("repo_url");
        if (project_name==null || repo_name==null || repo_url==null) {
            PrintWriter w = response.getWriter();
            w.println("ERROR: One of the required parameters is null...");
            w.println();
            w.println("project_name = " + project_name);
            w.println("repo_name = " + repo_name);
            w.println("repo_url = " + repo_url);
            return;
        }
        String fullname = project_name + "--" + repo_name;
        GitRepositoryData rd = new GitRepositoryData(fullname,repo_url);
        try {
            this.ras.create(rd);
            this.ras.enable(fullname);
            this.ras.start(fullname);
            response.getWriter().print("Successfully set up FishEye repository named '" + fullname + "' with url '" + repo_url + "'." );
        } catch (RepositoryConfigException e) {
            System.err.println(e);
            System.err.println("project_name = " + project_name);
            System.err.println("repo_name = " + repo_name);
            System.err.println("repo_url = " + repo_url);
        }
    }
}
