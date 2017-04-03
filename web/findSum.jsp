<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Summoner</title>
</head>
<body>
<h1>Find Summoner</h1>

<form action="/findSum" method="POST">
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

    <select name="stat" required>
        <option value="championMasteries">Champion Masteries</option>
        <option value="rankedStats">Ranked Stats</option>
        <option value="runes">Runes</option>
        <option value="masteries">Masteries</option>
    </select>

<input type="reset" value="Clear" name="clear">
<input type="submit" value="Search" name="submit">
<input type="button" value="Return to Profile" onclick=window.location.href='/Profile';>

</form>
</body>
</html>
