home
====

home
mvn install  -Dmaven.test.skip=true

In desktop browser
http://localhost:8080/oauth/authorize?client_id=unity-client&redirect_uri=http%3a%2f%2flocalhost%3a8080%2funity%2fdashboard.htm&response_type=code&scope=read

get token
http://localhost:8080/oauth/token?client_id=mobile-client&client_secret=mobile&grant_type=password&scope=read,write&username=admin&password=admin
http://localhost:8080/oauth/token?client_id=unity-client&client_secret=unity&grant_type=password&scope=read,write&username=admin&password=admin