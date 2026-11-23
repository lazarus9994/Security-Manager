package gui.panels;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControlPanel {

    private JPanel controlPanel;
    private JButton snapshotButton;
    private JLabel timestampLabel;

    public ControlPanel(){
        initializeLabel();
        initializeControlPanel();
    }

    private void initializeControlPanel(){
        controlPanel = new JPanel();
        snapshotButton = new JButton("Collect Network snapshot");
        controlPanel.add(snapshotButton);
        controlPanel.add(timestampLabel);
    }

    private void initializeLabel(){
        this.timestampLabel = new JLabel();
        timestampLabel.setText("Last snapshot: none");
    }

    public JPanel getControlPanel(){
        return controlPanel;
    }

    public void addSnapshotActionListener(ActionListener actionListener){
        snapshotButton.addActionListener(actionListener);
    }

    public JButton getSnapshotButton(){
        return snapshotButton;
    }

    public void updateSnapshotTime(LocalDateTime timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        timestampLabel.setText("Last snapshot: " + timestamp.format(formatter));
    }
}
