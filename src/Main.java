import manager.ProcessManager;
import mapper.TasklistMapper;
import process.ManagedProcess;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessManager pm = new ProcessManager();

        //ManagedProcess process1 = new ManagedProcess("netstat", "netstat", List.of("-ano").toArray(new String[1]));
        ManagedProcess process2 = new ManagedProcess("tasklist", "tasklist", Collections.emptyList().toArray(new String[0]));
        pm.addProcess(process2);

        pm.startProcess(process2);


        pm.waitForAllProcesses();


        System.out.println(process2.getState());
        System.out.println(process2.getExitCode());


        System.out.println(process2.getOutput());

        TasklistMapper tasklistMapper = new TasklistMapper(process2);

        Map<Integer, String> pidNameMap = tasklistMapper.getPidNameMap();

        for (Map.Entry<Integer, String> entry : pidNameMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
