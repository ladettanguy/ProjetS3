import java.util.ArrayList;

public class Routeur {
    private ArrayList<Connexion> connexions;
    private String nomRouteur;

    public Routeur(String nomRouteur) {
        this.nomRouteur = nomRouteur;
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

    public String getNomRouteur() {
        return nomRouteur;
    }

    public void addConnexion(Connexion c){
        for (Connexion c2:connexions) {
            if (c2.getRouteurDestinataire() == c.getRouteurDestinataire()){
                System.out.println("La connexion du routeur " + nomRouteur + " au routeur " + c.getRouteurDestinataire().getNomRouteur() + " exixste déjà.");
                return;
            }
        }
        connexions.add(c);
    }
}
