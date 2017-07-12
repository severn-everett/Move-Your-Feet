package com.severett.moveyourfeet.assignments;

import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import com.severett.moveyourfeet.config.SchedulerConfigParser;
import com.severett.moveyourfeet.config.SchedulerConfiguration;

/**
 * Singleton class that takes in a SchedulerConfig object and
 * returns a list of building assignments
 * @author Severn Everett
 */
public class BuildingAssigner {
    
    private static BuildingAssigner instance;
    
    public static BuildingAssigner getInstance() {
        if (instance == null) {
            instance = new BuildingAssigner();
        }
        return instance;
    }
    
    private BuildingAssigner() {
    }
    
    public JSONArray generateBuildingAssignments(SchedulerConfiguration config) {
        List<Integer> roomsList = config.getRoomsList();
        final int seniorCapacity = config.getSeniorCapacity();
        final int juniorCapacity = config.getJuniorCapacity();
        return new JSONArray(roomsList.stream()
                .map(roomAmt -> determineBuildingAssignment(roomAmt, seniorCapacity, juniorCapacity))
                .collect(Collectors.toList())
        );
    }
    
    private BuildingAssignment determineBuildingAssignment(int roomAmt, int seniorCapacity, int juniorCapacity) {
        // We're looking to be just under full capacity for the building crew, so set
        // the room amount target to one above the actual room amount
        roomAmt += 1;
        
        // Determine the crew capacity if we are only using senior crewmembers
        int onlySeniorAmt = (int) Math.ceil((double) roomAmt / seniorCapacity);
        int finalSeniorAmt = onlySeniorAmt;
        int finalJuniorAmt = 0;
        int bestCrewCapacity = onlySeniorAmt * seniorCapacity;
        
        // Increment downwards from the all-senior crew to see if there's a crew assignment
        // mix available consisting of both seniors and juniors that provides a more optimal
        // crew capacity. If bestCrewCapacity hits roomAmt, then we're at ideal crew capacity
        // and do not need to go any further.
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
