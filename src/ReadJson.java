import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));
        file.put("Eye Color", "Brown");
        file.put("Year Born", new Integer(2007));
        file.put("Mass", new Integer(180) + "lbs");

        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        System.out.println(file.get("Full Name"));
        System.out.println(file.get("Year Born"));
        System.out.println(file.get("Eye Color"));
        System.out.println(file.get("Mass"));

        pull();

    }

    public static void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {
            //   https://pokeapi.co/api/v2/pokemon/ditto
            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonArray);

        try {

            int f = jsonArray.size();
            for (int z = 0; z < f; ++z) {
                JSONObject gaga = (JSONObject) jsonArray.get(z);


                String name = (String) gaga.get("name");

                org.json.simple.JSONArray msg = (org.json.simple.JSONArray) gaga.get("allies");

                int n = msg.size(); //(msg).length();

                for (int i = 0; i < n; ++i) {
                    String test = (String) msg.get(i);
                    System.out.println("My allies are " + test);


                    // System.out.println(person.getInt("key"));
                }
                org.json.simple.JSONArray smg = (org.json.simple.JSONArray) gaga.get("enemies");

                int a = smg.size(); //(msg).length();

                for (int i = 0; i < a; ++i) {
                    String test = (String) smg.get(i);
                    System.out.println(test);
                }
//            org.json.simple.JSONArray aff = (org.json.simple.JSONArray) gaga.get("affiliation");
//
//            int w = aff.size(); //(msg).length();
//
//            for (int i = 0; i < w; ++i) {
//                String test = (String) aff.get(i);
//                System.out.println(test);
//            }

                //     String spg = (String) gaga.get("gender");


                //   System.out.println(spg);


                // System.out.println(person.getInt("key"));

                //  org.json.simple.JSONArray sag = (org.json.simple.JSONArray) gaga.get("hair");

                //   int q = sag.size(); //(msg).length();

                // for (int i = 0; i < q; ++i) {
                //      String test = (String) sag.get(i);
                //    System.out.println(test);


                // System.out.println(person.getInt("key"));
                //   }
                String height = (String) gaga.get("height");
                System.out.println("My name is " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


