package com.severett.moveyourfeet.config;

import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Implementation of SchedulderConfigParser to parse the configuration
 * passed in via the command-line
 * @author Severn Everett
 */
public class CLISchedulerConfigParser implements SchedulerConfigParser {
    
    private final String[] args;
    
    public CLISchedulerConfigParser(String[] args) {
        this.args = args;
    }
    
    @Override
    public SchedulerConfiguration parseConfig() throws ConfigurationException {
        try {
            JSONObject configArgs = new JSONObject(args[0]);
            List<Integer> roomsList = ((JSONArray) configArgs.getJSONArray("rooms")).toList().stream().map(Integer.class::cast).collect(Collectors.toList());
            int seniorCapacity = configArgs.getInt("senior");
            int juniorCapacity = configArgs.getInt("junior");
            return new SchedulerConfiguration(roomsList, seniorCapacity, juniorCapacity);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new ConfigurationException("No Configuration Passed In");
        } catch (JSONException jsone) {
            throw new ConfigurationException("Invalid JSON - " + jsone.getMessage());
        } catch (Exception e) {
            throw new ConfigurationException(e.getClass().toString() + " - " + e.getMessage());
        }
    }
    
}
