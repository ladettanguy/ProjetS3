import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GenerationGraphe {
    private static JSONParser parser = new JSONParser();

    public static Reseau genererJSON(String filePath) {
        Reseau r = null;
        try
        {
            //Lire le fichier JSON
            FileReader file = new FileReader(new File (filePath));
            JSONObject jsonObject = (JSONObject) parser.parse(file);
            JSONArray routeurs = (JSONArray) jsonObject.get("routeur");
            r = new Reseau(20);

            //Création des routeurs
            for (Object routeur: routeurs) r.addRouteur((String) routeur);
            JSONArray arrayConnexion = (JSONArray) jsonObject.get("connexion");

            //Création des Connexions
            for (Object arrayParameters: arrayConnexion) {
                JSONArray parameters = (JSONArray) arrayParameters;
                r.ajouterConnexion((String) parameters.get(0), (String) parameters.get(1), (int) ((long) parameters.get(2)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static Reseau genererTXT(String filePath,int nbMaxFrequences){
        Reseau r = new Reseau(nbMaxFrequences);
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File(filePath)));
            String str;
            str = read.readLine();
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

    public static void traiterTXTGlouton(Reseau r){
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File("listeGlouton.txt")));
            String str;
            while ((str = read.readLine()) != null){
                String[] split = str.split(" ");
                Chemin c = r.glouton(split[0], split[1], Integer.parseInt(split[2]), false);
                if (c == null){
                    System.out.println("Le chemin entre les routeur " + split[0] + " et " + split[1] + " ne passe pas.");
                    return;
                }
                c.desactiverFrequences();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}