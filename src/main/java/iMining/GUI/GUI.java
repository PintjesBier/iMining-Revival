package iMining.GUI;

import iMining.core.Core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tristan on 4/09/2018.
 */

public class GUI extends JFrame {
    private JPanel panelMain;
    private JLabel lblTitel;
    private JLabel lblSubTitle;
    private JComboBox cmbOre;
    private JLabel lblOre;
    private JButton btnStart;

    public GUI() {
        setTitle("Configure script");
        setContentPane(panelMain);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.GUIore = cmbOre.getSelectedItem().toString();
                dispose();
            }
        });
    }
}
