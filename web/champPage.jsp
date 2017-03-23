<%--
  Created by IntelliJ IDEA.
  User: Norm
  Date: 3/6/2017
  Time: 3:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Build Guides for champion</title>
</head>
<body>
<form action="buildPage" method="POST">
    <%=request.getSession().getAttribute("postList")%>
    <input type="button" value="Create Build" onclick=window.location.href='createPost.jsp';>

</form>
</body>
</html>
