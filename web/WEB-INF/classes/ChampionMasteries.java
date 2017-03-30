import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class ChampionMasteries extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchName = (String) request.getSession().getAttribute("summonerName");

    }
//s
        private String apiKey = "RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
        //private String region = "na";
        //private String summoner = "nornorlei";
        private String gotSumName;
        //private String gotSumID;



//    public static void main(String[] args) {
//
//        new APIConnection().makeRequest("nornorlei","na");
//        APIConnection findSum = new APIConnection();
//        findSum.makeRequest("nornorlei","na");
//
//    }

        //asdasdasdasdadsadssa
        public void makeRunesRequest (String id, String region){
            String url = "https://" + region + ".api.pvp.net/api/lol/".concat(region).concat("/v1.4/summoner/").concat(id).concat("/runes?api_key=").concat(apiKey);
            System.out.println(url);
            HttpURLConnection connection = getConnection(url);

            if (connection != null) {
                Scanner scanner = getConnectionScanner(connection);

                if (scanner != null) {
                    String display = "";
                    while (scanner.hasNextLine()) {
                        display = display.concat(scanner.nextLine());
                    }

                    System.out.println("webpage display: " + display);

                    try {
                        Class.forName("com.google.gson.JsonObject");
                        Class.forName("com.google.gson.JsonParser");
                        JsonObject jsonObject = new JsonParser().parse(display).getAsJsonObject();
                        JsonObject objects = jsonObject.get(id).getAsJsonObject();

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = new DBConnection().getconnection();
                        PreparedStatement statement = conn.prepareStatement("update temp_runes_masteries set runes = (?) where num = 1");
                        //statement.setString(1, objects.get("runes").getAsString());    do this eventaully
                        statement.setString(1, display);
                        statement.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println(e);
                    } catch (ClassNotFoundException e) {
                        System.out.println(e);

                    }

                }

            }
        }

    public void makeMasteriesRequest(String id, String region) {
        String url = "https://" + region + ".api.pvp.net/api/lol/".concat(region).concat("/v1.4/summoner/").concat(id).concat("/masteries?api_key=").concat(apiKey);
        System.out.println(url);
        HttpURLConnection connection = getConnection(url);

        if (connection != null) {
            Scanner scanner = getConnectionScanner(connection);

            if (scanner != null) {
                String display = "";
                while (scanner.hasNextLine()) {
                    display = display.concat(scanner.nextLine());
                }

                System.out.println("webpage display: " + display);

                try {
                    Class.forName("com.google.gson.JsonObject");
                    Class.forName("com.google.gson.JsonParser");
                    JsonObject jsonObject = new JsonParser().parse(display).getAsJsonObject();
                    JsonObject objects = jsonObject.get(id).getAsJsonObject();

                    System.out.println("break 1 ");


                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = new DBConnection().getconnection();
                    PreparedStatement statement = conn.prepareStatement("insert into temp_runes_masteries values (?,?,?)");
                    //statement.setString(1, objects.get("masteries").getAsString());    do this eventaully
                    statement.setString(1, "1");
                    statement.setString(2, display);
                    statement.setString(3, "placer");

                    statement.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(e);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);

                }

            }

        }
    }

    public void clearTempMasteries() {
        try {
            Connection conn = new DBConnection().getconnection();
            PreparedStatement statement = conn.prepareStatement("delete from temp_runes_masteries");
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void makeRequest(String summoner, String region) {
        String url = "https://" + region + ".api.pvp.net/api/lol/".concat(region).concat("/v1.4/summoner/by-name/").concat(summoner).concat("?api_key=").concat(apiKey);
        System.out.println(url);
        HttpURLConnection connection = getConnection(url);

        if (connection != null) {
            Scanner scanner = getConnectionScanner(connection);

            if (scanner != null) {
                String display = "";
                while (scanner.hasNextLine()) {
                    display = display.concat(scanner.nextLine());
                }

                System.out.println("webpage display: " + display);

                //getting individual elements

                try {
                    System.out.println("breka 0");
                    Class.forName("com.google.gson.JsonObject");
                    Class.forName("com.google.gson.JsonParser");
                    JsonObject jsonObject = new JsonParser().parse(display).getAsJsonObject();
                    JsonObject objects = jsonObject.get(summoner).getAsJsonObject();

                    System.out.println("break 1 ");


                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = new DBConnection().getconnection();
                    PreparedStatement statement = conn.prepareStatement("insert into temp_variables values (?,?)");
                    statement.setString(1, objects.get("name").getAsString());
                    statement.setString(2, objects.get("id").getAsString());
                    statement.executeUpdate();

                    System.out.println("breal 2");

//                    System.out.println(objects.get("id").getAsString());
//
                    System.out.println("try " + objects.get("name").getAsString());
//                    System.out.println(objects.get("summonerLevel").getAsString());

                    //setGotSumID(objects.get("id").getAsString());
                    //System.out.println(getID());
                    //setGotSumName(objects.get("name").getAsString());
                    //System.out.println(getName());
                    System.out.println("break 3");
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);

                }


            }

        }
    }

    public void clearTempDB() {
        try {
            Connection conn = new DBConnection().getconnection();
            PreparedStatement statement = conn.prepareStatement("delete from temp_variables");
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void setGotSumID (String id){
//        this.gotSumID = id;
//    }
//
//    public String getID (){
//        return gotSumID;
//    }
//    public void setGotSumName (String n){
//        this.gotSumName = n;
//    }

//    public String getName (){
//        return gotSumName;
//    }


    private HttpURLConnection getConnection(String urlString) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            url = null;
            System.out.println("Invalid URL");
        }

        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
            } catch (IOException e) {
                System.out.println("Failed to get connection");
            }
        }

        return connection;
    }

    private Scanner getConnectionScanner(HttpURLConnection connection) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(connection.getInputStream());
        } catch (IOException e) {
            System.out.println("Couldn't get input stream");
        }

        return scanner;
    }

}
