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
    <title>Runes/Masteries</title>
</head>
<body>
<h1><%=request.getSession().getAttribute("summonerName")%></h1>
<h2>MASTERIES</h2>
<p><%=request.getSession().getAttribute("masteryList")%></p>
<h3>RUNES</h3>
<p><%=request.getSession().getAttribute("runeList")%></p>

</body>
</html>
