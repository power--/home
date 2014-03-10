@echo off 
setlocal enabledelayedexpansion 
set GOPARTY_HOME=%~d0%~p0

call build.bat

call run.bat