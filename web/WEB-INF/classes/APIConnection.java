import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APIConnection {


    private String apiKey = "RGAPI-654f4896-0393-46cc-9043-9e078860ed31";
    //private String region = "na";
    //private String summoner = "nornorlei";

    public static void main(String[] args) {

        //new APIConnection().makeRequest("nornorlei","na");
    }

    public boolean makeRequest(String summoner, String region) {
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
                JsonObject jsonObject = new JsonParser().parse(display).getAsJsonObject();
                JsonObject objects = jsonObject.get(summoner).getAsJsonObject();
                System.out.println(objects.get("id").getAsString());
                System.out.println(objects.get("name").getAsString());
                System.out.println(objects.get("summonerLevel").getAsString());


            }
            return true;
        }else{
            return false;
        }
    }

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
