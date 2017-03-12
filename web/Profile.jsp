<%--
  Created by IntelliJ IDEA.
  User: norman
  Date: 3/12/2017
  Time: 3:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Hi Summoner <%=request.getSession().getAttribute("username")%></h1>
<p><a href="findSum.jsp">Find Summoner</a></p>
<p><a href="champs.jsp">Look for builds</a></p>
</body>
</html>
