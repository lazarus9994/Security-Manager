package mapper;

import process.ManagedProcess;

import java.util.HashMap;
import java.util.Map;

public class TasklistMapper {

    private Map<Integer, String> pidNameMap;
    private ManagedProcess process;

    public TasklistMapper(ManagedProcess process) {
        this.process = process;
        this.pidNameMap = new HashMap<Integer, String>();
    }

    public Map<Integer, String> getPidNameMap() {
        String[] data = process.getOutput().split("\n");
        int pid = 0;
        String name = "";

        for (int i = 3; i < data.length; i++) {
            String[] column = data[i].split("\\s{2,}");
            name = column[0];
            pid = Integer.parseInt(column[1].substring(0, column[1].indexOf(" ")));
            pidNameMap.put(pid, name);
        }
        return pidNameMap;
    }
}
