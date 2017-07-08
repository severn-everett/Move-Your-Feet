package com.severett.moveyourfeet.config;

import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SchedulerConfig {
    
    private List<Integer> roomsList;
    private int seniorCapacity;
    private int juniorCapacity;
    
    public SchedulerConfig() {
    }
    
    public void parseConfig(String[] args) throws ConfigurationException {
        try {
            JSONObject configArgs = new JSONObject(args[0]);
            roomsList = ((JSONArray) configArgs.getJSONArray("rooms")).toList().stream().map(Integer.class::cast).collect(Collectors.toList());
            seniorCapacity = configArgs.getInt("senior");
            juniorCapacity = configArgs.getInt("junior");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new ConfigurationException("Error During Configuration Parsing: No Configuration Passed In");
        } catch (JSONException jsone) {
            throw new ConfigurationException("Error During Configuration Parsing: Invalid JSON - " + jsone.getMessage());
        } catch (Exception e) {
            throw new ConfigurationException("Error During Configuration Parsing: " + e.getClass().toString() + " - " + e.getMessage());
        }
    }
    
    public List<Integer> getRoomsList() {
        return roomsList;
    }
    
    public int getSeniorCapacity() {
        return seniorCapacity;
    }
    
    public int getJuniorCapacity() {
        return juniorCapacity;
    }
    
}
