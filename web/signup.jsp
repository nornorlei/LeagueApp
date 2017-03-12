<%--
  Created by IntelliJ IDEA.
  User: norman
  Date: 3/12/2017
  Time: 1:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<form name="asd" action="/Signup" method="POST">

    <h1>Please register</h1>
    <p>Enter a user name and password</p>
    <table>
        <tr>
            <th>Username</th>
            <th><input type="text" name="username"  /></th>
        </tr>
        <tr>
            <th>Password</th>
            <th><input type="password" name="password" /></th>
        </tr>
    </table>

    <input type="reset" value="Clear" name="clear">
    <input type="submit" value="Signup" name="submit">

</form>

</body>
</html>
