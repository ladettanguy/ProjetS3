import javax.swing.JFrame;
import java.awt.*;
import java.awt.Graphics;

public class Fenetre extends JFrame {
    public Fenetre(Reseau r){
        this.setTitle("Ma première fenêtre Java");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Etre redimensionner ou pas
        this.setResizable(true);

        //Enlever le contours ou pas
        this.setUndecorated(false);

        //Instanciation d'un objet JPanel
        Panneau pan = new Panneau();
        //Définition de sa couleur de fond
        pan.setBackground(Color.LIGHT_GRAY);
        //On prévient notre JFrame que notre JPanel sera son content pane
        this.setContentPane(pan);

        this.setVisible(true);
    }
}