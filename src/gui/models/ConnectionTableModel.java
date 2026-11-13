package gui.models;

import entity.NetstatSnapshot;

import javax.swing.table.AbstractTableModel;

public class ConnectionTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Process Name", "PID", "Protocol", "Local Address", "Local Port", "Foreign Address", "Foreign Port", "State"};

    private NetstatSnapshot snapshot;

    public ConnectionTableModel(){
    }

    @Override
    public int getRowCount() {
        if(snapshot == null){
            return 0;
        }else return snapshot.getConnections().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(snapshot == null){
            return 0;
        }
        switch (columnIndex){
            case 0 : return snapshot.getConnections().get(rowIndex).getProcessName();
            case 1 : return snapshot.getConnections().get(rowIndex).getPid();
            case 2 : return snapshot.getConnections().get(rowIndex).getProtocol();
            case 3 : return snapshot.getConnections().get(rowIndex).getLocalAddress();
            case 4 : return snapshot.getConnections().get(rowIndex).getLocalPort();
            case 5 : return snapshot.getConnections().get(rowIndex).getForeignAddress();
            case 6 : return snapshot.getConnections().get(rowIndex).getForeignPort();
            case 7 : return snapshot.getConnections().get(rowIndex).getState();
            default : throw new IllegalArgumentException("Invalid input");

        }
    }

    @Override
    public String getColumnName(int index){
        return COLUMN_NAMES[index];
    }

    public void updateData(NetstatSnapshot snapshot){
        this.snapshot = snapshot;
        fireTableDataChanged();
    }
}
