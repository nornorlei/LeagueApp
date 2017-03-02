import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class champResult extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");
        String champ = (String) request.getSession().getAttribute("champSearch");
        if (username == null) {
            response.sendRedirect("/login");
        } else {
            out.write("<head>");
            out.write("<title>" + champ + "</title>");
            out.write("</head>");
            out.write("<body>");
            out.write("<h1><font size=\"18\">"+ champ +"</font></h1>");
            out.write("</body>");
        }

    }
}
