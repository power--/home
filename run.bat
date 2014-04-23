@echo off 
set GOPARTY_HOME=%~d0%~p0

echo ------------%GOPARTY_HOME%

SET MAVEN_OPTS=-Xmx1024m -Xms256m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n

cd %GOPARTY_HOME%service

mvn -Djetty.port=8081 jetty:run 
