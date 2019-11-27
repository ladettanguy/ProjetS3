import java.util.ArrayList;

public class Graphe {
    private int nombreFrequencesParConnexion;
    private ArrayList<Routeur> listeRouteur;

    public Graphe(int nombreFrequencesParConnexion) {
        this.nombreFrequencesParConnexion = nombreFrequencesParConnexion;
        listeRouteur = new ArrayList<Routeur>();
    }

    public Graphe(Graphe g){
        nombreFrequencesParConnexion = g.nombreFrequencesParConnexion;
        listeRouteur = new ArrayList<Routeur>(g.listeRouteur);
    }

    public void ajouterConnexion(String nomRouteur1, String nomRouteur2, int distance){
        Routeur r = getRouteur(nomRouteur1);
        if (r==null) {
            System.out.println("Le routeur " + nomRouteur1 + " n'est pas connu.");
            return;
        }
        Routeur r2 = getRouteur(nomRouteur2);
        if (r2 == null){
            System.out.println("Le routeur " + nomRouteur2 + " n'est pas connu.");
            return;
        }
        r.addConnexion(new Connexion(distance, r2, nombreFrequencesParConnexion));
        r2.addConnexion(new Connexion(distance, r, nombreFrequencesParConnexion));
        return;
    }

    public void addRouteur(String nomRouteur){
        Routeur r = getRouteur(nomRouteur);
        if (r == null){
            listeRouteur.add(new Routeur(nomRouteur));
            return;
        }
        System.out.println("Le routeur " + nomRouteur + " existe déjà.");
    }

    public Chemin plusCourtChemin (String nomRouteurDepart, String nomRouteurArrivee){

        // Cherche le routeur correspondant au nom donné
        Routeur routeurDepart = getRouteur(nomRouteurDepart);

        // Envoie un message d'erreur si routeurDepart n'est pas connu
        if (routeurDepart==null) {
            System.out.println("Le routeur " + nomRouteurDepart + " n'est pas connu.");
            return null;
        }

        // Cherche le routeur correspondant au nom donné
        Routeur routeurArrivee = getRouteur(nomRouteurArrivee);

        // Envoie un message d'erreur si routeurDepart n'est pas connu
        if (routeurArrivee == null){
            System.out.println("Le routeur " + nomRouteurArrivee + " n'est pas connu.");
            return null;
        }

        // Crée un nouveau graphe qui est une copie de this
        Graphe graphe = new Graphe(this);

        // Crée sur le routeur de départ (étiquette de départ)
        Chemin cur = new Chemin();
        cur.ajouterFin(routeurDepart);

        // Instancie la liste d'étiquettes à traiter ainsi que celle des étiquettes déjà traités
        ArrayList<Chemin> liste = new ArrayList<Chemin>();
        ArrayList<Chemin> listeFinale = new ArrayList<Chemin>();

        // Ajoute l'étiquette de départ à la liste
        liste.add(cur);

        // Boucle qui traitera tout ce qui se trouve dans liste
        while (liste.size()!=0){

            /* Parcours total de liste afin de trouver l'étiquette qui possède la somme la plus petite
            puis la place dans cur et place son indice dans j */
            cur = null;
            int j = 0;
            for (int i = 0; i < liste.size(); i++) {
                if (cur == null || liste.get(i).getLongueurChemin()<cur.getLongueurChemin()){
                    j = i;
                    cur = liste.get(j);
                }
            }

            // Déplace cur de liste pour le mettre dans listeFinale
            liste.remove(j);
            listeFinale.add(cur);

            // Boucle qui parcourt toutes les connexions du routeur de l'étiquette cur
            for (Connexion connexion : cur.getDernierRouteur().getConnexions()) {

                // Recherche si la destination de la connexion n'est pas un routeur déjà traité
                boolean dejaTraite = false;
                for (Chemin chemin : listeFinale) {
                    if (chemin.getDernierRouteur() == connexion.getRouteurDestinataire()) dejaTraite = true;
                }
                if (!dejaTraite) {
                    /* Recherche si le routeur de la connexion possède déja une étiquette, si oui il place true dans existe
                    et il met à jour liste */
                    boolean existe = false;
                    for (int i = 0; i < liste.size(); i++) {
                        if (liste.get(i).getDernierRouteur() == connexion.getRouteurDestinataire()) {
                            existe = true;
                            if (cur.getLongueurChemin() + connexion.getDistance() < liste.get(i).getLongueurChemin()) {
                                liste.remove(i);
                                Chemin nouveau = new Chemin(cur);
                                nouveau.ajouterFin(connexion.getRouteurDestinataire());
                                liste.add(nouveau);
                            }
                        }
                    }
                    if (!existe) {
                        Chemin nouveau = new Chemin(cur);
                        nouveau.ajouterFin(connexion.getRouteurDestinataire());
                        liste.add(nouveau);
                    }
                }
            }
        }

        // Recherche de l'étiquette correspondant au routeur demandé
        for (Chemin chemin : listeFinale) {
            if (chemin.getDernierRouteur() == routeurArrivee) return chemin;
        }

        // Renvoie null ainsi qu'un message d'erreur si aucun chemin n'est trouvé
        System.out.println("Aucun chemin n'a été trouvé");
        return null;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Routeur r:listeRouteur) {
            s.append("Routeur " + r.getNomRouteur() + " :\n");
            for (Connexion c:r.getConnexions()) {
                s.append("  --> " + c.getRouteurDestinataire().getNomRouteur() + " - Distance : " + c.getDistance() + " - Fréquences disponibles : ");
                String s2 = "[";
                for (int i = 0; i < nombreFrequencesParConnexion; i++) {
                    if (c.getConnexions()[i]){
                        if (s2.equals("[")) s2 += i;
                        else s2 += ", " + i;
                    }
                }
                s.append(s2 + "]\n");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private Routeur getRouteur(String s){
        for (Routeur r:listeRouteur) {
            if (r.getNomRouteur().equals(s)) return r;
        }
        return null;
    }

    public void desactiverFrequences(String nomRouteur1, String nomRouteur2, int[] tab){
        Routeur r = getRouteur(nomRouteur1);
        if (r==null) {
            System.out.println("Le routeur " + nomRouteur1 + " n'est pas connu.");
            return;
        }
        Routeur r2 = getRouteur(nomRouteur2);
        if (r2 == null){
            System.out.println("Le routeur " + nomRouteur2 + " n'est pas connu.");
            return;
        }

    }
}
