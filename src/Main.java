import database.DatabaseConnection;
import entity.*;
import manager.ProcessManager;
import repository.SQLiteSnapshotRepository;
import repository.SnapshotRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

            if(netstatSnapshotOptional.isPresent()){
                NetstatSnapshot netstatSnapshot = netstatSnapshotOptional.get();
                System.out.println(netstatSnapshot.getTimestamp());
                netstatSnapshot.printConnections();
            }
            System.out.println(netstatSnapshotOptional);
        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
