import java.io.FileReader;
import java.lang.String;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GenerationGraphe {
    private static JSONParser parser = new JSONParser();

    public static Reseau generer() {
        String filePath = "";
        Reseau r = null;
        try (FileReader fichier = new FileReader(ClassLoader.getSystemResource(filePath).getFile()))
        {
            JSONObject jsonObject = (JSONObject) parser.parse(fichier);
            JSONArray routeurs = (JSONArray) jsonObject.get("routeur");
            r = new Reseau(20);

            Iterator routeurArray = routeurs.iterator();
            while(routeurArray.hasNext()) r.addRouteur((String) routeurArray.next());

            JSONArray arrayConnexion = (JSONArray) jsonObject.get("connexion");
            Iterator arrayConnexionIterator = arrayConnexion.iterator();
            while(arrayConnexionIterator.hasNext()){
                JSONArray connexion = (JSONArray) arrayConnexionIterator.next();
                if(connexion.size() == 3)
                    for (int j = 0; j < 3; j++)
                        r.ajouterConnexion((String) connexion.get(0),(String) connexion.get(1),(int)connexion.get(2));
                else if(connexion.size() == 4)
                    for (int j = 0; j < 4; j++)
                        System.out.println("pas encore implémenté");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}