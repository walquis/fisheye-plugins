Set up the Atlassian Plugin SDK environment.

Package the plugin:
createrepo$ atlas-plugin-package

The plugin jar will be built into the "target" directory.

Plugin install instructions for FishEye 2.3.3:
- Copy your plugin jar to <Fisheye-Data-Root>/var/plugins/user
- From FishEye 2.3.3's admin plugins page, click the "Check for new plugins in <Fisheye-Data-Root>/var/plugins/user" at bottom right.  This will install the plugin.

If in FishEye 2.5.x, you can just navigate to the jar from your FishEye admin page and upload remotely.

To call the "/create-repo" servlet:

http://localhost:8060/plugins/servlet/create-repo?project_name=MyCoolProject&repo_name=MyRepo&repo_url=<MyGitURL>

A "MyCoolProject--MyRepo" FishEye repo will be created, if it doesn't already exist.

Notes:
- The lib directory was copied from target/fecru/home/lib, created by a Maven build of fecrutwitter.
