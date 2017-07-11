package com.severett.moveyourfeet.config;

/**
 * Wrapper exception for Scheduler to catch for configuration
 * parsing issues
 * @author Severn Everett
 */
public class ConfigurationException extends Exception {
    
    public ConfigurationException(String reason) {
        super(reason);
    }
    
}
