import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class results extends HttpServlet {

    String URL="https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/VeryAngryPig?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
    ArrayList<String> list = new ArrayList<String>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            String username = (String) request.getSession().getAttribute("username");


            String x = "nornorlei";
            String y = "na";


            String sumName = request.getParameter("summonerName");
            String region = request.getParameter("region");

            if(username == null) {
                response.sendRedirect("/login");
            }else {
                if((sumName.equals(x)) && (region.equalsIgnoreCase(y))){
                    list.add(sumName + " " + region.toUpperCase());
                }
                request.getSession().setAttribute("list",list);
                PrintWriter out = response.getWriter();
                out.write("<head>");
                out.write("<title>Player Stats</title>");
                out.write("</head>");
                out.write("<body>");
                out.write("<form name=\"adf\" action=\"recentSearch\" method=\"GET\">\n");
                out.write("<input type=\"submit\" value=\"Recently Searched\" name=\"submit\">");
                out.write("<input type=\"button\" value=\"Another Search\" onclick=\"window.location.href='/findSum';\"/>");
                out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");
                out.write("<h1>Summoner " + sumName + " from " + region.toUpperCase() +  "</h1>");
                out.write("<p>Rank: "+ "                    " + "points: </p>");

                out.write("<div>");
                    out.write("<ul>");
                    out.write("<li><a href=\"#matchhistory\"> Match History </a></li>");
                    out.write("<li><a href=\"#rankstats\"> Ranked Stats </a></li>");
                    out.write("<li><a href=\"#runesnmasteries\"> Runes & Masteries </a></li>");
                    out.write("<li><a href=\"#champmasteries\"> Champion Masteries </a></li>");
                    out.write("</ul>");

                    out.write("<div>");

                        out.write("<div id=\"matchhistory\">");
                        out.write("<h2><font size=\"12\">MATCH HISTORY</font></h2>");
                        out.write("<p>");
                        out.write("</p>");
                        out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");
                        out.write("</div>");

                        out.write("<div id=\"rankstats\">");
                        out.write("<h3><font size=\"12\">RANKED STATS </font></h3>");
                        out.write("<p>");
                       out.write("</p>");
                out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");
                        out.write("</div>");

                        out.write("<div id=\"runesnmasteries\">");
                        out.write("<h4><font size=\"12\">RUNES AND MASTERIES </font></h4>");
                        out.write("<p>");
                        out.write("</p>");
                out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");
                        out.write("</div>");

                        out.write("<div id=\"champmasteries\">");
                        out.write("<h5><font size=\"12\">CHAMPION MASTERIES</font></h5>");
                        out.write("<p>");
                        out.write("</p>");
                out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");out.write("<p> g </p>");
                        out.write("</div>");

                    out.write("</div>");
                out.write("</div>");

                out.write("<input type=\"submit\" value=\"Recently Searched\" name=\"submit\">");
                out.write("<input type=\"button\" value=\"Another Search\" onclick=\"window.location.href='/findSum';\"/>");
                out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");
                out.write("</body>");
            }


    
}

        }