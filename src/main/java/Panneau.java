import java.awt.*;
import javax.imageio.IIOImage;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Panneau extends JPanel {

    private Reseau r;

    public void paintComponent(Graphics g){
        this.add(new Button("btn1"));
    }

    public void setReseau(Reseau r){
        this.r = r;
    }


    public void drawGraph(Graphics g){
        r.getListeRouteur().size();
        g.drawOval(10,10,75,75);
        g.drawString("Paris",75/2-5,75/2-5);
    }
}