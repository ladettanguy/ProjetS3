public class MainGraphe {
    public static void main(String[] args){
        Graphe g = new Graphe(5);
        g.addRouteur("A");
        g.addRouteur("B");
        g.addRouteur("C");
        g.addRouteur("D");
        g.addRouteur("E");
        g.addRouteur("F");
        g.addRouteur("G");

        g.ajouterConnexion("A", "B", 2);
        g.ajouterConnexion("A", "C", 5);
        g.ajouterConnexion("A", "D", 2);
        g.ajouterConnexion("B", "C", 6);
        g.ajouterConnexion("B", "F", 7);
        g.ajouterConnexion("C", "G", 1);
        g.ajouterConnexion("D", "E", 17);
        g.ajouterConnexion("E", "G", 4);
        g.ajouterConnexion("G", "F", 1);

        //System.out.println(g.toString());

        Chemin plusCourt = g.plusCourtChemin("F", "D");
        System.out.println(plusCourt.toString());
        System.out.println("Longueur : " + plusCourt.getLongueurChemin());
    }
}