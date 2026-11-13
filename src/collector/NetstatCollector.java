package collector;


import entity.NetstatResult;
import entity.NetstatSnapshot;
import manager.ProcessManager;
import mapper.NetstatMapper;
import mapper.TasklistMapper;
import process.ManagedProcess;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NetstatCollector {

    private ProcessManager processManager;
    private ManagedProcess netstat;
    private ManagedProcess tasklist;
    private NetstatMapper netstatMapper;
    private TasklistMapper tasklistMapper;
    private NetstatSnapshot netstatSnapshot;
    private Map<Integer, String> pidNameMap;

    public NetstatCollector(ProcessManager processManager) {
        this.processManager = processManager;
        this.netstatSnapshot = new NetstatSnapshot(LocalDateTime.now());
    }

    public boolean startProcesses(){
        this.netstat = new ManagedProcess("netstat", "netstat", List.of("-ano").toArray(new String[1]));
        this.tasklist = new ManagedProcess("tasklist", "tasklist", Collections.emptyList().toArray(new String[0]));
        processManager.addProcess(netstat);
        processManager.addProcess(tasklist);
        try {
            processManager.startProcess(netstat);
            processManager.startProcess(tasklist);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        try {
            processManager.waitForAllProcesses();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return true;
    }

    public void collectData(){

        if (startProcesses()) {
            this.netstatMapper = new NetstatMapper(netstat);
            this.tasklistMapper = new TasklistMapper(tasklist);
            this.netstatMapper.addNetstatConnections();
            this.pidNameMap = this.tasklistMapper.getPidNameMap();
        }else {
            System.out.println("Method collectData() could not collect data. Method startProcesses() returned false");
            throw new IllegalStateException("Method collectData() could not collect data.");
        }

    }

    public NetstatSnapshot combineData(){
        collectData();

        for (Map.Entry<Integer, String> entry : this.pidNameMap.entrySet()) {
            for (int i = 0; i < this.netstatMapper.getNetstatConnectionList().size(); i++) {
                if(entry.getKey().equals(this.netstatMapper.getNetstatConnectionList().get(i).getPid())){
                    NetstatResult result = new NetstatResult(entry.getValue(), this.netstatMapper.getNetstatConnectionList().get(i));
                    netstatSnapshot.addConnection(result);
                }
            }
        }

        return netstatSnapshot;
    }


}
