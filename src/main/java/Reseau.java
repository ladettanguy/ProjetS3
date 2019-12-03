import java.util.ArrayList;

public class Reseau {
    private int nombreFrequencesParConnexion;
    private ArrayList<Routeur> listeRouteur;

    public Reseau(int nombreFrequencesParConnexion) {
        this.nombreFrequencesParConnexion = nombreFrequencesParConnexion;
        listeRouteur = new ArrayList<Routeur>();
    }

    public Reseau(Reseau r, int[] frequencesObligatoires){
        nombreFrequencesParConnexion = r.nombreFrequencesParConnexion;
        listeRouteur = new ArrayList<Routeur>();
        for (Routeur ancienRouteur : r.listeRouteur) {
            addRouteur(ancienRouteur.getNom());
        }
        for (Routeur ancienRouteur : r.listeRouteur) {
            for (Connexion ancienneConnexion : ancienRouteur.getConnexions()) {
                boolean ajouter = true;
                for (int i = 0; i < frequencesObligatoires.length; i++) {
                    if (frequencesObligatoires[i]<0 || frequencesObligatoires[i]>=nombreFrequencesParConnexion){
                        System.out.println("La fréquence entrée " + frequencesObligatoires[i] + " n'est pas dans l'intervalle [0, " + (nombreFrequencesParConnexion-1) + "], le reseau ne s'est donc pas crée correctement.");
                    }
                    else {
                        if (!ancienneConnexion.getFrequence(frequencesObligatoires[i])) ajouter = false;
                    }
                }
                if (ajouter) getRouteur(ancienRouteur.getNom()).ajouterConnexion(new Connexion(ancienneConnexion.getDistance(), getRouteur(ancienneConnexion.getRouteurDestinataire().getNom()), nombreFrequencesParConnexion));
            }
        }
    }

    public void ajouterConnexion(String nomRouteur1, String nomRouteur2, int distance){
        ajouterConnexion(nomRouteur1,nomRouteur2,distance,nombreFrequencesParConnexion);
    }

    public void ajouterConnexion(String nomRouteur1, String nomRouteur2, int distance, int nbFrequence){
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
        r.ajouterConnexion(new Connexion(distance, r2, nbFrequence));
        r2.ajouterConnexion(new Connexion(distance, r, nbFrequence));
        return;
    }

    public void supprimerConnexion(String nomRouteur1, String nomRouteur2){
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
        r.supprimerConnexionVersUnRouteur(r2);
        r2.supprimerConnexionVersUnRouteur(r);
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

        // Cherche le routeur correspondant a nomRouteurDepart
        Routeur routeurDepart = getRouteur(nomRouteurDepart);

        // Envoie un message d'erreur si routeurDepart n'est pas connu
        if (routeurDepart==null) {
            System.out.println("Le routeur " + nomRouteurDepart + " n'est pas connu.");
            return null;
        }

        // Cherche le routeur correspondant a nomRouteurArrivee
        Routeur routeurArrivee = getRouteur(nomRouteurArrivee);

        // Envoie un message d'erreur si routeurArrivee n'est pas connu
        if (routeurArrivee == null){
            System.out.println("Le routeur " + nomRouteurArrivee + " n'est pas connu.");
            return null;
        }

        // Crée un chemin de départ et ajoute le routeur de départ
        Chemin cur = new Chemin();
        cur.ajouterFin(routeurDepart);

        // Instancie la liste de chemins à traiter ainsi que celle des chemins déjà traités
        ArrayList<Chemin> liste = new ArrayList<Chemin>();
        ArrayList<Chemin> listeFinale = new ArrayList<Chemin>();

        // Ajoute le chemin de départ à la liste de chemins à traiter
        liste.add(cur);

        // Boucle qui traite tout ce qui se trouve dans la liste de chemins à traiter
        while (liste.size()!=0){

            /* Parcours total de liste afin de trouver le chemin qui possède la plus petite longueur
            puis le place dans cur et place son indice dans j */
            cur = null;
            int j = 0;
            for (int i = 0; i < liste.size(); i++) {
                if (cur == null || liste.get(i).getLongueurChemin()<cur.getLongueurChemin()){
                    j = i;
                    cur = liste.get(j);
                }
            }

            // Supprime cur de liste pour le mettre dans listeFinale
            liste.remove(j);
            listeFinale.add(cur);

            // Boucle qui parcourt toutes les connexions du dernier routeur du chemin de cur
            for (Connexion connexion : cur.getDernierRouteur().getConnexions()) {

                // Recherche si la destination de la connexion n'est pas un routeur déjà traité
                boolean dejaTraite = false;
                for (Chemin chemin : listeFinale) {
                    if (chemin.getDernierRouteur() == connexion.getRouteurDestinataire()) dejaTraite = true;
                }
                if (!dejaTraite) {
                    /* Recherche si le routeur destination de la connexion possède déja une étiquette
                    et met à jour liste */
                    boolean existe = false;
                    for (int i = 0; i < liste.size(); i++) {
                        if (liste.get(i).getDernierRouteur() == connexion.getRouteurDestinataire()) {
                            existe = true;
                            if (cur.getLongueurChemin() + connexion.getDistance() < liste.get(i).getLongueurChemin()) {
                                liste.remove(i);
                                liste.add(new Chemin(cur).ajouterFin(connexion.getRouteurDestinataire()));
                            }
                        }
                    }
                    if (!existe) liste.add(new Chemin(cur).ajouterFin(connexion.getRouteurDestinataire()));
                }
            }
        }

        // Recherche de l'étiquette correspondant au routeur de destination demandé si il existe, sinon renvoie null
        for (Chemin chemin : listeFinale) {
            if (chemin.getDernierRouteur() == routeurArrivee) return chemin;
        }
        return null;
    }

