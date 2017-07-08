package com.severett.moveyourfeet.assignments;

import com.severett.moveyourfeet.config.SchedulerConfig;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;

public class BuildingAssigner {
    
    public BuildingAssigner() {
    }
    
    public JSONArray generateBuildingAssignments(SchedulerConfig config) {
        List<Integer> roomsList = config.getRoomsList();
        final int seniorCapacity = config.getSeniorCapacity();
        final int juniorCapacity = config.getJuniorCapacity();
        return new JSONArray(roomsList.stream()
                .map(roomAmt -> determineBuildingAssignment(roomAmt, seniorCapacity, juniorCapacity))
                .collect(Collectors.toList())
        );
    }
    
    private BuildingAssignment determineBuildingAssignment(int roomAmt, int seniorCapacity, int juniorCapacity) {
        roomAmt += 1;
        int onlySeniorAmt = (int) Math.ceil((double) roomAmt / seniorCapacity);
        int finalSeniorAmt = onlySeniorAmt;
        int finalJuniorAmt = 0;
        int bestCrewCapacity = onlySeniorAmt * seniorCapacity;
        for (int seniorAmt = (onlySeniorAmt - 1); seniorAmt > 0 && bestCrewCapacity > roomAmt; seniorAmt--) {
            int seniorGivenCapacity = seniorAmt * seniorCapacity;
            int juniorReqCapacity = roomAmt - seniorGivenCapacity;
            int juniorAmt = (int) Math.ceil((double) juniorReqCapacity / juniorCapacity);
            int juniorGivenCapacity = juniorAmt * juniorCapacity;
            int crewCapacity = seniorGivenCapacity + juniorGivenCapacity;
            if (bestCrewCapacity > crewCapacity) {
                bestCrewCapacity = crewCapacity;
                finalSeniorAmt = seniorAmt;
                finalJuniorAmt = juniorAmt;
            }
        }
        return new BuildingAssignment(finalSeniorAmt, finalJuniorAmt);
    }
    
}
