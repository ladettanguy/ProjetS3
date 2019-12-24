import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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

    public static Reseau genererTXT(String filePath, boolean hope){
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File(filePath)));
            String str;
            int nbMaxFrequences = TraiterOptionTXT.getIntEndOfLine(read.readLine());
            Reseau r = new Reseau(nbMaxFrequences);
            read.readLine();
            while ((str = read.readLine()) != null && !str.equals("Connexions :")){
                r.addRouteur(str);
            }
            while ((str = read.readLine()) != null){
                String[] split = str.split(" ");
                if (hope) r.ajouterConnexion(split[0], split[1], 1);
                else r.ajouterConnexion(split[0], split[1], Integer.parseInt(split[2]));
            }
            return r;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<ArrayList<String>> traiterListeRequeteTXT(Reseau r){
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(new File("Options\\listeRequete.txt")));
            String str;
            while ((str = read.readLine()) != null){
                list.add(new ArrayList<String>());
                String[] split = str.split(" ");
                list.get(list.size()-1).add(split[0]);
                list.get(list.size()-1).add(split[1]);
                list.get(list.size()-1).add(split[2]);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}