package controller;

import collector.NetstatCollector;
import entity.NetstatSnapshot;
import gui.models.ConnectionTableModel;
import gui.panels.ControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

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
        SwingWorker<NetstatSnapshot, Void> swingWorker = new SwingWorker<NetstatSnapshot, Void>() {
            @Override
            protected NetstatSnapshot doInBackground() throws Exception {
                return netstatCollector.combineData();
            }
            @Override
            protected void done(){
                try {
                    NetstatSnapshot snapshot = get();
                    connectionTableModel.updateData(snapshot);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e){
                    e.printStackTrace();
                }

            }
        };
        swingWorker.execute();

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
