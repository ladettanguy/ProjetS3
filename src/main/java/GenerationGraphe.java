import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GenerationGraphe {
    private static JSONParser parser = new JSONParser();

    public static Reseau genererJSON() {
        String filePath = "Reseau.json";
        Reseau r = null;
        try
        {
            //Lire le fichier JSON
            FileReader file = new FileReader(new File (filePath));
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

    public static Reseau genererTXT(int nbMaxFrequences){
        Reseau r = new Reseau(nbMaxFrequences);
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File("generateurGraphe.txt")));
            String str = read.readLine();
            while ((str = read.readLine()) != null && !str.equals("Connexions :")){
                r.addRouteur(str);
            }
            while ((str = read.readLine()) != null){
                String[] split = str.split(" ");
                r.ajouterConnexion(split[0], split[1], Integer.parseInt(split[2]));
            }
            return r;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}