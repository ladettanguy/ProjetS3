import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class TraiterOptionTXT {
    public static void traiterOptionTXT(){
        traiterListeTXT(listeInt());
    }

    public static void traiterListeTXT(int[] tab){
        boolean hope = false;
        Reseau r;
        ArrayList<ArrayList<String>> listeRequetes = new ArrayList<>();
        if (tab[1] == 1) hope = false;
        else if (tab[1] == 0) hope = true;
        else {
            System.out.println("Erreur pour la distance hope");
            return;
        }
        if (tab[0] == 1) r = Reseau.genererReseauAleatoire(tab[2], tab[4], tab[3], tab[5], hope);
        else if (tab[0] == 0) r = GenerationGraphe.genererTXT("Options\\graphe.txt", hope);
        else {
            System.out.println("Erreur pour la ligne de generation de graphe aléatoire ou non");
            return;
        }
        if (tab[6] == 1) listeRequetes = r.genererListeRequetes(tab[7], tab[8]);
        else if(tab[6] == 0) listeRequetes = GenerationGraphe.traiterListeRequeteTXT(r);
        else {
            System.out.println("Erreur pour la ligne de generation de requêtes aléatoire ou non");
            return;
        }
        System.out.println("Liste des requêtes utilisés :");
        for (int i = 0; i < listeRequetes.size(); i++) {
            System.out.println(listeRequetes.get(i).get(0) + " " + listeRequetes.get(i).get(1) + " " + listeRequetes.get(i).get(2));
        }
        r.tetrisMethodeForcer(listeRequetes, false);
        new Reseau(r, new int[0]).tetrisMethodeForcer(listeRequetes, true);
    }

    private static int[] listeInt(){
        int[] tab = new int[9];
        try {
            BufferedReader f = new BufferedReader(new FileReader(new File("Options\\option.txt")));
            String line = sautLigne(f, 3);
            tab[0] = getIntEndOfLine(line);
            tab[1] = getIntEndOfLine(f.readLine());
            f.readLine();
            tab[2] = getIntEndOfLine(f.readLine());
            tab[3] = getIntEndOfLine(f.readLine());
            tab[4] = getIntEndOfLine(f.readLine());
            tab[5] = getIntEndOfLine(f.readLine());
            sautLigne(f, 2);
            tab[6] = getIntEndOfLine(f.readLine());
            f.readLine();
            tab[7] = getIntEndOfLine(f.readLine());
            tab[8] = getIntEndOfLine(f.readLine());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return tab;
    }

    public static int getIntEndOfLine(String s){
        String end = "";
        int i = s.length()-1;
        while (s.charAt(i) != ' ' && s.charAt(i) != ':'){
            end = s.charAt(i) + end;
            i--;
        }
        return Integer.parseInt(end);
    }

    private static String sautLigne(BufferedReader f, int i){
        String end = "";
        try{
            for (int j = 0; j < i; j++) {
                end = f.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return end;
    }
}
