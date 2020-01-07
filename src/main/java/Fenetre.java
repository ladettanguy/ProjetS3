import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Fenetre extends JFrame {
    private Panneau contentPane = new Panneau();

    //Basic frame
    private final JTextField text = new JTextField("Reseau.json");
    private final JTextField connexionDebut = new JTextField("Connexion de début");
    private final JTextField connexionFin = new JTextField("Connexion d'arrivé");
    private final JTextField nbFrequence = new JTextField("Nb de fréquence voulut");

    private final JButton dijkstra = new JButton("Dijkstra");
    private final JButton send = new JButton("Envoyer");
    private final JButton edit = new JButton("Edit");
    private final JButton glouton = new JButton("Glouton");

    private final JCheckBox plusCour = new JCheckBox("Plus Court chemin");
    private final JCheckBox checkJson = new JCheckBox("Json");
    private final JCheckBox checkTxt = new JCheckBox("Txt");

    //Edit Frame

    private final JTextField nomRouteur = new JTextField("Nom du routeur");
    private final JTextField connexionRouteur = new JTextField("Routeur de départ");
    private final JTextField connexionRouteurDestinataire = new JTextField("Routeur destinataire");
    private final JTextField nbFrequenceConnexion = new JTextField("Nb fréquence max");
    private final JTextField distance = new JTextField("Distance");

    private final JButton creeRouteur = new JButton("Crée");
    private final JButton creeConnexion = new JButton("Crée");
    private final JButton suppConnexion = new JButton("Supprimé");
    private final JButton suppRouteur = new JButton("Supprimé");
    private final JButton retour = new JButton("Retour");

    Fenetre(){
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setContentPane(contentPane);
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setLayout(null);
        basicFrame();
        this.setVisible(true);
    }

    public void editFrame(){
        repaint();

        this.add(nomRouteur);
        nomRouteur.setBounds(130,10,140,30);
        this.add(creeRouteur);
        creeRouteur.setBounds(130,50,80,25);

        this.add(connexionRouteur);
        connexionRouteur.setBounds(350,10,140,30);
        this.add(connexionRouteurDestinataire);
        connexionRouteurDestinataire.setBounds(350,40,140,30);
        this.add(nbFrequenceConnexion);
        nbFrequenceConnexion.setBounds(490,10,140,30);
        this.add(distance);
        distance.setBounds(490,40,140,30);
        this.add(creeConnexion);
        creeConnexion.setBounds(450,80,80,25);

        this.add(retour);
        retour.setBounds(10,10,80,25);

        creeRouteur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.getReseau().addRouteur(nomRouteur.getText());
                repaint();
            }
        });

        creeConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reseau r = contentPane.getReseau();
                r.getRouteur(connexionRouteur.getText()).ajouterConnexion(
                    new Connexion(Integer.parseInt(distance.getText()),r.getRouteur(connexionRouteurDestinataire.getText()),20)
                    );
                r.getRouteur(connexionRouteurDestinataire.getText()).ajouterConnexion(
                        new Connexion(Integer.parseInt(distance.getText()),r.getRouteur(connexionRouteur.getText()),20)
                );
                repaint();
            }
        });

        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.setChemin(null);
                contentPane.removeAll();
                basicFrame();
            }
        });
    }

    public void basicFrame(){
        repaint();

        text.setColumns(14);
        connexionDebut.setColumns(14);
        connexionFin.setColumns(14);
        checkJson.setSelected(true);

        this.add(edit);
        edit.setBounds(10,10,80,25);


        this.add(checkJson);
        checkJson.setBounds(130, 10, 60, 30);
        checkJson.setSelected(true);

        this.add(checkTxt);
        checkTxt.setBounds(130, 35, 60, 30);

        this.add(plusCour);
        plusCour.setBounds(650,80,140,30);

        this.add(text);
        text.setBounds(200,25,140,30);

        this.add(send);
        send.setBounds(350,25,90,30);


        this.add(connexionFin);
        connexionFin.setBounds(500,35,140,30);

        this.add(connexionDebut);
        connexionDebut.setBounds(500,10,140,30);

        this.add(nbFrequence);
        nbFrequence.setBounds(500,80,140,30);

        this.add(dijkstra);
        dijkstra.setBounds(660,8,90,30);

        this.add(glouton);
        glouton.setBounds(660,37,90,30);

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

        glouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.setChemin(contentPane.getReseau().glouton(connexionDebut.getText(),connexionFin.getText(),Integer.parseInt(nbFrequence.getText()),plusCour.isSelected()));
                repaint();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.setChemin(null);
                contentPane.removeAll();
                editFrame();
            }
        });
    }
}