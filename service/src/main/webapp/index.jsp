<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h3>spring-oauth is work!</h3>

<p>
    <a href="${contextPath}/logout.do">Logout</a>
</p>

<div>
    Notes:
    <ol>
        <li>
            User is not needed by OAuth, <br/>
            Unity and Mobile need OAuth
        </li>
        <li>
          Test. There is still much code to be modified.
        </li>
    </ol>
</div>
<br/>
Menu
<ul>
    <li>
        <a href="${contextPath}/user/overview.htm">User</a>
    </li>
    <li>
        <a href="${contextPath}/unity/dashboard.htm">Unity</a>
    </li>
    <li>
        <a href="${contextPath}/m/dashboard.htm">Mobile</a>
    </li>
</ul>
</body>
</html>