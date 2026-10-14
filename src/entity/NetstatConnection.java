package entity;

public class NetstatConnection {

    private int pid;
    private Protocol protocol;
    private String localIp;
    private int localPort;
    private String foreignIp;
    private int foreignPort;
    private State state;

    public NetstatConnection(int pid, Protocol protocol, String localIp, int localPort, String foreignIp, int foreignPort, State state) {
        this.pid = pid;
        this.protocol = protocol;
        this.localIp = localIp;
        this.localPort = localPort;
        this.foreignIp = foreignIp;
        this.foreignPort = foreignPort;
        setState(state);
    }

    public int getPid() {
        return pid;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getLocalIp() {
        return localIp;
    }

    public int getLocalPort() {
        return localPort;
    }

    public String getForeignIp() {
        return foreignIp;
    }

    public int getForeignPort() {
        return foreignPort;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (this.protocol == Protocol.TCP) {
            this.state = state;
        }else if (this.protocol == Protocol.UDP) {
            this.state = State.STATELESS;
        }
    }
}
