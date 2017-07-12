package com.severett.moveyourfeet.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration class for deriving BuildingAssignment lists via
 * BuildingAssigner. The values passed in are subject to validation,
 * and the configuration object will throw a ConfigurationException
 * if a setter fails this validation.
 * @author Severn Everett
 */
public final class SchedulerConfiguration {

    private List<Integer> roomsList;
    private int seniorCapacity;
    private int juniorCapacity;
    
    public SchedulerConfiguration(List<Integer> roomsList, int seniorCapacity, int juniorCapacity) throws ConfigurationException {
        setRoomsList(roomsList);
        setSeniorCapacity(seniorCapacity);
        setJuniorCapacity(juniorCapacity);
    }
    
    public List<Integer> getRoomsList() {
        return new ArrayList<>(roomsList);
    }
    
    public int getSeniorCapacity() {
        return seniorCapacity;
    }
    
    public int getJuniorCapacity() {
        return juniorCapacity;
    }
    
    public void setRoomsList(List<Integer> roomsList) throws ConfigurationException {
        if ((roomsList != null) && (!roomsList.isEmpty())) {
            if (roomsList.stream().allMatch(i -> i != null && i > 0)) {
                this.roomsList = roomsList;
            } else {
                throw new ConfigurationException("Rooms list must contain non-null whole numbers greater than zero");
            }
        } else {
            throw new ConfigurationException("Rooms list must be a list of non-null whole numbers greater than zero");
        }
    }
    
    public void setSeniorCapacity(int seniorCapacity) throws ConfigurationException {
        if (seniorCapacity > 0) {
            this.seniorCapacity = seniorCapacity;
        } else {
            throw new ConfigurationException("Senior capacity must be greater than zero");
        }
    }
    
    public void setJuniorCapacity(int juniorCapacity) throws ConfigurationException {
        if (juniorCapacity > 0) {
            this.juniorCapacity = juniorCapacity;
        } else {
            throw new ConfigurationException("Junior capacity must be greater than zero");
        }
    }
    
}
