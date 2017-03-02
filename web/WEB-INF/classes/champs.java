import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


// this is basically just a drop down menu page with all the champions

public class champs extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");



        if (username == null) {
            response.sendRedirect("/login");
        } else {
            out.write("<head>");
            out.write("<title>CHAMPION LOOKUP</title>");
            out.write("</head>");
            out.write("<h1>Please select a champion</h1>");
            out.write("<form action=\"champs\" method=\"GET\">\n");
            out.write("<select name=\"champ\"  size=\"10\"  required");
            out.write("<option value=\"aatrox\">Aatrox</option>");
            out.write("<option value=\"ahri\">Ahri</option>");
            out.write("<option value=\"akali\">Akali</option>");
            out.write("<option value=\"alistar\">Alistar</option>");
            out.write("<option value=\"amumu\">Amumu</option>");
            out.write("<option value=\"ashe\">Ashe</option>");
            out.write("<option value=\"aurelionsol\">Aurelion Sol</option>");
            out.write("<option value=\"azir\">Azir</option>");
            out.write("<option value=\"bard\">Bard</option>");
            out.write("<option value=\"blitzcrank\">Blitzcrank</option>");
            out.write("<option value=\"caitlyn\">Caitlyn</option>");
            out.write("<option value=\"camille\">Camille</option>");
            out.write("<option value=\"cassiopeia\">Cassiopeia</option>");
            out.write("<option value=\"chogath\">Cho'Gath</option>");
            out.write("<option value=\"corki\">Corki</option>");
            out.write("<option value=\"drmundo\">Dr.Mundo</option>");
            out.write("<option value=\"draven\">Draven</option>");
            out.write("<option value=\"ekko\">Ekko</option>");
            out.write("<option value=\"elise\">Elise</option>");
            out.write("<option value=\"evelynn\">Evelynn</option>");
            out.write("<option value=\"fiora\">Fiora</option>");
            out.write("<option value=\"fizz\">Fizz</option>");
            out.write("<option value=\"galio\">Galio</option>");
            out.write("<option value=\"gangplank\">Gangplank</option>");
            out.write("<option value=\"garen\">Garen</option>");
            out.write("<option value=\"graves\">Graves</option>");
            out.write("<option value=\"hecarim\">Hecarim</option>");
            out.write("<option value=\"heimerdinger\">Heimerdinger</option>");
            out.write("<option value=\"illaoi\">Illaoi</option>");
            out.write("<option value=\"irelia\">Irelia</option>");
            out.write("<option value=\"jarveniv\">Jarvan IV</option>");
            out.write("<option value=\"jax\">Jax</option>");
            out.write("<option value=\"jayce\">Jayce</option>");
            out.write("<option value=\"jhin\">Jhin</option>");
            out.write("<option value=\"jinx\">Jinx</option>");
            out.write("<option value=\"karthus\">Karthus</option>");
            out.write("<option value=\"kassadin\">Kassadin</option>");
            out.write("<option value=\"katarina\">Katarina</option>");
            out.write("<option value=\"kayle\">Kayle</option>");
            out.write("<option value=\"kennen\">Kennen</option>");
            out.write("<option value=\"kled\">Kled</option>");
            out.write("<option value=\"kogmaw\">Kog'Maw</option>");
            out.write("<option value=\"leblanc\">LeBlanc</option>");
            out.write("<option value=\"leesin\">Lee Sin</option>");
            out.write("<option value=\"leona\">Leona</option>");
            out.write("<option value=\"lulu\">Lulu</option>");
            out.write("<option value=\"lux\">Lux</option>");
            out.write("<option value=\"malphite\">Malphite</option>");
            out.write("<option value=\"malzahar\">Malzahar</option>");
            out.write("<option value=\"maokai\">Maokai</option>");
            out.write("<option value=\"mordekaiser\">Mordekaiser</option>");
            out.write("<option value=\"morgana\">Morgana</option>");
            out.write("<option value=\"nami\">Nami</option>");
            out.write("<option value=\"nasus\">Nasus</option>");
            out.write("<option value=\"nautilus\">Nautilus</option>");
            out.write("<option value=\"nunu\">Nunu</option>");
            out.write("<option value=\"olaf\">Olaf</option>");
            out.write("<option value=\"orianna\">Orianna</option>");
            out.write("<option value=\"pantheon\">Pantheon</option>");
            out.write("<option value=\"poppy\">Poppy</option>");
            out.write("<option value=\"reksai\">Rek'Sai</option>");
            out.write("<option value=\"renekton\">Renekton</option>");
            out.write("<option value=\"rengar\">Rengar</option>");
            out.write("<option value=\"riven\">Riven</option>");
            out.write("<option value=\"rumble\">Rumble</option>");
            out.write("<option value=\"shaco\">Shaco</option>");
            out.write("<option value=\"shen\">Shen</option>");
            out.write("<option value=\"shyvana\">Shyvana</option>");
            out.write("<option value=\"singed\">Singed</option>");
            out.write("<option value=\"sion\">Sion</option>");
            out.write("<option value=\"sona\">Sona</option>");
            out.write("<option value=\"soraka\">Soraka</option>");
            out.write("<option value=\"swain\">Swain</option>");
            out.write("<option value=\"syndra\">Syndra</option>");
            out.write("<option value=\"tahmkench\">Tahm Kench</option>");
            out.write("<option value=\"taric\">Taric</option>");
            out.write("<option value=\"teemo\">Teemo</option>");
            out.write("<option value=\"thresh\">Thresh</option>");
            out.write("<option value=\"tristana\">Tristana</option>");
            out.write("<option value=\"trundle\">Trundle</option>");
            out.write("<option value=\"twitch\">Twitch</option>");
            out.write("<option value=\"udyr\">Udyr</option>");
            out.write("<option value=\"urgot\">Urgot</option>");
            out.write("<option value=\"varus\">Varus</option>");
            out.write("<option value=\"vayne\">Vayne</option>");
            out.write("<option value=\"vi\">Vi</option>");
            out.write("<option value=\"viktor\">Viktor</option>");
            out.write("<option value=\"vladimir\">Vladimir</option>");
            out.write("<option value=\"volibear\">Volibear</option>");
            out.write("<option value=\"warwick\">Warwick</option>");
            out.write("<option value=\"xinzhao\">Xin Zhao</option>");
            out.write("<option value=\"yasuo\">Yasuo</option>");
            out.write("<option value=\"yorick\">Yorick</option>");
            out.write("<option value=\"zac\">Zac</option>");
            out.write("<option value=\"zed\">Zed</option>");
            out.write("<option value=\"zyra\">Zyra</option>");
            out.write("</select>");

            out.write("<input type=\"submit\" value=\"Submit\" name=\"submit\" >");
            out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");

            String x = request.getParameter("champ");
            request.getSession().setAttribute("champSearch",x);

            //testing
            if (x.equals("akali")){
                response.sendRedirect("/champResult");
            }


        }
    }
}