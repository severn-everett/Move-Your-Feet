package com.severett.moveyourfeet.config;

/**
 * Interface for the configuration object that is passed into
 * BuildingAssigner. Currently, only the command-line parser
 * is implemented, but this arrangement leaves the capability
 * to make other configuration classes, such as getting the
 * information from a file or a JSON-based REST request.
 * @author Severn Everett
 */
public interface SchedulerConfigParser {
    
    public SchedulerConfiguration parseConfig() throws ConfigurationException;
    
}
