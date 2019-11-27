import java.util.ArrayList;

public class Routeur {
    private ArrayList<Connexion> connexions;
    private String nom;

    public Routeur(String nom) {
        this.nom = nom;
        connexions = new ArrayList<Connexion>();
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
}
