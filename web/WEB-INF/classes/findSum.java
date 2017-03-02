import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class findSum extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = (String) request.getSession().getAttribute("username");
        PrintWriter out = response.getWriter();


        if (user != null) {

            out.write("<head>");
            out.write("<title>Home Page</title>");
            out.write("</head>");
            out.write("<form action=\"results.jsp\">");
            out.write("<body>");
            out.write("<h1>Find Summoner</h1>");
            out.write("<p>Please enter a summoner name and respective region</p>");
            out.write("<table>");
            out.write("<tr>");
            out.write("<th>Summoner Name</th>");
            out.write("<th><input type=\"text\" name=\"summonerName\" value=\"\" /></th>");
            out.write("</tr>");
            out.write("<tr>");
            out.write("<th>Region</th>");
            out.write("<th><input type=\"text\" name=\"region\" value=\"\" /></th>");
            out.write("</tr>");
            out.write("</table>");
            out.write("<input type=\"reset\" value=\"Clear\" name=\"clear\">");
            out.write("<input type=\"submit\" value=\"Search\" name=\"submit\">");
            out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");
            out.write("</body>");



        }
        else {
            response.sendRedirect("/login");
        }
    }
}
