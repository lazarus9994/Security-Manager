package entity;

import java.time.LocalDateTime;
import java.util.List;

public class NetstatSnapshot {

    private LocalDateTime timestamp;
    private List<NetstatConnection> connections;

    public NetstatSnapshot(LocalDateTime timestamp, List<NetstatConnection> connections) {
        this.timestamp = timestamp;
        this.connections = connections;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<NetstatConnection> getConnections() {
        return connections;
    }
}
