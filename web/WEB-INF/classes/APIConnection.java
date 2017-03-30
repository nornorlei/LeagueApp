
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.json.*;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;


public class APIConnection{

    private String api = "RGAPI-52269a2a-0944-4223-bbb2-39dd1d381a12";

    public void findSummoner(String summoner, String region){
        String s = "https://" + region.toLowerCase() + ".api.riotgames.com/api/lol/" + region.toUpperCase() +"/v1.4/summoner/by-name/"+ summoner + "?api_key=" + api;
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;

        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            System.out.println(display);
        }
    }
    public String getSummonerID (String name, String region) {
        String s = "https://" + region + ".api.riotgames.com/api/lol/" + region.toUpperCase() + "/v1.4/summoner/by-name/" + name + "?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";

        String summonerID = null;
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if (connection != null){
            scanner = getConnectionScanner(connection);
            if (scanner != null) {
                String display = "";
                while (scanner.hasNextLine()) {
                    display = display.concat(scanner.nextLine());
                }
                try {
                    JSONObject json = new JSONObject(display);
                    JSONObject sum = json.getJSONObject(name);
                    Integer id = sum.getInt("id");
                    System.out.println(json);
                    System.out.println(sum);
                    System.out.println(id);

                    summonerID = id.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        return summonerID;
    }

    public JSONArray getChampMastery (String summonerID, String region){
         String s = "https://" + region + ".api.riotgames.com/championmastery/location/" + region + "/player/" + summonerID + "/champions?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
         String ss = "https://" + region + ".api.riotgames.com/championmastery/location/" + region + "1/player/" + summonerID + "/champions?api_key=RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
         JSONArray array = null;
         if ((region.equalsIgnoreCase("kr")) || (region.equalsIgnoreCase("ru"))) {
             HttpURLConnection connection = getConnection(s);
             Scanner scanner = null;
             if (connection != null) {
                 scanner = getConnectionScanner(connection);
                 if (scanner != null) {
                     String display = "";
                     while (scanner.hasNextLine()) {
                         display = display.concat(scanner.nextLine());
                     }
                     try {
                         array = new JSONArray(display);
                         System.out.println(array);
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                 }
             }

         }else{
             HttpURLConnection connection = getConnection(ss);
             Scanner scanner = null;
             if (connection != null) {
                 scanner = getConnectionScanner(connection);
                 if (scanner != null) {
                     String display = "";
                     while (scanner.hasNextLine()) {
                         display = display.concat(scanner.nextLine());
                     }
                     try {
                         array = new JSONArray(display);
                         System.out.println(array);
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                 }
             }

         }
             return array;


    }
    public static void main(String [] args) {
        APIConnection api = new APIConnection();
        System.out.println(api.getChampMastery("1135567","kr"));
    }





    public void fillChamps(){
        String s = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/champion?champData=image&api_key=RGAPI-52269a2a-0944-4223-bbb2-39dd1d381a12";
        HttpURLConnection connection = getConnection(s);
        Scanner scanner = null;
        if(connection != null)
            scanner = getConnectionScanner(connection);
        if(scanner != null){
            String display = "";
            while(scanner.hasNextLine()) {
                display = display.concat(scanner.nextLine());
            }
            try{
                String [] champs = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe","AurelionSol","Azir","Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Camille", "Cassiopeia", "Chogath", "Corki", "Darius", "Diana", "DrMundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks", "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Illaoi", "Irelia", "Ivern", "Janna", "JarvanIV", "Jax", "Jayce",  "Jhin", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin", "Katarina", "Kayle", "Kennen", "Khazix", "Kindred", "Kled", "KogMaw", "Leblanc", "LeeSin", "Leona", "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "MasterYi", "MissFortune", "Mordekaiser", "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Pantheon", "Poppy", "Quinn", "Rammus", "RekSai", "Renekton", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani", "Shaco", "Shen", "Shyvana", "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "TahmKench", "Taliyah", "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "TwistedFate", "Twitch", "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Velkoz", "Vi", "Viktor", "Vladimir", "Volibear", "Warwick", "MonkeyKing", "Xerath", "XinZhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"};
                System.out.println(champs.length + "length");
                JSONObject json = new JSONObject(display);
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = new DBConnection().getconnection();
                JSONObject data = json.getJSONObject("data");
                //Connection conn = new DBConnection().getconnection();
                for (String champ : champs) {
                    JSONObject champion = data.getJSONObject(champ);
                    String name = champion.getString("name").toString();
                    String title = champion.getString("title").toString();
                    JSONObject imageData = champion.getJSONObject("image");
                    String image = imageData.getString("full").toString();
                    int id = champion.getInt("id");
                    // System.out.println("name " + name + " " + title + " " + id + " " + image);
                    String query = "select * from Champions where name ='" + name + "'";
                    Statement statement = conn.createStatement();
                    ResultSet champResults = statement.executeQuery(query);

                    if(champResults.next()){
                    }
                    else{
                        if(name.contains("'")){
                            name.replace("'", "\'");
                            System.out.println(name);
                        }else if(title.contains("'")){
                            title.replace("'", "\'");
                        }
                        PreparedStatement statementChamp = null;
                        statementChamp = conn.prepareStatement("insert into Champions values (?,?,?,?)");
                        statementChamp.setInt(1, id);
                        statementChamp.setString(2, name);
                        statementChamp.setString(3, title);
                        statementChamp.setString(4, image);
                        statementChamp.execute();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public HttpURLConnection getConnection(String string){
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(string);
        } catch (MalformedURLException e) {
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

