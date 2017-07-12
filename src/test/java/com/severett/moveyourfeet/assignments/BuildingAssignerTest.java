package com.severett.moveyourfeet.assignments;

import com.severett.moveyourfeet.config.ConfigurationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import com.severett.moveyourfeet.config.SchedulerConfiguration;

public class BuildingAssignerTest {
    
    private final BuildingAssigner buildingAssigner = BuildingAssigner.getInstance();
    
    @Test
    public void buildingAssignmentTest() {
        try {
            SchedulerConfiguration schedulerConfig = new SchedulerConfiguration(Arrays.asList(35, 21, 17), 10, 6);
            JSONArray expectedArray = new JSONArray();
            Map<String, Integer> buildingOneMap = new HashMap<>();
            buildingOneMap.put("senior", 3);
            buildingOneMap.put("junior", 1);
            Map<String, Integer> buildingTwoMap = new HashMap<>();
            buildingTwoMap.put("senior", 1);
            buildingTwoMap.put("junior", 2);
            Map<String, Integer> buildingThreeMap = new HashMap<>();
            buildingThreeMap.put("senior", 2);
            buildingThreeMap.put("junior", 0);
            expectedArray.put(buildingOneMap);
            expectedArray.put(buildingTwoMap);
            expectedArray.put(buildingThreeMap);

            JSONArray actualArray = buildingAssigner.generateBuildingAssignments(schedulerConfig);

            Assert.assertEquals(expectedArray.toString(), actualArray.toString());
        } catch (ConfigurationException ce) {
            Assert.fail(ce.getMessage());
        }
    }
    
}
