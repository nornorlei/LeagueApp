import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String x = "right";   //will connect this to the data base later on
        String y = "left";   //will connect this to the data base later on

        out.write("<html>");
        out.write("<head>");
        out.write("<title>Home Page</title>");
        out.write("</head>");
        out.write("<body>");
        out.write("<form action=\"login\" method=\"GET\">\n");
        out.write("<h1>Please login</h1>");
        out.write("<p>Enter user name and password</p>");
        out.write("<table>");
        out.write("<tr>");
        out.write("<th>Username</th>");
        out.write("<th><input type=\"text\" name=\"username\" value=\"\" /></th>");
        out.write("</tr>");
        out.write("<tr>");
        out.write("<th>Password</th>");
        out.write("<th><input type=\"text\" name=\"password\" value=\"\" /></th>");
        out.write("</tr>");
        out.write("</table>");
        out.write("<input type=\"reset\" value=\"Clear\" name=\"clear\">");
        out.write("<input type=\"submit\" value=\"Login\" name=\"submit\">");
        out.write("<input type=\"button\" value=\"Signup\" onclick=\"window.location.href='/';\"/>");
        out.write("</body>");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if((username.equals(x)) && (password.equals(y))){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect("Profile");
        }
        else{
            response.sendRedirect("/login");
        }
    }
}
