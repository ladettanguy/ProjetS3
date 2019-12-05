import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Panneau extends JPanel {

    private Reseau r;
    private Chemin c;

    protected void paintComponent(Graphics g){
        genererReseau(g);
    }

    void setReseau(Reseau r){
        this.r = r;
    }

    void genererReseau(Graphics g){
        if (r != null) {
            ArrayList<Routeur> routeurs = r.getListeRouteur();
            ArrayList<Integer> xRouteurs = new ArrayList<>();
            ArrayList<Integer> yRouteurs = new ArrayList<>();
            int rayon = 10*routeurs.size()*2;
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(this.getWidth()/2,this.getHeight()/2);
            for (int i = 0; i < routeurs.size(); i++) {
                int y = (int) (Math.sin( Math.PI*2 / (float)routeurs.size()    * i)  *rayon);
                int x = (int) (Math.cos( Math.PI*2 / (float)routeurs.size()    * i)  *rayon);
                g2d.drawOval(x, y,30, 30);
                g2d.drawString(routeurs.get(i).getNom(),x+14,y+15);
                xRouteurs.add(x);
                yRouteurs.add(y);
            }
            for (Routeur rout: routeurs) {
                for (Connexion c : rout.getConnexions()) {
                    int indexEnvoyeur = routeurs.indexOf(rout);
                    int indexDestinataire = routeurs.indexOf(c.getRouteurDestinataire());
                    g2d.drawLine(xRouteurs.get(indexEnvoyeur)+15,yRouteurs.get(indexEnvoyeur)+15,xRouteurs.get(indexDestinataire)+15,yRouteurs.get(indexDestinataire)+15);
                }
            }
            g2d.translate(-this.getWidth()/2,-this.getHeight()/2);
        }
    }

}