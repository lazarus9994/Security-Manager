package analyzer;

import entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProcessAnalyzerTest {

    private List<NetstatResult> previousConnections;
    private List<NetstatResult> currentConnections;

    public ProcessAnalyzerTest(){
        this.previousConnections = new ArrayList<>();
        this.currentConnections = new ArrayList<>();
    }

    private void prepareTestData(){
        NetstatConnection netstatConnectionА = new NetstatConnection(1000, Protocol.TCP, "127.0.0.1", 8080, "127.0.0.1", 50000, State.ESTABLISHED);
        NetstatResult netstatResultA = new NetstatResult("TestProcess.exe", netstatConnectionА);

        previousConnections.add(netstatResultA);
        currentConnections.add(netstatResultA);

        NetstatConnection netstatConnectionB = new NetstatConnection(1000, Protocol.TCP, "127.0.0.1", 8081, "127.0.0.1", 50001, State.ESTABLISHED);
        NetstatResult netstatResultB = new NetstatResult("TestProcess.exe", netstatConnectionB);

        previousConnections.add(netstatResultB);

        NetstatConnection netstatConnectionC = new NetstatConnection(1000, Protocol.TCP, "127.0.0.1", 8082, "127.0.0.1", 50002, State.ESTABLISHED);
        NetstatResult netstatResultC = new NetstatResult("TestProcess.exe", netstatConnectionC);

        currentConnections.add(netstatResultC);

        NetstatConnection netstatConnectionD = new NetstatConnection(2000, Protocol.TCP, "127.0.0.1", 9090, "127.0.0.1", 50003, State.ESTABLISHED);
        NetstatResult netstatResultD = new NetstatResult("SecondProcess.exe", netstatConnectionD);

        previousConnections.add(netstatResultD);

    }

    public void runTest(){

        prepareTestData();

        NetstatSnapshot previousSnapshot = new NetstatSnapshot(LocalDateTime.now());
        for (NetstatResult netstatResult : previousConnections){
            previousSnapshot.addConnection(netstatResult);
        }

        NetstatSnapshot currentSnapshot = new NetstatSnapshot(LocalDateTime.now());
        for (NetstatResult netstatResult : currentConnections){
            currentSnapshot.addConnection(netstatResult);
        }

        ProcessAnalyzer processAnalyzer = new ProcessAnalyzer();
        List<ProcessAnalysisResult> processAnalysisResult = processAnalyzer.analyze(previousSnapshot, currentSnapshot);

        processAnalysisResult.forEach(p -> System.out.println(p.getProcessName()));
        processAnalysisResult.forEach(p -> System.out.println(p.getAddedConnections()));
        processAnalysisResult.forEach(p -> System.out.println(p.getRemovedConnections()));

    }

}
