

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class recentSearch extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //
        String summoner = request.getParameter("summonerName");
        String region = request.getParameter("region");

        try {
            Connection conn = new DBConnection().getconnection();
            PreparedStatement statement = null;

            statement = conn.prepareStatement("INSERT INTO RecentSearches VALUES (?,?)");
            statement.setString(1, summoner);
            statement.setString(2, region);
            statement.executeQuery();
        }catch (SQLException e){
            System.out.println(e);
        }
        response.sendRedirect("/login");
//meow

    }
}
