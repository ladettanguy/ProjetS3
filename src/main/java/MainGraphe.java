public class MainGraphe {
    public static void main(String[] args){
        Graphe g = new Graphe(5);
        g.addRouteur("Paris");
        g.addRouteur("Mtp");
        g.ajouterConnexion("Paris", "Mtp", 10);
        g.addRouteur("Montady");
        g.ajouterConnexion("Paris", "Montady", 15);
        System.out.println(g.toString());
    }
}