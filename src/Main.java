import collector.NetstatCollector;
import database.DatabaseConnection;
import entity.*;
import gui.MainWindow;
import manager.ProcessManager;
import repository.SQLiteSnapshotRepository;
import repository.SnapshotRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessManager processManager = new ProcessManager();

        NetstatCollector collector = new NetstatCollector(processManager);

        NetstatSnapshot snapshot = collector.combineData();

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try(Connection connection = databaseConnection.connect()){
            System.out.println("Connected to database");

            SnapshotRepository repo = new SQLiteSnapshotRepository(connection);

            repo.save(snapshot);

        }catch (SQLException e){
            e.printStackTrace();
        }

        try(Connection connection = databaseConnection.connect()){
            SnapshotRepository repo = new SQLiteSnapshotRepository(connection);
            Optional<NetstatSnapshot> netstatSnapshotOptional = repo.findById(6);

            netstatSnapshotOptional.ifPresent(NetstatSnapshot::printConnections);

        }catch (SQLException e){
            e.printStackTrace();
        }

        MainWindow mainWindow = new MainWindow();




    }
}
