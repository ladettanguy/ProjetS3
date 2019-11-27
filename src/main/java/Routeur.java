import java.util.ArrayList;

public class Routeur {
    private ArrayList<Connexion> connexions;
    private String nom;

    public Routeur(String nomRouteur) {
        this.nom = nomRouteur;
        connexions = new ArrayList<Connexion>();
    }

    public Connexion getConnexionVersUnRouteur(Routeur r){
        for (Connexion c:connexions) {
            if (c.getRouteurDestinataire()==r) return c;
        }
        return null;
    }

    public ArrayList<Connexion> getConnexions() {
        return connexions;
    }

    public String getNom() {
        return nom;
    }

    public void addConnexion(Connexion c){
        for (Connexion c2:connexions) {
            if (c2.getRouteurDestinataire() == c.getRouteurDestinataire()){
                System.out.println("La connexion du routeur " + nom + " au routeur " + c.getRouteurDestinataire().getNom() + " exixste déjà.");
                return;
            }
        }
        connexions.add(c);
    }
}
