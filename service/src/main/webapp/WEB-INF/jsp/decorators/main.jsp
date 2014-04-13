<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="wdcy.cc"/>
    <meta name="description" content="wdcy.cc"/>
    <meta name="author" content="wdcy.cc"/>

    <title><decorator:title default=""/> OAuth2 </title>
    <decorator:head/>

</head>
<body>
<div>
    <decorator:body/>
</div>
<div>
    <hr/>
    <p>
        <a href="mailto:chenbo@1.1">chenb@1.1</a>, from <a href="http://www.chenbtest.com" target="_blank">OAuth2 Test</a>.
    </p>
</div>
</body>
</html>