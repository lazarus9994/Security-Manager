package controller;

import collector.NetstatCollector;
import entity.NetstatSnapshot;
import gui.models.ConnectionTableModel;
import gui.panels.ControlPanel;

public class MainController {

    private NetstatCollector netstatCollector;
    private ConnectionTableModel connectionTableModel;

    public MainController(NetstatCollector netstatCollector, ConnectionTableModel connectionTableModel){
        this.netstatCollector = netstatCollector;
        this.connectionTableModel = connectionTableModel;
    }

    public void collectNetworkSnapshot(){
        NetstatSnapshot snapshot = netstatCollector.combineData();
        connectionTableModel.updateData(snapshot);
    }
}
