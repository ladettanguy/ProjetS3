import java.awt.*;
import javax.imageio.IIOImage;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Panneau extends JPanel {

    private Reseau r;

    protected void paintComponent(Graphics g){
        if (r != null) {
            for (int i = 0; i < r.getListeRouteur().size(); i++) {
                g.drawOval(15+(i*35), 200, 30, 30);
            }
        }
    }

    void setReseau(Reseau r){
        this.r = r;
    }

}