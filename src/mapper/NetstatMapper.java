package mapper;

import entity.NetstatConnection;
import entity.Protocol;
import entity.State;
import process.ManagedProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NetstatMapper {

    private List<NetstatConnection> netstatConnectionList;
    private ManagedProcess process;

    public NetstatMapper(ManagedProcess process) {
        this.process = process;
        netstatConnectionList = new ArrayList<NetstatConnection>();
    }

    public List<NetstatConnection> getNetstatConnectionList() {
        return netstatConnectionList;
    }

    public void addNetstatConnections() {
        String[] data = process.getOutput().split("\n");

        String protocol = "";
        String localAddress = "";
        int localPort = 0;
        String foreignAddress = "";
        int foreignPort = 0;
        String state = "";
        int pid = 0;

        for (int i = 3; i < data.length; i++) {
            data[i] = data[i].trim();
            String[] column = data[i].split("\\s{2,}");

            NetstatConnection netstatConnection;

            protocol = column[0];

            if (protocol.equals("TCP")) {
                localAddress = column[1];
                localPort = Integer.parseInt(localAddress.substring(localAddress.lastIndexOf(':') + 1));
                localAddress = localAddress.substring(0, localAddress.lastIndexOf(':'));
                foreignAddress = column[2];
                foreignPort = Integer.parseInt(foreignAddress.substring(foreignAddress.lastIndexOf(':') + 1));
                foreignAddress = foreignAddress.substring(0, foreignAddress.lastIndexOf(':'));
                state = column[3];
                pid = Integer.parseInt(column[4]);

                netstatConnection = new NetstatConnection(pid, Protocol.valueOf(protocol.toUpperCase()),localAddress, localPort, foreignAddress, foreignPort, State.valueOf(state.toUpperCase()));
                netstatConnectionList.add(netstatConnection);
            }

            if (protocol.equals("UDP")) {
                localAddress = column[1];
                localPort = Integer.parseInt(localAddress.substring(localAddress.lastIndexOf(':') + 1));
                localAddress = localAddress.substring(0, localAddress.lastIndexOf(':'));
                foreignAddress = column[2];
                state = "STATELESS";
                pid = Integer.parseInt(column[3]);

                netstatConnection = new NetstatConnection(pid, Protocol.valueOf(protocol.toUpperCase()), localAddress, localPort, "", 0, State.valueOf(state.toUpperCase()));
                netstatConnectionList.add(netstatConnection);
            }



        }
    }
}
