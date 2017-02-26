

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recentSearch extends HttpServlet {

    ArrayList <String> history;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
         history = (ArrayList) request.getSession().getAttribute("list");
        if (username == null) {
            response.sendRedirect("/login");
        } else {
            out.write("<head>");
            out.write("<title>Searches</title>");
            out.write("</head>");
            out.write("<body>");
            out.write("<h1>Recently Searched</h1>");
            out.write("<p>" + history);
            out.write("</p>");
            out.write("<input type=\"submit\" value=\"Search Another Summoner\" onclick=\"window.location.href='/findSum';\">");
            out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");
            out.write("</body>");
        }

    }
}
