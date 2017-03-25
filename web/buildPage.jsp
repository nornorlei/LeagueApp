<%--
  Created by IntelliJ IDEA.
  User: norman
  Date: 3/6/2017
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Build</title>
</head>
<body>
<%=request.getSession().getAttribute("buildList")%>
<input type="button" value="Return to Profile" onclick=window.location.href='/Profile';>
<input type="button" value="Back to build list" onclick=window.location.href='/champPage.jsp';>
<input type="button" value="Look for build for another champ" onclick=window.location.href='/champs.jsp';>
</body>
</html>
