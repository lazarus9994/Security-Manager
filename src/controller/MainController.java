package controller;

import collector.NetstatCollector;
import entity.NetstatSnapshot;
import gui.models.ConnectionTableModel;
import gui.panels.ControlPanel;
import repository.SnapshotRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class MainController {

    private NetstatCollector netstatCollector;
    private ConnectionTableModel connectionTableModel;
    private ControlPanel controlPanel;
    private SnapshotRepository snapshotRepository;

    public MainController(NetstatCollector netstatCollector, ConnectionTableModel connectionTableModel, ControlPanel controlPanel, SnapshotRepository snapshotRepository){
        this.netstatCollector = netstatCollector;
        this.connectionTableModel = connectionTableModel;
        this.controlPanel = controlPanel;
        this.snapshotRepository = snapshotRepository;
        initializeListeners();
    }

    public void collectNetworkSnapshot(){
        SwingWorker<NetstatSnapshot, Void> swingWorker = new SwingWorker<NetstatSnapshot, Void>() {
            @Override
            protected NetstatSnapshot doInBackground() throws Exception {
                NetstatSnapshot netstatSnapshot = netstatCollector.combineData();
                snapshotRepository.save(netstatSnapshot);
                return netstatSnapshot;
            }
            @Override
            protected void done(){
                try {
                    NetstatSnapshot snapshot = get();
                    connectionTableModel.updateData(snapshot);
                    controlPanel.updateSnapshotTime(snapshot.getTimestamp());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e){
                    e.printStackTrace();
                }finally {
                    controlPanel.getSnapshotButton().setEnabled(true);
                }

            }
        };
        controlPanel.getSnapshotButton().setEnabled(false);
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
