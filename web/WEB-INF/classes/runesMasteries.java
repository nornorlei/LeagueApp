import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class runesMasteries extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sumName = req.getRequestDispatcher("sumName").toString();
        String region = req.getRequestDispatcher("region").toString();
        String stat = req.getRequestDispatcher("stat").toString();
        System.out.println("runes and masteries");
        System.out.println("sum " + sumName + " region " + region + " stat " + stat);

        req.getRequestDispatcher("runeMasteries.jsp").forward(req, resp);
    }
}
