package com.severett.moveyourfeet;

import com.severett.moveyourfeet.config.ConfigurationException;
import com.severett.moveyourfeet.config.SchedulerConfig;

public class Scheduler {

    public static void main(String[] args) {
        SchedulerConfig config = new SchedulerConfig();
        try {
            config.parseConfig(args);
            // TODO Run Scheduler
        } catch (ConfigurationException ce) {
            // TODO Display help dialog for how to properly run Scheduler
        }
    }
    
}
