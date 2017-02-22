import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class results extends HttpServlet {
    
    int count = 0;
    String URL="https://eune.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/VeryAngryPig?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            String username = (String) request.getSession().getAttribute("username");

            ArrayList<String> list = new ArrayList<String>();
            String x = "nornorlei";
            String y = "na";
            request.setAttribute("list",list);
            String sumName = request.getParameter("summonerName");
            String region = request.getParameter("region");

            if(username == null) {
                response.sendRedirect("/login");
            }else {
                if((sumName.equals(x)) && (region.equalsIgnoreCase(y))){
                    list.add(count,sumName + " " + region.toUpperCase());
                }
                PrintWriter out = response.getWriter();
                out.write("<head>");
                out.write("<title>Player Stats</title>");
                out.write("</head>");
                out.write("<body>");
                out.write("<form name=\"adf\" action=\"recentSearch\" method=\"POST\">\n");
                out.write("<h1>Summoner " + sumName + " from " + region.toUpperCase() +  "</h1>");
                out.write("<input type=\"submit\" value=\"Recently Searched\" name=\"submit\">");
                out.write("<input type=\"button\" value=\"Another Search\" onclick=\"window.location.href='/findSum';\"/>");
                out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");
                out.write("</body>");
            }


    
}

        }