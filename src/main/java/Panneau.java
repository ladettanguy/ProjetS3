import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Panneau extends JPanel {

    private Reseau r;

    protected void paintComponent(Graphics g){
        if (r != null) {
            int i = 0;
            ArrayList<Integer> xRouteurs=new ArrayList<>();
            ArrayList<Integer> yRouteurs=new ArrayList<>();
            int x;
            int y;
            g.translate(this.getWidth()/2,this.getHeight()/2);
            Iterator<Routeur> ite =r.getListeRouteur().iterator();
            while (ite.hasNext()) {
                x = i*35;//+ Math.PI*30);
                y = -10*r.getListeRouteur().size();
                g.drawOval(x, y,30, 30);
                g.drawString(ite.next().getNom(),x +(30/2)-2,y+2*30/3);
                i++;
            }
            g.translate(-this.getWidth()/2,-this.getHeight()/2);
        }
    }

    void setReseau(Reseau r){
        this.r = r;
    }

}