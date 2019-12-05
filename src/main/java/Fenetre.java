import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Fenetre extends JFrame {
    private Panneau contentPane = new Panneau();

    Fenetre(){
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setContentPane(contentPane);
        contentPane.setBackground(Color.LIGHT_GRAY);
        go();
        this.setVisible(true);
    }

    void go(){
        final JTextField text = new JTextField("Reseau.json");
        text.setColumns(14);
        final JButton btn = new JButton("Envoyer");
        final JCheckBox checkJson = new JCheckBox("Json");
        final JCheckBox checkTxt = new JCheckBox("Txt");

        contentPane.setLayout(new FlowLayout());
        contentPane.add(checkTxt);
        contentPane.add(checkJson);
        contentPane.add(text);
        contentPane.add(btn);


        checkJson.setSelected(true);




        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((checkJson.isSelected())) {
                    contentPane.setReseau(GenerationGraphe.genererJSON(text.getText()));
                }
                else {
                    if ((checkTxt.isSelected())) {
                        contentPane.setReseau(GenerationGraphe.genererTXT(text.getText(), 10));
                    }
                }
                Graphics g = contentPane.getGraphics();
                contentPane.genererReseau(g);
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
                text.setText("FilePath to *.txt");
            }
        });
    }
}