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
    public static Reseau generer() {
        JSONParser parser = new JSONParser();
        String filePath = "./Reseau.json";
        Reseau r = null;
        try (FileReader fichier = new FileReader(ClassLoader.getSystemResource(filePath).getFile()))
        {
            JSONObject jsonObject = (JSONObject) parser.parse(fichier);
            JSONArray routeurs = (JSONArray) jsonObject.get("routeur");
            r = new Reseau(20);

            Iterator i = routeurs.iterator();
            while(i.hasNext()) r.addRouteur((String) i.next());

            System.out.println(r);
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