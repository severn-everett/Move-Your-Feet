package com.severett.moveyourfeet;

import com.severett.moveyourfeet.assignments.BuildingAssigner;
import com.severett.moveyourfeet.config.ConfigurationException;
import com.severett.moveyourfeet.config.CLISchedulerConfig;
import org.json.JSONArray;

/**
 * Main class for running the Building Assignment Scheduler. As the
 * error output in the program suggests, the ideal way to run the program is:
 * java -jar Move-Your-Feet.jar "{ rooms: [35, 21, 17], senior: 10, junior: 6 }"
 * @author Severn Everett
 */
public class Scheduler {

    public static void main(String[] args) {
        CLISchedulerConfig config = new CLISchedulerConfig();
        try {
            config.parseConfig(args);
            JSONArray schedule = BuildingAssigner.getInstance().generateBuildingAssignments(config);
            System.out.println(schedule.toString());
        } catch (ConfigurationException ce) {
            System.err.println(ce);
            System.err.println("Example command: java -jar Move-Your-Feet.jar \"{ rooms: [35, 21, 17], senior: 10, junior: 6 }\"");
            System.exit(1);
        }
    }
    
}
