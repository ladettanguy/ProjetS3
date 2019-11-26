public class Etiquette {
    private Chemin chemin;
    private int somme;

    public Etiquette(int somme) {
        this.somme = somme;
        chemin = new Chemin();
    }

    public Etiquette(Etiquette e) {
        chemin = e.chemin;
        somme = e.somme;
    }

    public void ajouterRouteur(Routeur r){
        chemin.ajouterFin(r);
    }

    public int getSomme() {
        return somme;
    }

    public Routeur getDernierRouteur(){
        return chemin.getDernierRouteur();
    }
}
