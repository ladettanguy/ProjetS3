import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        //Vous verrez cette phrase chaque fois que la méthode sera invoquée
        System.out.println("Je suis exécutée !");
        g.fillOval(20, 20, 75, 75);
    }
}