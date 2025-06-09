@echo off
rem Gradle wrapper script for Windows

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.

set GRADLE_HOME=%DIRNAME%gradle\wrapper
set GRADLE_JAR=%GRADLE_HOME%\gradle-wrapper.jar

if not exist "%GRADLE_JAR%" (
    echo ERROR: Gradle wrapper jar not found at "%GRADLE_JAR%"
    exit /b 1
)

java -jar "%GRADLE_JAR%" %*