package entity;

public class NetstatResult {

    private String processName;
    private NetstatConnection connection;

    public NetstatResult(String processName, NetstatConnection connection) {
        this.processName = processName;
        this.connection = connection;
    }

    public String getProcessName() {
        return processName;
    }

    public String getLocalAddress() {
        return connection.getLocalIp();
    }

    public int getLocalPort() {
        return connection.getLocalPort();
    }

    public String getForeignAddress() {
        return connection.getForeignIp();
    }

    public int getForeignPort() {
        return connection.getForeignPort();
    }

    public State getState() {
        return connection.getState();
    }

    public int getPid(){
        return connection.getPid();
    }

    public Protocol getProtocol(){
        return connection.getProtocol();
    }
}
