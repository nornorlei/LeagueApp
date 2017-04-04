
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>

<form name="asd" action="/login" method="POST">

    <h1>Please login</h1>
    <p>Enter user name and password</p>
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
<input type="submit" value="Login" name="submit">
<input type="button" value="Signup" onclick=window.location.href='/signup';>

</form>
</body>
</html>