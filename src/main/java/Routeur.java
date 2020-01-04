import java.util.ArrayList;

public class Routeur {
    private ArrayList<Connexion> connexions;
    private String nom;

    public Routeur(String nom) {
        this.nom = nom;
        connexions = new ArrayList<Connexion>();
    }

    public void reinitialiserFrequences(){
        for (Connexion c : connexions) {
            c.reinitialiserFrequences();
        }
    }

    public ArrayList<Connexion> getConnexions() {
        return connexions;
    }

    public String getNom() {
        return nom;
    }

    public Connexion getConnexionVersUnRouteur(Routeur r){
        for (Connexion connexion:connexions) {
            if (connexion.getRouteurDestinataire()==r) return connexion;
        }
        return null;
    }

    public void ajouterConnexion(Connexion c){
        for (Connexion cur:connexions) {
            if (cur.getRouteurDestinataire() == c.getRouteurDestinataire()){
                System.out.println("La connexion du routeur " + nom + " au routeur " + c.getRouteurDestinataire().getNom() + " exixste déjà.");
                return;
            }
        }
        connexions.add(c);
    }

    public void supprimerConnexionVersUnRouteur(Routeur r){
        for (int i = 0; i < connexions.size(); i++) {
            if (connexions.get(i).getRouteurDestinataire() == r){
                connexions.remove(i);
                return;
            }
        }
        System.out.println("La connexion du routeur " + nom + " au routeur " + r.getNom() + " n'exixste pas.");
    }

    public boolean isNull(){
        if( this == null) System.out.println("Le routeur " + this + " n'est pas connu.");
        return this == null;
    }
}
