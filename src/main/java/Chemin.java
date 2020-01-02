import java.util.ArrayList;

public class Chemin {
    private ArrayList<Routeur> listeRouteur;
    private int[] frequenceUtilisees;

    public Chemin() {
        listeRouteur = new ArrayList<Routeur>();
        frequenceUtilisees = new int[0];
    }

    public Chemin(Chemin c) {
        listeRouteur = new ArrayList<Routeur>();
        listeRouteur.addAll(c.listeRouteur);
        frequenceUtilisees = new int[c.frequenceUtilisees.length];
        for (int i = 0; i < c.frequenceUtilisees.length; i++) {
            frequenceUtilisees[i] = c.frequenceUtilisees[i];
        }
    }

    public void setFrequenceUtilisees(int[] frequenceUtilisees) {
        this.frequenceUtilisees = frequenceUtilisees;
    }

    public void copyFrequenceUtilisees(int[] frequenceUtilisees){
        this.frequenceUtilisees = new int[frequenceUtilisees.length];
        for (int i = 0; i < frequenceUtilisees.length; i++) {
            this.frequenceUtilisees[i] = frequenceUtilisees[i];
        }
    }

    public void ajouterDebut(Routeur r){
        listeRouteur.add(0, r);
    }

    public Chemin ajouterFin(Routeur r){
        listeRouteur.add(r);
        return this;
    }

    public int getLongueurChemin(){
        int distance = 0;
        for (int i = 0; i < listeRouteur.size()-1; i++) {
            distance += listeRouteur.get(i).getConnexionVersUnRouteur(listeRouteur.get(i+1)).getDistance();
        }
        return distance;
    }

    public Routeur getDernierRouteur(){
        return listeRouteur.get(listeRouteur.size()-1);
    }

    public ArrayList<Routeur> getListeRouteur() {
        return listeRouteur;
    }

    public void desactiverFrequences(){
        for (int i = 0; i < listeRouteur.size()-1; i++) {
            for (int j = 0; j < frequenceUtilisees.length; j++) {
                listeRouteur.get(i).getConnexionVersUnRouteur(listeRouteur.get(i+1)).desactiverFrequence(frequenceUtilisees[j]);
            }
        }
    }

    public boolean contient(Routeur r){
        return listeRouteur.contains(r);
    }


    public String toString(){
        String s = "Chemin : ";
        for (Routeur r : listeRouteur) {
            if (s.equals("Chemin : ")) s += r.getNom();
            else s += ", " + r.getNom();
        }
        if (frequenceUtilisees.length != 0){
            s += "\nFréquences utilisés : [";
            for (int i : frequenceUtilisees) {
                if (s.charAt(s.length()-1)=='[') s += i;
                else s += ", " + i;
            }
            s += "]";
        }
        s += "\nLongueur du chemin : " + getLongueurChemin();
        return s;
    }
}
