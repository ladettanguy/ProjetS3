public class MainGraphe {
    public static void main(String[] args){
        Reseau r = new Reseau(5);
        r.addRouteur("A");
        r.addRouteur("B");
        r.addRouteur("C");
        r.addRouteur("D");
        r.addRouteur("E");
        r.addRouteur("F");
        r.addRouteur("G");

        r.ajouterConnexion("A", "B", 2);
        r.ajouterConnexion("A", "C", 5);
        r.ajouterConnexion("A", "D", 2);
        r.ajouterConnexion("B", "C", 6);
        r.ajouterConnexion("B", "F", 7);
        r.ajouterConnexion("C", "G", 1);
        r.ajouterConnexion("D", "E", 17);
        r.ajouterConnexion("E", "G", 4);
        r.ajouterConnexion("G", "F", 1);

        r.desactiverFrequences("B", "A", new int[] {2});
        r.desactiverFrequences("F", "G", new int[] {3});
        r.desactiverFrequences("B", "C", new int[] {3});

        //System.out.println(r.toString());

        Chemin glouton = r.glouton1("F", "D", 4);
        if (glouton != null) System.out.println(glouton.toString());
        else System.out.println("Aucun chemin n'a été trouvé");

        //Chemin plusCourt = r.plusCourtChemin("F", "D");
        //System.out.println(plusCourt.toString());
        //System.out.println("Longueur : " + plusCourt.getLongueurChemin());
    }
}