<%--
  Created by IntelliJ IDEA.
  User: norman
  Date: 3/12/2017
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ranked Stats</title>
</head>
<body>
<h1><%=request.getSession().getAttribute("summonerName")%>  Ranked Stats</h1>
<%=request.getAttribute("rankedStats")%>
</body>
</html>
