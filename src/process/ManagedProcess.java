package process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagedProcess {

    private String processName;
    private String command;
    private String[] arguments;
    private long pid;
    private ProcessState state;
    private int exitCode;
    private Process process;
    private Thread thread;

    public ManagedProcess(String processName, String command, String[] arguments) {
        this.processName = processName;
        this.command = command;
        this.arguments = arguments;
        this.pid = 0;
        this.state = ProcessState.INIT;
        this.exitCode = -1;
    }

    public String getProcessName() {
        return processName;
    }

    public String getCommand() {
        return command;
    }


    public String[] getArguments() {
        return arguments;
    }

    public long getPid() {
        return pid;
    }

    public ProcessState getState() {
        return state;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void start() throws IOException {
        List<String> params = new ArrayList<>(Arrays.asList(arguments));
        params.addFirst(command);
        ProcessBuilder pb = new ProcessBuilder(params);
        this.process = pb.start();

        this.state = ProcessState.RUNNING;

        this.pid = this.process.pid();
    }

    public void waitForCompletion() {
        Runnable waitForProcess = () -> {
            try {
                this.exitCode = process.waitFor();
                this.state = ProcessState.FINISHED;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        this.thread = new Thread(waitForProcess);
        thread.start();
    }

    public void waitForProcess() throws InterruptedException {
        if (this.state == ProcessState.INIT) {
            System.out.println("Process not started");
        }else this.thread.join();
    }
}
