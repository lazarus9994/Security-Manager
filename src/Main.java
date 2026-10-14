import manager.ProcessManager;
import process.ManagedProcess;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessManager pm = new ProcessManager();

        ManagedProcess process1 = new ManagedProcess("cmd", "cmd", List.of("/c", "timeout", "/t", "5", "/nobreak").toArray(new String[3]));
        ManagedProcess process2 = new ManagedProcess("cmd", "cmd", List.of("/c", "timeout", "/t", "8", "/nobreak").toArray(new String[3]));
        ManagedProcess process3 = new ManagedProcess("cmd", "cmd", List.of("/c", "timeout", "/t", "12", "/nobreak").toArray(new String[3]));

        pm.addProcess(process1);
        pm.addProcess(process2);
        pm.addProcess(process3);

        pm.startProcess(process1);
        pm.startProcess(process2);
        pm.startProcess(process3);

        pm.waitForAllProcesses();

        System.out.println(process1.getState());
        System.out.println(process1.getExitCode());

        System.out.println(process2.getState());
        System.out.println(process2.getExitCode());

        System.out.println(process3.getState());
        System.out.println(process3.getExitCode());

    }
}
