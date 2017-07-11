package com.severett.moveyourfeet.assignments;

import com.severett.moveyourfeet.config.SchedulerConfig;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BuildingAssignerTest {
    
    @Mock
    private SchedulerConfig schedulerConfig;
    
    private BuildingAssigner buildingAssigner = BuildingAssigner.getInstance();
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void buildingAssignmentTest() {
        Mockito.when(schedulerConfig.getRoomsList()).thenReturn(Arrays.asList(35, 21, 17));
        Mockito.when(schedulerConfig.getSeniorCapacity()).thenReturn(10);
        Mockito.when(schedulerConfig.getJuniorCapacity()).thenReturn(6);
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
    }
    
}
