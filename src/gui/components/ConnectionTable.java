package gui.components;

import gui.models.ConnectionTableModel;

import javax.swing.*;

public class ConnectionTable {

    private JTable connectionTable;
    private JScrollPane scrollPane;
    private ConnectionTableModel connectionTableModel;

    public ConnectionTable(){
        connectionTableModel = new ConnectionTableModel();

        initializeTable();
        initializeScrollPane();
    }

    private void initializeTable(){
        connectionTable = new JTable();
        connectionTable.setModel(connectionTableModel);
    }

    private void initializeScrollPane(){
        scrollPane = new JScrollPane(connectionTable);
    }

    public JScrollPane getScrollPane(){
        return scrollPane;
    }

    public ConnectionTableModel getConnectionTableModel(){
        return connectionTableModel;
    }
}
