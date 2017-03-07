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
    <form action="/createPost" method="POST">
        <select name="champ"  size="10"  required>
            <option value="aatrox">Aatrox</option>
            <option value="ahri">Ahri</option>
            <option value="akali">Akali</option>
            <option value="alistar">Alistar</option>
            <option value="amumu">Amumu</option>
            <option value="ashe">Ashe</option>
            <option value="aurelionsol">Aurelion Sol</option>
            <option value="azir">Azir</option>
            <option value="bard">Bard</option>
            <option value="blitzcrank">Blitzcrank</option>
            <option value="caitlyn">Caitlyn</option>
            <option value="camille">Camille</option>
            <option value="cassiopeia">Cassiopeia</option>
            <option value="chogath">Cho'Gath</option>
            <option value="corki">Corki</option>
            <option value="drmundo">Dr.Mundo</option>
            <option value="draven">Draven</option>
            <option value="ekko">Ekko</option>
            <option value="elise">Elise</option>
            <option value="evelynn">Evelynn</option>
            <option value="fiora">Fiora</option>
            <option value="fizz">Fizz</option>
            <option value="galio">Galio</option>
            <option value="gangplank">Gangplank</option>
            <option value="garen">Garen</option>
            <option value="graves">Graves</option>
            <option value="hecarim">Hecarim</option>
            <option value="heimerdinger">Heimerdinger</option>
            <option value="illaoi">Illaoi</option>
            <option value="irelia">Irelia</option>
            <option value="jarveniv">Jarvan IV</option>
            <option value="jax">Jax</option>
            <option value="jayce">Jayce</option>
            <option value="jhin">Jhin</option>
            <option value="jinx">Jinx</option>
            <option value="karthus">Karthus</option>
            <option value="kassadin">Kassadin</option>
            <option value="katarina">Katarina</option>
            <option value="kayle">Kayle</option>
            <option value="kennen">Kennen</option>
            <option value="kled">Kled</option>
            <option value="kogmaw">Kog'Maw</option>
            <option value="leblanc">LeBlanc</option>
            <option value="leesin">Lee Sin</option>
            <option value="leona">Leona</option>
            <option value="lulu">Lulu</option>
            <option value="lux">Lux</option>
            <option value="malphite">Malphite</option>
            <option value="malzahar">Malzahar</option>
            <option value="maokai">Maokai</option>
            <option value="mordekaiser">Mordekaiser</option>
            <option value="morgana">Morgana</option>
            <option value="nami">Nami</option>
            <option value="nasus">Nasus</option>
            <option value="nautilus">Nautilus</option>
            <option value="nunu">Nunu</option>
            <option value="olaf">Olaf</option>
            <option value="orianna">Orianna</option>
            <option value="pantheon">Pantheon</option>
            <option value="poppy">Poppy</option>
            <option value="reksai">Rek'Sai</option>
            <option value="renekton">Renekton</option>
            <option value="rengar">Rengar</option>
            <option value="riven">Riven</option>
            <option value="rumble">Rumble</option>
            <option value="shaco">Shaco</option>
            <option value="shen">Shen</option>
            <option value="shyvana">Shyvana</option>
            <option value="singed">Singed</option>
            <option value="sion">Sion</option>
            <option value="sona">Sona</option>
            <option value="soraka">Soraka</option>
            <option value="swain">Swain</option>
            <option value="syndra">Syndra</option>
            <option value="tahmkench">Tahm Kench</option>
            <option value="taric">Taric</option>
            <option value="teemo">Teemo</option>
            <option value="thresh">Thresh</option>
            <option value="tristana">Tristana</option>
            <option value="trundle">Trundle</option>
            <option value="twitch">Twitch</option>
            <option value="udyr">Udyr</option>
            <option value="urgot">Urgot</option>
            <option value="varus">Varus</option>
            <option value="vayne">Vayne</option>
            <option value="vi">Vi</option>
            <option value="viktor">Viktor</option>
            <option value="vladimir">Vladimir</option>
            <option value="volibear">Volibear</option>
            <option value="warwick">Warwick</option>
            <option value="xinzhao">Xin Zhao</option>
            <option value="yasuo">Yasuo</option>
            <option value="yorick">Yorick</option>
            <option value="zac">Zac</option>
            <option value="zed">Zed</option>
            <option value="zyra">Zyra</option>
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
        <input type="text" name="i6" placeholder="Item 5">
        <input type="text" name="ability_sequence" placeholder="Ability Sequence">
        <input type="text" name="masteries" placeholder="Masteries">

        <input type="submit" value="Submit" name="submit">
        <input type="button" value="Return to Profile" onclick=window.location.href='/Profile';>


    </form>
</body>
</html>
