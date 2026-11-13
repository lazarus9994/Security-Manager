package gui.panels;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ControlPanel {

    private JPanel controlPanel;
    private JButton snapshotButton;

    public ControlPanel(){
        initializeControlPanel();
    }

    private void initializeControlPanel(){
        controlPanel = new JPanel();
        snapshotButton = new JButton("Collect Network snapshot");
        controlPanel.add(snapshotButton);
    }

    public JPanel getControlPanel(){
        return controlPanel;
    }

    public void addSnapshotActionListener(ActionListener actionListener){
        snapshotButton.addActionListener(actionListener);
    }
}
