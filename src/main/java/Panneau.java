import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.GradientPaint;
import java.awt.Graphics2D;


public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, Color.RED, this.getWidth(), this.getHeight(), Color.cyan, false);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}