    public Chemin glouton1(String nomRouteurDepart, String nomRouteurArrivee, int nbFrequenceConsecutives){
        Routeur r = getRouteur(nomRouteurDepart);
        if (r==null) {
            System.out.println("Le routeur " + nomRouteurDepart + " n'est pas connu.");
            return null;
        }
        Routeur r2 = getRouteur(nomRouteurArrivee);
        if (r2 == null){
            System.out.println("Le routeur " + nomRouteurArrivee + " n'est pas connu.");
            return null;
        }
        int[] tabfrequences = new int[nbFrequenceConsecutives];
        for (int i = -1; i < nbFrequenceConsecutives-1; i++) {
            tabfrequences[i+1] = i;
        }
        for (int i = 0; i < nombreFrequencesParConnexion - nbFrequenceConsecutives + 1; i++) {
            for (int j = 0; j < nbFrequenceConsecutives; j++) {
                tabfrequences[j]++;
            }
            Chemin c = new Reseau(this, tabfrequences).plusCourtChemin(nomRouteurDepart, nomRouteurArrivee);
            if (c != null) return c;
        }
        return null;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Routeur r:listeRouteur) {
            s.append("Routeur " + r.getNom() + " :\n");
            for (Connexion c:r.getConnexions()) {
                s.append("  --> " + c.getRouteurDestinataire().getNom() + " - Distance : " + c.getDistance() + " - Fréquences disponibles : ");
                String s2 = "[";
                for (int i = 0; i < nombreFrequencesParConnexion; i++) {
                    if (c.getTableauFrequences()[i]){
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
            if (r.getNom().equals(s)) return r;
        }
        return null;
    }

    public ArrayList<Routeur> getListeRouteur(){ return listeRouteur; }

    public void desactiverFrequences(String nomRouteurDepart, String nomRouteurArrivee, int[] tab){
        Routeur r = getRouteur(nomRouteurDepart);
        if (r==null) {
            System.out.println("Le routeur " + nomRouteurDepart + " n'est pas connu.");
            return;
        }
        Routeur r2 = getRouteur(nomRouteurArrivee);
        if (r2 == null){
            System.out.println("Le routeur " + nomRouteurArrivee + " n'est pas connu.");
            return;
        }
        Connexion c = r.getConnexionVersUnRouteur(r2);
        if (c==null) {
            System.out.println("Il n'y a pas de connexion entre " + r.getNom() + " et " + r2.getNom());
            return;
        }

        for (int i = 0; i < tab.length; i++) {
            if (tab[i]<0 || tab[i]>=nombreFrequencesParConnexion){
                System.out.println("Une fréquence entrée n'est pas dans l'intervalle [0, " + (nombreFrequencesParConnexion-1) + "], aucune fréquence n'a été modifiée");
                return;
            }
        }
        for (int i : tab) {
            c.desactiverFrequence(i);
        }
    }

    public void activerFrequences(String nomRouteurDepart, String nomRouteurArrivee, int[] tab){
        Routeur r = getRouteur(nomRouteurDepart);
        if (r==null) {
            System.out.println("Le routeur " + nomRouteurDepart + " n'est pas connu.");
            return;
        }
        Routeur r2 = getRouteur(nomRouteurArrivee);
        if (r2 == null){
            System.out.println("Le routeur " + nomRouteurArrivee + " n'est pas connu.");
            return;
        }
        Connexion c = r.getConnexionVersUnRouteur(r2);
        if (c==null) {
            System.out.println("Il n'y a pas de connexion entre " + r.getNom() + " et " + r2.getNom());
            return;
        }

        for (int i = 0; i < tab.length; i++) {
            if (tab[i]<0 || tab[i]>=nombreFrequencesParConnexion){
                System.out.println("Une fréquence entrée n'est pas dans l'intervalle [0, " + (nombreFrequencesParConnexion-1) + "], aucune fréquence n'a été modifiée");
                return;
            }
        }
        for (int i : tab) {
            c.activerFrequence(i);
        }
    }
}
