public class MainGraphe {
    public static void main(String[] args){
        Graphe g = new Graphe(5);
        g.addRouteur("Paris");
        g.addRouteur("Mtp");
        g.ajouterConnexion("Paris", "Mtp", 3);
        g.addRouteur("Montady");
        g.ajouterConnexion("Paris", "Montady", 7);
        g.ajouterConnexion("Mtp", "Montady", 11);
        Chemin plusCourt = g.plusCourtChemin("Mtp", "Montady");
        System.out.println(plusCourt.toString());
        System.out.println("Longueur : " + plusCourt.getLongueurChemin());
    }
}