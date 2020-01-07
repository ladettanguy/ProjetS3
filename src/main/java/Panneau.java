import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class Panneau extends JPanel {


    private Chemin c;private Reseau r;
    private int rayon;

    protected void paintComponent(Graphics g){
        genererReseau(g);
    }

    void setReseau(Reseau r){
        this.r = r;
    }

    void setChemin(Chemin c){
        this.c = c;
    }

    Reseau getReseau(){return r;}

    void genererReseau(Graphics g){
        if (r != null) {
            try {
                Image routeurNormal = ImageIO.read(new File("Image.png"));
                Image routeurDijkstra = ImageIO.read(new File("Router-icon.png"));

                ArrayList<Routeur> routeurs = r.getListeRouteur();
                ArrayList<Integer> xRouteurs = new ArrayList<>();
                ArrayList<Integer> yRouteurs = new ArrayList<>();

                rayon = 10*routeurs.size()*2;
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(this.getWidth()/2,this.getHeight()/2);
                for (int i = 0; i < routeurs.size(); i++) {
                    int y = (int) (Math.sin( Math.PI*2 / (float)routeurs.size()    * i)  *rayon);
                    int x = (int) (Math.cos( Math.PI*2 / (float)routeurs.size()    * i)  *rayon);
                    if(y-15<=0)
                        g2d.drawString(routeurs.get(i).getNom(),x+12,y-15);
                    else
                        g2d.drawString(routeurs.get(i).getNom(),x+12,y+45);
                    xRouteurs.add(x);
                    yRouteurs.add(y);
                }
                int i = 0;
                for (Routeur rout: routeurs) {
                    for (Connexion co : rout.getConnexions()) {
                        int indexEnvoyeur = routeurs.indexOf(rout);
                        int indexDestinataire = routeurs.indexOf(co.getRouteurDestinataire());
                        if(c != null && c.contient(co.getRouteurDestinataire()) && c.contient(rout))
                            g2d.setColor(Color.RED);
                        g2d.drawLine(xRouteurs.get(indexEnvoyeur)+15,yRouteurs.get(indexEnvoyeur)+15,xRouteurs.get(indexDestinataire)+15,yRouteurs.get(indexDestinataire)+15);
                        g2d.setColor(Color.BLACK);
                    }
                    if(c != null && c.contient(r.getListeRouteur().get(i)))
                        g.drawImage(routeurDijkstra, xRouteurs.get(i) , yRouteurs.get(i) ,30,30,null);
                    else
                        g.drawImage(routeurNormal  , xRouteurs.get(i) , yRouteurs.get(i) , 30, 30,null);
                    i++;
                }
                g2d.translate(-this.getWidth()/2,-this.getHeight()/2);
            }
            catch (Exception e){}
        }
    }
}


