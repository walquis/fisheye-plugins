FishEye Plugins
===============

The only entry so far is *createrepo*, a dead-simple URL-triggered FishEye repo creator pointing to a Git VCS, using Atlassian's plugin SDK. 

I needed a way for our Git repo management service automatically create a corresponding FishEye repository when a new Git repo was created.  I wasn't able to find an existing app out there that did this, so I modified the "Example-Servlet" plugin to become a RepoCreator.

The piece of the API that does this is the [RepositoryAdminService](http://docs.atlassian.com/fisheye-crucible/2.2.1/javadoc/com/atlassian/fisheye/spi/admin/services/RepositoryAdminService.html), which is available from the plugin API, but not the REST api.


Getting started
---------------

Set up the Atlassian Plugin SDK environment.

### Package the plugin:

	createrepo$ atlas-plugin-package

The plugin jar will be built into the "target" directory.

### Plugin install instructions (FishEye 2.3.3)

1. Copy your plugin jar to <Fisheye-Data-Root>/var/plugins/user
1. From FishEye 2.3.3's admin plugins page, click the "Check for new plugins in <Fisheye-Data-Root>/var/plugins/user" at bottom right.  This will install the plugin.

In FishEye 2.5.x, navigate to the jar from your FishEye admin page and upload remotely.

To call the "create-repo" servlet, request this URL:

	http://localhost:8060/plugins/servlet/create-repo?project_name=MyCoolProject&repo_name=MyRepo&repo_url=<MyGitURL>

A "MyCoolProject--MyRepo" FishEye repo will be created, if it doesn't already exist.

Other Notes
----------

* The lib directory was copied from target/fecru/home/lib, created by a Maven build of fecrutwitter.
