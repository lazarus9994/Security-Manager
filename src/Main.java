import collector.NetstatCollector;
import controller.MainController;
import gui.MainWindow;
import gui.panels.ConnectionPanel;
import gui.panels.ControlPanel;
import manager.ProcessManager;


public class Main {
    public static void main(String[] args){

        ProcessManager processManager = new ProcessManager();

        NetstatCollector collector = new NetstatCollector(processManager);

        ControlPanel controlPanel = new ControlPanel();
        ConnectionPanel connectionPanel = new ConnectionPanel();

        MainController mainController = new MainController(collector, connectionPanel.getConnectionTableModel(), controlPanel);
        MainWindow mainWindow = new MainWindow(connectionPanel, controlPanel);


    }
}
