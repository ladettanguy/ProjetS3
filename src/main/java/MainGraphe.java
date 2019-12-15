import java.util.ArrayList;

public class MainGraphe {
    public static void main(String[] args){

        Reseau r = GenerationGraphe.genererTXT("graphe.txt",5);

        ArrayList<ArrayList<String>> test = r.genererListeTestPourTetris(20, 3);
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i).get(0) + " " + test.get(i).get(1) + " " + test.get(i).get(2));
        }
        r.tetrisMethodeForcer(test, false);
        new Reseau(r, new int[0]).tetrisMethodeForcer(test, true);

        //Fenetre f = new Fenetre();
    }
}