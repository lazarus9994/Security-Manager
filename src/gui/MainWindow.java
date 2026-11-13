package gui;


import gui.panels.ConnectionPanel;
import gui.panels.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame mainFrame;
    private ConnectionPanel connectionPanel;
    private ControlPanel controlPanel;

    public MainWindow(ConnectionPanel connectionPanel, ControlPanel controlPanel){
        this.connectionPanel = connectionPanel;
        this.controlPanel = controlPanel;
        initializeFrame();
    }

    private void initializeFrame(){
        JPanel connPanel  = connectionPanel.getPanel();
        JPanel contPanel = controlPanel.getControlPanel();

        mainFrame = new JFrame();
        mainFrame.getContentPane().setLayout(new BorderLayout());


        mainFrame.add(connPanel, BorderLayout.CENTER);
        mainFrame.add(contPanel, BorderLayout.SOUTH);
        mainFrame.setTitle("Security Manager");
        mainFrame.setSize(1000, 600);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


}
