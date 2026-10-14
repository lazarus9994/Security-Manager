package manager;

import process.ManagedProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

    private List<ManagedProcess> processList;

    public ProcessManager() {
        processList = new ArrayList<>();
    }


    public void addProcess(ManagedProcess process){
        this.processList.add(process);
    }

    public void startProcess(ManagedProcess process) throws IOException {
        process.start();
        process.waitForCompletion();
    }

    public void waitForAllProcesses() throws InterruptedException {

        for (ManagedProcess process : processList) {
            process.waitForProcess();
        }
    }

}
