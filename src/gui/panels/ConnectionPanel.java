package gui.panels;

import gui.components.ConnectionTable;
import gui.models.ConnectionTableModel;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel {
    private JPanel panel;
    private ConnectionTable connectionTable;


    public ConnectionPanel(){
        connectionTable = new ConnectionTable();
        initializePanel();
    }

    private void initializePanel(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(connectionTable.getScrollPane(), BorderLayout.CENTER);
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public ConnectionTableModel getConnectionTableModel(){
        return connectionTable.getConnectionTableModel();
    }

}
