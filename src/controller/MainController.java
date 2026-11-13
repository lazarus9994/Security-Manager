package controller;

import collector.NetstatCollector;
import entity.NetstatSnapshot;
import gui.models.ConnectionTableModel;
import gui.panels.ControlPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private NetstatCollector netstatCollector;
    private ConnectionTableModel connectionTableModel;
    private ControlPanel controlPanel;

    public MainController(NetstatCollector netstatCollector, ConnectionTableModel connectionTableModel, ControlPanel controlPanel){
        this.netstatCollector = netstatCollector;
        this.connectionTableModel = connectionTableModel;
        this.controlPanel = controlPanel;
        initializeListeners();
    }

    public void collectNetworkSnapshot(){
        NetstatSnapshot snapshot = netstatCollector.combineData();
        connectionTableModel.updateData(snapshot);
    }

    private void initializeListeners(){
        controlPanel.addSnapshotActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectNetworkSnapshot();
            }
        });
    }
}
