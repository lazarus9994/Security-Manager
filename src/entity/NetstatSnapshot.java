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

    public void printConnections(){
        System.out.println(getTimestamp());
        System.out.println("Connections:");
        for (NetstatResult netstatResult : connections){
            System.out.println(netstatResult.getPid());
            System.out.println(netstatResult.getProcessName());
            System.out.println(netstatResult.getLocalAddress() + " " + netstatResult.getLocalPort());
            System.out.println(netstatResult.getForeignAddress() + " " + netstatResult.getForeignPort());
        }
    }
}
