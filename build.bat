@echo off 
setlocal enabledelayedexpansion 
set GOPARTY_HOME=%~d0%~p0

echo ------------%GOPARTY_HOME%

mvn install  -Dmaven.test.skip=true
