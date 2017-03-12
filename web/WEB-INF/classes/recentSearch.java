

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

    ArrayList <String> recentSearch = new ArrayList();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the values from results.jsp  including summoner name, region, + maybe a redirect?
        // organize the data too
    }
}
