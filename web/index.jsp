<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>League App</title>
  </head>
  <body>
  <h1>Welcome Summoner!</h1>

  <p>Type in a summoner name in order to get details regarding the user.</p>
  <form action="/Signup" method="POST">
    <input type="text" name="username" placeholder="Username">
    <input type="text" name="password" placeholder="Password">
    <input type="email" name="email" placeholder="Email">
    <input type="submit" name="submit" value="Submit">
  </form>
  </body>
</html>