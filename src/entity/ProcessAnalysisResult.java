package entity;

import java.util.List;

public class ProcessAnalysisResult {

    private String processName;
    private int processPid;
    private List<NetstatResult> addedConnections;
    private List<NetstatResult> removedConnections;

    public ProcessAnalysisResult(String processName, int processPid, List<NetstatResult> addedConnections, List<NetstatResult> removedConnections) {
        this.processName = processName;
        this.processPid = processPid;
        this.addedConnections = addedConnections;
        this.removedConnections = removedConnections;
    }

    public String getProcessName() {
        return processName;
    }

    public int getProcessPid() {
        return processPid;
    }

    public List<NetstatResult> getAddedConnections() {
        return addedConnections;
    }

    public List<NetstatResult> getRemovedConnections() {
        return removedConnections;
    }
}
