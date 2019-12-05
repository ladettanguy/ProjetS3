import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Panneau extends JPanel {

    private Reseau r;

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
            //int y = -10*(r.getListeRouteur().size()*2);
            int rayon = 10*routeurs.size()*2;
            int y = (int) (Math.sin(0)*rayon);
            int x = rayon;
            xRouteurs.add(x);
            yRouteurs.add(y);
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(this.getWidth()/2,this.getHeight()/2);
            for (int i = 0; i < routeurs.size(); i++) {
                g2d.drawOval(x, y,30, 30);
                g2d.drawString(routeurs.get(i).getNom(),x+14,y+15);
                y = (int) (Math.sin(360/((float)routeurs.size()-1.0)*(i+1))*rayon);
                x = (int) (Math.cos(360/((float)routeurs.size()-1.0)*(i+1))*rayon);
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