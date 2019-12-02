import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class GenerationGraphe {
    private static JSONParser parser = new JSONParser();

    public static Reseau generer() {
        String filePath = "/home/tamikata/IdeaProjects/ProjetS3/Reseau.json";
        Reseau r = null;
        try
        {
            FileReader file = new FileReader("/home/tamikata/IdeaProjects/ProjetS3/Reseau.json");
            JSONObject jsonObject = (JSONObject) parser.parse(file);
            JSONArray routeurs = (JSONArray) jsonObject.get("routeur");
            r = new Reseau(20);
            for (Object routeur: routeurs) r.addRouteur((String) routeur);
            JSONArray arrayConnexion = (JSONArray) jsonObject.get("connexion");

            Iterator arrayConnexionIterator = arrayConnexion.iterator();
            while(arrayConnexionIterator.hasNext()) {
                JSONArray connexion = (JSONArray) arrayConnexionIterator.next();
                if (connexion.size() == 3)
                    for (int j = 0; j < 3; j++)
                        r.ajouterConnexion((String) connexion.get(0), (String) connexion.get(1), (int) ((long) connexion.get(2)));
                else if (connexion.size() == 4)
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