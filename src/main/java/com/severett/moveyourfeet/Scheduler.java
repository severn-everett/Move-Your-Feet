package com.severett.moveyourfeet;

import com.severett.moveyourfeet.assignments.BuildingAssigner;
import com.severett.moveyourfeet.config.ConfigurationException;
import com.severett.moveyourfeet.config.CLISchedulerConfig;
import org.json.JSONArray;

public class Scheduler {

    public static void main(String[] args) {
        CLISchedulerConfig config = new CLISchedulerConfig();
        try {
            config.parseConfig(args);
            JSONArray schedule = new BuildingAssigner().generateBuildingAssignments(config);
            System.out.println(schedule.toString());
        } catch (ConfigurationException ce) {
            System.err.println(ce);
            System.exit(1);
        }
    }
    
}
