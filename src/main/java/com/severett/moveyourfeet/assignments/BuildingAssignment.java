package com.severett.moveyourfeet.assignments;

import org.json.JSONObject;

/**
 * Data structure for each building's crew assignment
 * @author Severn Everett
 */
public class BuildingAssignment {
    
    private static final String SENIOR_KEY = "senior";
    private static final String JUNIOR_KEY = "junior";

    private int senior;
    private int junior;
    
    public BuildingAssignment(int senior, int junior) {
        this.senior = senior;
        this.junior = junior;
    }
    
    public int getSenior() {
        return senior;
    }
    
    public void setSenior(int senior) {
        this.senior = senior;
    }
    
    public int getJunior() {
        return junior;
    }
    
    public void setJunior(int junior) {
        this.junior = junior;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SENIOR_KEY, senior);
        jsonObject.put(JUNIOR_KEY, junior);
        return jsonObject;
    }
    
}
