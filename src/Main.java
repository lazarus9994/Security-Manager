import manager.ProcessManager;
import process.ManagedProcess;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessManager pm = new ProcessManager();

        ManagedProcess process = new ManagedProcess("cmd", "cmd", List.of("/c", "echo", "Hello SecurityMonitor").toArray(new String[3]));
        pm.addProcess(process);
        pm.startProcess(process);

    }
}
