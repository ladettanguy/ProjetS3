import java.util.ArrayList;

public class Chemin {
    private ArrayList<Routeur> listeRouteur;

    public Chemin() {
        listeRouteur = new ArrayList<Routeur>();
    }

    public void ajouterDebut(Routeur r){
        listeRouteur.add(0, r);
    }

    public void ajouterFin(Routeur r){
        listeRouteur.add(r);
    }

    public int getLongueurChemin(){
        int distance = 0;
        for (int i = 0; i < listeRouteur.size()-1; i++) {
            distance += listeRouteur.get(i).getConnexionVersUnRouteur(listeRouteur.get(i+1)).getDistance();
        }
        return distance;
    }
}