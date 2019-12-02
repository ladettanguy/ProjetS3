import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GénérationGraphe {
    public static Reseau generer(String mode) {
        JSONParser parser = new JSONParser();
        String filePath = "/home/tamikata/IdeaProjects/ProjetS3/Reseau.json";

        try (FileReader fichier = new FileReader(ClassLoader.getSystemResource(filePath).getFile())) {

            JSONObject jsonObject = (JSONObject) parser.parse(fichier);

            JSONObject s = (JSONObject) jsonObject.get(mode);

            JSONArray routeurs = (JSONArray) s.get("routeur");
            Reseau r = new Reseau(20);
            Iterator i = routeurs.iterator();
            while(i.hasNext()){

            }
            System.out.println(s);
            /*System.out.println("1\n");

            JSONObject reseauJson = (JSONObject) obj;

            System.out.println("\n");

            JSONObject s = (JSONObject) reseauJson.get(mode);

            System.out.println(s+"\n");

            // loop array
            JSONArray cars = (JSONArray) reseauJson.get("Routeur");
            Iterator<String> iterator = cars.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}