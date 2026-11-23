package analyzer;

import entity.NetstatConnection;
import entity.NetstatResult;
import entity.NetstatSnapshot;
import entity.ProcessAnalysisResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessAnalyzer {



    public List<ProcessAnalysisResult> analyze(NetstatSnapshot previous, NetstatSnapshot current){
        List<NetstatResult> previousNetstatResults = previous.getConnections();

        List<NetstatResult> currentNetstatResults = current.getConnections();

        List<ProcessAnalysisResult> processAnalysisResults = new ArrayList<>();

        Map<Integer, List<NetstatResult>> previousGroupByPid = new HashMap<>();
        Map<Integer, List<NetstatResult>> currentGroupByPid = new HashMap<>();

        for (NetstatResult result : previousNetstatResults){
            if(!previousGroupByPid.containsKey(result.getPid())){
                previousGroupByPid.put(result.getPid(), new ArrayList<>());
                previousGroupByPid.get(result.getPid()).add(result);
            }else previousGroupByPid.get(result.getPid()).add(result);

        }

        for (NetstatResult result : currentNetstatResults){
            if(!currentGroupByPid.containsKey(result.getPid())){
                currentGroupByPid.put(result.getPid(), new ArrayList<>());
                currentGroupByPid.get(result.getPid()).add(result);
            }else currentGroupByPid.get(result.getPid()).add(result);
        }

        for(Map.Entry<Integer, List<NetstatResult>> entryCurrent : currentGroupByPid.entrySet()){
            List<NetstatResult> addedConnections = new ArrayList<>();
            List<NetstatResult> removedConnections = new ArrayList<>();

            List<NetstatResult> previousConnections =
                    previousGroupByPid.get(entryCurrent.getKey());

            if (previousConnections == null){
                addedConnections.addAll(entryCurrent.getValue());
            } else {
                for (NetstatResult currentConnection : entryCurrent.getValue()){
                    boolean found = false;
                    for(NetstatResult previousConnection : previousConnections){
                        if(isSameConnection(currentConnection, previousConnection)){
                            found = true;
                            break;
                        }

                    }
                    if (!found){
                        addedConnections.add(currentConnection);
                    }
                }

                for (NetstatResult previousConnection : previousConnections){
                    boolean found = false;
                    for (NetstatResult currentConnection : entryCurrent.getValue()){
                        if (isSameConnection(currentConnection, previousConnection)){
                            found = true;
                            break;
                        }
                    }

                    if(!found){
                        removedConnections.add(previousConnection);
                    }
                }
            }

            ProcessAnalysisResult processAnalysisResult = new ProcessAnalysisResult(
                    entryCurrent.getValue().getFirst().getProcessName(),
                    entryCurrent.getKey(),
                    addedConnections,
                    removedConnections
            );
            processAnalysisResults.add(processAnalysisResult);
        }

        for (Map.Entry<Integer, List<NetstatResult>> entryPrevious : previousGroupByPid.entrySet()){

            if(!currentGroupByPid.containsKey(entryPrevious.getKey())){
                List<NetstatResult> removedConnections = new ArrayList<>();
                removedConnections.addAll(entryPrevious.getValue());

                ProcessAnalysisResult processAnalysisResult = new ProcessAnalysisResult(
                        entryPrevious.getValue().getFirst().getProcessName(),
                        entryPrevious.getKey(),
                        new ArrayList<>(),
                        removedConnections


                );

                processAnalysisResults.add(processAnalysisResult);
            }
        }

        return processAnalysisResults;
    }

    private boolean isSameConnection(NetstatResult first, NetstatResult second){
        return first.getPid() == second.getPid() &&
                first.getLocalPort() == second.getLocalPort() &&
                first.getForeignPort() == second.getForeignPort() &&
                first.getProtocol().equals(second.getProtocol()) &&
                first.getLocalAddress().equals(second.getLocalAddress()) &&
                first.getForeignAddress().equals(second.getForeignAddress());
    }
}
