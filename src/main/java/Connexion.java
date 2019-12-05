public class Connexion {
    private int distance;
    private Routeur routeurDestinataire;
    private boolean[] tableauFrequences;

    public Connexion(int distance, Routeur routeurDestinataire, int nbFrequencesMax) {
        this.distance = distance;
        this.routeurDestinataire = routeurDestinataire;
        tableauFrequences = new boolean[nbFrequencesMax];
        for (int i = 0; i < nbFrequencesMax; i++) {
            tableauFrequences[i] = true;
        }
    }

    public boolean[] getTableauFrequences() {
        return tableauFrequences;
    }

    public int getDistance() {
        return distance;
    }

    public Routeur getRouteurDestinataire() {
        return routeurDestinataire;
    }

    public void desactiverFrequence (int i){
        tableauFrequences[i] = false;
    }

    public void activerFrequence (int i){
        tableauFrequences[i] = true;
    }

    public boolean getFrequence (int i) {
        return tableauFrequences[i];
    }

    public boolean isNull(){
        if(this == null) System.out.println("Il n'y a pas de connexion entre ces deux routeurs");
        return this == null;
    }
}
