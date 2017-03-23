<%--
  Created by IntelliJ IDEA.
  User: norman
  Date: 3/12/2017
  Time: 3:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Summoner</title>
</head>
<body>
<h1>Find Summoner</h1>

<form action="findSum" method="POST">
<p>Please enter a summoner name and respective region</p>
    <table>
        <tr>
            <th>Summoner Name</th>
            <th><input type="text" name="summonerName" value="" /></th>
        </tr>
        <tr>
            <th>Region</th>
            <th><input type="text" name="region" value="" /></th>
        </tr>
    </table>

        <select name="stat"  size="4"  required>
            <option value="matchhistory">Match History</option>
            <option value="rankedstats">Ranked Stats</option>
            <option value="runesmasteries">Runes/Masteries</option>
            <option value="championmasteries">Champion Masteries</option>

        </select>



<input type="reset" value="Clear" name="clear">
<input type="submit" value="Search" name="submit">
<input type="button" value="Return to Profile" onclick=window.location.href='Profile';>

</form>

</body>
</html>
