/"ƒ†<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Player Stat</title>
</head>
<body>
    <form name=adf action=recentSearch method=POST>
    <input type=submit value="Recently Searched" name=submit>
    <input type=button value="Another Search" onclick=window.location.href='findSum';>
    <input type=button value="Return to Profile" onclick=window.location.href='Profile';>
    <h1>Summoner <%=request.getParameter("summonerName")%> from <%=request.getParameter("region")%> </h1>
    <p>Rank:                  points: </p>

    <div>
        <ul>
            <li><a href=#matchhistory> Match History </a></li>
            <li><a href=#rankstats> Ranked Stats </a></li>
            <li><a href=#runesnmasteries> Runes & Masteries </a></li>
            <li><a href=#champmasteries> Champion Masteries </a></li>
            </ul>

        <div>

            <div id=matchhistory>
                <h2><font size=12>MATCH HISTORY</font></h2>
            </div>

            <div id=rankstats>
                <h3><font size=12>RANKED STATS </font></h3>
                    <p></p>
            </div>

            <div id=runesnmasteries>
                <h4><font size=12>RUNES AND MASTERIES </font></h4>
                    <p></p>
            </div>

            <div id=champmasteries>
                <h5><font size=12>CHAMPION MASTERIES</font></h5>
            </div>

        </div>
    </div>

    <input type=submit value="Recently Searched" name= submit>
    <input type=button value="Another Search" onclick=window.location.href='findSum.jsp';>
    <input type=button value="Return to Profile" onclick=window.location.href='Profile.jsp';>
</form>
</body>
</html>
