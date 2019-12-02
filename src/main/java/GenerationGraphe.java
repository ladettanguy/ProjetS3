import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class GenerationGraphe {
    private static JSONParser parser = new JSONParser();

    public static Reseau generer() {
        String filePath = "/home/tamikata/IdeaProjects/ProjetS3/Reseau.json";
        Reseau r = null;
        try
        {
            //Lire le fichier JSON
            FileReader file = new FileReader(filePath);
            JSONObject jsonObject = (JSONObject) parser.parse(file);
            JSONArray routeurs = (JSONArray) jsonObject.get("routeur");
            r = new Reseau(255);

            //Création des routeurs
            for (Object routeur: routeurs) r.addRouteur((String) routeur);
            JSONArray arrayConnexion = (JSONArray) jsonObject.get("connexion");

            //Création des Connexions
            for (Object arrayParameters: arrayConnexion) {
                JSONArray parameters = (JSONArray) arrayParameters;
                if (parameters.size() == 3)
                        r.ajouterConnexion((String) parameters.get(0), (String) parameters.get(1), (int) ((long) parameters.get(2)));
                else if (parameters.size() == 4)
                        r.ajouterConnexion((String) parameters.get(0), (String) parameters.get(1), (int) ((long) parameters.get(2)),(int) ((long) parameters.get(3)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}