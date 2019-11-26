public class Connexion {
    private int distance;
    private Routeur routeurDestinataire;
    private boolean[] connexions;

    public Connexion(int distance, Routeur routeurDestinataire, int nbFrequencesMax) {
        this.distance = distance;
        this.routeurDestinataire = routeurDestinataire;
        connexions = new boolean[nbFrequencesMax];
        for (int i = 0; i < nbFrequencesMax; i++) {
            connexions[i] = true;
        }
    }

    public boolean[] getConnexions() {
        return connexions;
    }

    public int getDistance() {
        return distance;
    }

    public Routeur getRouteurDestinataire() {
        return routeurDestinataire;
    }
}
