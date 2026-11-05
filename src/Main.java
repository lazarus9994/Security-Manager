import entity.NetstatCollector;
import entity.NetstatSnapshot;
import manager.ProcessManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessManager pm = new ProcessManager();

        NetstatCollector collector = new NetstatCollector(pm);

        NetstatSnapshot snapshot = collector.combineData();


        snapshot.printConnections();



    }
}
