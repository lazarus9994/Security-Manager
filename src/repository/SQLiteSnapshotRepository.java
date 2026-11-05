package repository;

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

        try(PreparedStatement statement = connection.prepareStatement(insertStatement, PreparedStatement.RETURN_GENERATED_KEYS)){

            statement.setString(1, timestamp);
            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getLong(1));
                }
            }catch (SQLException e){
                e.printStackTrace();
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
