<%--
  Created by IntelliJ IDEA.
  User: Kheiyasa
  Date: 3/12/17
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match History</title>
</head>
<body>
<h1><%=request.getAttribute("name")%></h1>

<%=request.getAttribute("matchHistory")%>


</body>
</html>
