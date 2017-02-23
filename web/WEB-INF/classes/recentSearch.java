

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recentSearch extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            response.sendRedirect("/login");
        } else {
            out.write("<head>");
            out.write("<title>Searches</title>");
            out.write("</head>");
            out.write("<body>");
            out.write("<h1>Recently Searched</h1>");

            int x = (int) request.getSession().getAttribute("count");
            if(request.getSession().getAttribute(("s").concat(Integer.toString(x))) != null){
                for (int i = 0; i < x +1 ; i++) {
                    out.write("<p> Summoner + Region:   " + request.getSession().getAttribute(("s").concat(Integer.toString(i))) + "</p>");
                }
            }
            out.write("<p>");
            out.write("</p>");
            out.write("<input type=\"submit\" value=\"Search Another Summoner\" onclick=\"window.location.href='/findSum';\">");
            out.write("<input type=\"button\" value=\"Return to Profile\" onclick=\"window.location.href='/Profile';\"/>");
            out.write("</body>");
        }

    }
}
