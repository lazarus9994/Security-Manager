package repository;

import entity.NetstatResult;
import entity.NetstatSnapshot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SQLiteSnapshotRepository implements SnapshotRepository {

    private final Connection connection;

    public SQLiteSnapshotRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(NetstatSnapshot snapshot) {
        String timestamp = snapshot.getTimestamp().toString();
        String insertStatement = "INSERT INTO netstat_snapshots (timestamp) VALUES(?)";

        long snapshot_id = -1;

        try(PreparedStatement statement = connection.prepareStatement(insertStatement, PreparedStatement.RETURN_GENERATED_KEYS)){

            statement.setString(1, timestamp);
            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    snapshot_id = resultSet.getLong(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        if(snapshot_id == -1){
            System.out.println("Invalid snapshot_id value");
            return;
        }

        String netstatResultInsertStatement = "INSERT INTO netstat_results" +
                " (snapshot_id, process_name, local_address, local_port, foreign_address, foreign_port, state, pid, protocol)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement resultsStatement = connection.prepareStatement(netstatResultInsertStatement)){

            for (NetstatResult netstatResult : snapshot.getConnections()){
                resultsStatement.setLong(1 , snapshot_id);
                resultsStatement.setString(2, netstatResult.getProcessName());
                resultsStatement.setString(3, netstatResult.getLocalAddress());
                resultsStatement.setInt(4, netstatResult.getLocalPort());
                resultsStatement.setString(5, netstatResult.getForeignAddress());
                resultsStatement.setInt(6, netstatResult.getForeignPort());
                resultsStatement.setString(7, netstatResult.getState().toString().toUpperCase());
                resultsStatement.setInt(8, netstatResult.getPid());
                resultsStatement.setString(9, netstatResult.getProtocol().toString().toUpperCase());

                resultsStatement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Optional<NetstatSnapshot> findById(long id) {
        return Optional.empty();
    }
}
