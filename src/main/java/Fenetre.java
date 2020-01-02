import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Fenetre extends JFrame {
    private Panneau contentPane = new Panneau();

    private final JTextField text = new JTextField("Reseau.json");
    private final JTextField connexionDebut = new JTextField("Connexion de début");
    private final JTextField connexionFin = new JTextField("Connexion d'arrivé");

    private final JButton dijkstra = new JButton("Dijkstra");
    private final JButton send = new JButton("Envoyer");
    private final JButton edit = new JButton("Edit");
    private final JCheckBox checkJson = new JCheckBox("Json");
    private final JCheckBox checkTxt = new JCheckBox("Txt");

    Fenetre(){
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setContentPane(contentPane);
        contentPane.setBackground(Color.LIGHT_GRAY);
        go();
        this.setVisible(true);
    }


    private void go(){
        contentPane.setLayout(null);

        BasicFrame();
        checkJson.setSelected(true);



        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((checkJson.isSelected())) {
                    contentPane.setReseau(GenerationGraphe.genererJSON(text.getText()));
                    contentPane.setChemin(null);
                    repaint();
                }
                else {
                    if ((checkTxt.isSelected())) {
                        contentPane.setReseau(GenerationGraphe.genererTXT(text.getText(), false));
                        contentPane.setChemin(null);
                        repaint();
                    }
                }
                contentPane.genererReseau(contentPane.getGraphics());
            }
        });

        checkJson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkTxt.isSelected()) checkTxt.setSelected(false);
                text.setText("FilePath to *.json");
            }
        });

        checkTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkJson.isSelected()) checkJson.setSelected(false);
                text.setText("Options\\graphe.txt");
            }
        });

        dijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.setChemin(contentPane.getReseau().plusCourtChemin(connexionDebut.getText(),connexionFin.getText()));
                repaint();
            }
        });
    }

    public void EditFrame(){

    }

    public void BasicFrame(){
        text.setColumns(14);
        connexionDebut.setColumns(14);
        connexionFin.setColumns(14);


        this.add(edit);
        edit.setBounds(10,10,80,25);


        this.add(checkJson);
        checkJson.setBounds(130, 10, 60, 30);
        checkJson.setSelected(true);

        this.add(checkTxt);
        checkTxt.setBounds(130, 35, 60, 30);

        this.add(text);
        text.setBounds(200,25,140,30);

        this.add(send);
        send.setBounds(350,25,90,30);


        this.add(connexionFin);
        connexionFin.setBounds(500,35,140,30);

        this.add(connexionDebut);
        connexionDebut.setBounds(500,10,140,30);

        this.add(dijkstra);
        dijkstra.setBounds(660,20,90,30);
    }
}