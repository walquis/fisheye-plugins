@echo off

set DEFAULT_SVNKIT_HOME=%~dp0

if "%SVNKIT_HOME%"=="" set SVNKIT_HOME=%DEFAULT_SVNKIT_HOME%

for /f %%a IN ('dir /b %SVNKIT_HOME%svnkit-cli-*.jar') do set SVNKIT_VER=%%a
SET SVNKIT_VER=%SVNKIT_VER:.jar=%
SET SVNKIT_VER=%SVNKIT_VER:svnkit-cli-=%
ECHO SVNKIT_VER: %SVNKIT_VER%

set SVNKIT_CLASSPATH=%SVNKIT_HOME%svnkit-%SVNKIT_VER%.jar;%SVNKIT_HOME%svnkit-cli-%SVNKIT_VER%.jar;%SVNKIT_HOME%svnkit-trilead-%SVNKIT_VER%.jar;%SVNKIT_HOME%svnkit-jna-%SVNKIT_VER%.jar
set SVNKIT_MAINCLASS=org.tmatesoft.svn.cli.SVN
set SVNKIT_OPTIONS=-Djava.util.logging.config.file="%SVNKIT_HOME%/logging.properties"
set PATH=%PATH%;%SVNKIT_HOME%

"%JAVA_HOME%\bin\java" %SVNKIT_OPTIONS% -cp "%SVNKIT_CLASSPATH%" %SVNKIT_MAINCLASS% %*
