package com.severett.moveyourfeet.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class SchedulerConfigurationTest {
    
    @Test
    public void idealConfigTest() {
        try {
            SchedulerConfiguration schedulerConfig = new SchedulerConfiguration(Arrays.asList(35, 21, 17), 10, 6);
        } catch (ConfigurationException ce) {
            Assert.fail(ce.getMessage());
        }
    }
    
    @Test
    public void invalidSeniorCapacityTest() {
        try {
            SchedulerConfiguration schedulerConfig = new SchedulerConfiguration(Arrays.asList(35, 21, 17), 0, 6);
            Assert.fail("No ConfigurationException occurred when one should have");
        } catch (ConfigurationException ce) {
            Assert.assertEquals("Error During Configuration Parsing: Senior capacity must be greater than zero", ce.getMessage());
        }
    }
    
    @Test
    public void invalidJuniorCapacityTest() {
        try {
            SchedulerConfiguration schedulerConfig = new SchedulerConfiguration(Arrays.asList(35, 21, 17), 10, 0);
            Assert.fail("No ConfigurationException occurred when one should have");
        } catch (ConfigurationException ce) {
            Assert.assertEquals("Error During Configuration Parsing: Junior capacity must be greater than zero", ce.getMessage());
        }
    }
    
    @Test
    public void noRoomsListTest() {
        List<List<Integer>> rlList = Arrays.asList(null, new ArrayList<>());
        rlList.forEach(roomsList -> {
            try {
                SchedulerConfiguration schedulerConfig = new SchedulerConfiguration(roomsList, 10, 6);
                Assert.fail("No ConfigurationException occurred when one should have when passing in '" + roomsList + "' as the rooms list");
            } catch (ConfigurationException ce) {
                Assert.assertEquals("Error During Configuration Parsing: Rooms list must be a list of non-null whole numbers greater than zero", ce.getMessage());
            }
        });
    }
    
    @Test
    public void badRoomsListTest() {
        List<Integer> badRoomList = Arrays.asList(null, -1);
        badRoomList.forEach(badRoom -> {
            try {
                SchedulerConfiguration schedulerConfig = new SchedulerConfiguration(Arrays.asList(1, badRoom, 3), 10, 6);
                Assert.fail("No ConfigurationException occurred when one should have when passing in '" + badRoom + "' as a room amount in the rooms list");
            } catch (ConfigurationException ce) {
                Assert.assertEquals("Error During Configuration Parsing: Rooms list must contain non-null whole numbers greater than zero", ce.getMessage());
            }
        });
    }
    
}
