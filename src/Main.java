import analyzer.ProcessAnalyzerTest;
import collector.NetstatCollector;
import controller.MainController;
import database.DatabaseConnection;
import entity.NetstatSnapshot;
import gui.MainWindow;
import gui.panels.ConnectionPanel;
import gui.panels.ControlPanel;
import manager.ProcessManager;
import repository.SQLiteSnapshotRepository;
import repository.SnapshotRepository;

import javax.swing.*;
import java.util.List;


public class Main {
    public static void main(String[] args){

        ProcessManager processManager = new ProcessManager();

        NetstatCollector collector = new NetstatCollector(processManager);

        DatabaseConnection databaseConnection = new DatabaseConnection();
        SnapshotRepository snapshotRepository = new SQLiteSnapshotRepository(databaseConnection.connect());


        ProcessAnalyzerTest processAnalyzerTest = new ProcessAnalyzerTest();
        processAnalyzerTest.runTest();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                ControlPanel controlPanel = new ControlPanel();
                ConnectionPanel connectionPanel = new ConnectionPanel();

                MainController mainController = new MainController(collector, connectionPanel.getConnectionTableModel(), controlPanel, snapshotRepository);
                MainWindow mainWindow = new MainWindow(connectionPanel, controlPanel);
            }
        });


    }
}
