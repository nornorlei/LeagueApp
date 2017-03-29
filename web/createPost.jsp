<%--
  Created by IntelliJ IDEA.
  User: norman
  Date: 3/7/2017
  Time: 10:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Build</title>
</head>
<body>
<h1>Create your build</h1>
<form action="/createPost" method="POST" >
    <select name="champ"  size="10"  required>
        <option value="Aatrox">Aatrox</option>
        <option value="Ahri">Ahri</option>
        <option value="Akali">Akali</option>
        <option value="Alistar">Alistar</option>
        <option value="Amumu">Amumu</option>
        <option value="Ashe">Ashe</option>
        <option value="AurelionSol">Aurelion Sol</option>
        <option value="Azir">Azir</option>
        <option value="Bard">Bard</option>
        <option value="Blitzcrank">Blitzcrank</option>
        <option value="Brand">Brand</option>
        <option value="Braum">Braum</option>
        <option value="Caitlyn">Caitlyn</option>
        <option value="Camille">Camille</option>
        <option value="Cassiopeia">Cassiopeia</option>
        <option value="Chogath">Cho'Gath</option>
        <option value="Corki">Corki</option>
        <option value="Darius">Darius</option>
        <option value="Diana">Diana</option>
        <option value="DrMundo">Dr.Mundo</option>
        <option value="Draven">Draven</option>
        <option value="Ekko">Ekko</option>
        <option value="Elise">Elise</option>
        <option value="Evelynn">Evelynn</option>
        <option value="Ezreal">Ezreal</option>
        <option value="Fiddlesticks">Fiddlesticks</option>
        <option value="Fiora">Fiora</option>
        <option value="Fizz">Fizz</option>
        <option value="Galio">Galio</option>
        <option value="Gangplank">Gangplank</option>
        <option value="Garen">Garen</option>
        <option value="Gnar">Gnar</option>
        <option value="Gragas">Gragas</option>
        <option value="Graves">Graves</option>
        <option value="Hecarim">Hecarim</option>
        <option value="Heimerdinger">Heimerdinger</option>
        <option value="Illaoi">Illaoi</option>
        <option value="Irelia">Irelia</option>
        <option value="Ivern">Ivern</option>
        <option value="Janna">Janna</option>
        <option value="JarvenIV">Jarvan IV</option>
        <option value="Jax">Jax</option>
        <option value="Jayce">Jayce</option>
        <option value="Jhin">Jhin</option>
        <option value="Jinx">Jinx</option>
        <option value="Kalista">Kalista</option>
        <option value="Karma">Karma</option>
        <option value="Karthus">Karthus</option>
        <option value="Kassadin">Kassadin</option>
        <option value="Katarina">Katarina</option>
        <option value="Kayle">Kayle</option>
        <option value="Kennen">Kennen</option>
        <option value="Khazix">Khazix</option>
        <option value="Kindred">Kindred</option>
        <option value="Kled">Kled</option>
        <option value="KogMaw">Kog'Maw</option>
        <option value="Leblanc">LeBlanc</option>
        <option value="LeeSin">Lee Sin</option>
        <option value="Leona">Leona</option>
        <option value="Lissandra">Lissandra</option>
        <option value="Lucian">Lucian</option>
        <option value="Lulu">Lulu</option>
        <option value="Lux">Lux</option>
        <option value="Malphite">Malphite</option>
        <option value="Malzahar">Malzahar</option>
        <option value="Maokai">Maokai</option>
        <option value="MasterYi">MasterYi</option>
        <option value="MissFortune">MissFortune</option>
        <option value="Mordekaiser">Mordekaiser</option>
        <option value="Morgana">Morgana</option>
        <option value="Nami">Nami</option>
        <option value="Nasus">Nasus</option>
        <option value="Nautilus">Nautilus</option>
        <option value="Nidalee">Nidalee</option>
        <option value="Nocturne">Nocturne</option>
        <option value="Nunu">Nunu</option>
        <option value="Olaf">Olaf</option>
        <option value="Orianna">Orianna</option>
        <option value="Pantheon">Pantheon</option>
        <option value="Poppy">Poppy</option>
        <option value="Quinn">Quinn</option>
        <option value="Rammus">Rammus</option>
        <option value="RekSai">Rek'Sai</option>
        <option value="Renekton">Renekton</option>
        <option value="Rengar">Rengar</option>
        <option value="Riven">Riven</option>
        <option value="Rumble">Rumble</option>
        <option value="Ryze">Ryze</option>
        <option value="Sejuani">Sejuani</option>
        <option value="Shaco">Shaco</option>
        <option value="Shen">Shen</option>
        <option value="Shyvana">Shyvana</option>
        <option value="Singed">Singed</option>
        <option value="Sion">Sion</option>
        <option value="Sivir">Blitzcrank</option>
        <option value="Skarner">Blitzcrank</option>
        <option value="Sona">Sona</option>
        <option value="Soraka">Soraka</option>
        <option value="Swain">Swain</option>
        <option value="Syndra">Syndra</option>
        <option value="TahmKench">Tahm Kench</option>
        <option value="Taliyah">Blitzcrank</option>
        <option value="Talon">Blitzcrank</option>
        <option value="Taric">Taric</option>
        <option value="Teemo">Teemo</option>
        <option value="Thresh">Thresh</option>
        <option value="Tristana">Tristana</option>
        <option value="Trundle">Trundle</option>
        <option value="Tryndamere">Trynadamere</option>
        <option value="TwistedFate">TwistedFate</option>
        <option value="Twitch">Twitch</option>
        <option value="Udyr">Udyr</option>
        <option value="Urgot">Urgot</option>
        <option value="Varus">Varus</option>
        <option value="Vayne">Vayne</option>
        <option value="Veigar">Veigar</option>
        <option value="Velkoz">Velkoz</option>
        <option value="Vi">Vi</option>
        <option value="Viktor">Viktor</option>
        <option value="Vladimir">Vladimir</option>
        <option value="Volibear">Volibear</option>
        <option value="Warwick">Warwick</option>
        <option value="MonkeyKing">MonkeyKing</option>
        <option value="Xerath">Xerath</option>
        <option value="Xinzhao">Xin Zhao</option>
        <option value="Yasuo">Yasuo</option>
        <option value="Yorick">Yorick</option>
        <option value="Zac">Zac</option>
        <option value="Zed">Zed</option>
        <option value="Ziggs">Ziggs</option>
        <option value="Zilean">Zilean</option>
        <option value="Zyra">Zyra</option>
    </select>

    <input tpye="text" name="post_name" placeholder="post name">
    <input type="text" name="ss1" placeholder="Summoner Skill 1">
    <input type="text" name="ss2" placeholder="Summoner skill 2">
    <input type="text" name="marks" placeholder="Marks">
    <input type="text" name="seals" placeholder="Seals">
    <input type="text" name="glyphs" placeholder="Glyphs">
    <input type="text" name="quints" placeholder="Quints">
    <input type="text" name="i1" placeholder="Item 1">
    <input type="text" name="i2" placeholder="Item 2">
    <input type="text" name="i3" placeholder="Item 3">
    <input type="text" name="i4" placeholder="Item 4">
    <input type="text" name="i5" placeholder="Item 5">
    <input type="text" name="i6" placeholder="Item 6">
    <input type="text" name="ability_sequence" placeholder="Ability Sequence">
    <input type="text" name="masteries" placeholder="Masteries">

    <input type="submit" value="Submit" name="submit">
    <input type="button" value="Return to Profile" onclick=window.location.href='/Profile';>


</form>
</body>
</html>
