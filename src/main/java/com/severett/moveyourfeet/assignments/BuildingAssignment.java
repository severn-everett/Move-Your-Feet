package com.severett.moveyourfeet.assignments;

import org.json.JSONObject;

public class BuildingAssignment {
    
    private static final String SENIOR = "senior";
    private static final String JUNIOR = "junior";

    private int seniorAmount;
    private int juniorAmount;
    
    public BuildingAssignment(int seniorAmount, int juniorAmount) {
        this.seniorAmount = seniorAmount;
        this.juniorAmount = juniorAmount;
    }
    
    public int getSeniorAmount() {
        return seniorAmount;
    }
    
    public void setSeniorAmount(int seniorAmount) {
        this.seniorAmount = seniorAmount;
    }
    
    public int getJuniorAmount() {
        return juniorAmount;
    }
    
    public void setJuniorAmount(int juniorAmount) {
        this.juniorAmount = juniorAmount;
    }
    
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SENIOR, seniorAmount);
        jsonObject.put(JUNIOR, juniorAmount);
        return jsonObject;
    }
    
}
