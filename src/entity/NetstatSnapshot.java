package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NetstatSnapshot {

    private LocalDateTime timestamp;
    private List<NetstatResult> connections;

    public NetstatSnapshot(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.connections = new ArrayList<>();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<NetstatResult> getConnections() {
        return connections;
    }

    public void addConnection(NetstatResult netstatResult){
        connections.add(netstatResult);
    }

    //TODO
    public void printConnections(){
        String separator = "    |   ";
        StringBuilder builder = new StringBuilder();
        builder.append("Process name");
        builder.append(separator);
        builder.append("PID");
        builder.append(separator);
        builder.append("Local Address");
        builder.append(separator);
        builder.append("Local Port");
        builder.append(separator);
        builder.append("Foreign Address");
        builder.append(separator);
        builder.append("Foreign Port");
        builder.append(separator);
        builder.append("Protocol");
        builder.append(separator);
        builder.append("State");
        builder.append(separator);
        builder.append("\n");
        System.out.println("Connections:");
        System.out.println(builder);
        for (NetstatResult netstatResult : connections){
            System.out.println(netstatResult.toString());
        }

    }
}
