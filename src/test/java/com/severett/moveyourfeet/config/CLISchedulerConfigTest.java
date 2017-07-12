package com.severett.moveyourfeet.config;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class CLISchedulerConfigTest {
    
    @Test
    public void testValidInput() {
        String[] args = {"{ rooms: [35, 21, 17], senior: 10, junior: 6 }"};
        try {
            CLISchedulerConfigParser cliParser = new CLISchedulerConfigParser(args);
            SchedulerConfiguration config = cliParser.parseConfig();
            Assert.assertEquals(Arrays.asList(35, 21, 17), config.getRoomsList());
            Assert.assertEquals(10, config.getSeniorCapacity());
            Assert.assertEquals(6, config.getJuniorCapacity());
        } catch (ConfigurationException ce) {
            Assert.fail(ce.getMessage());
        }
    }
    
    @Test
    public void testImproperFormatting() {
        String[] args = {"{ This should fail!"};
        try {
            CLISchedulerConfigParser cliParser = new CLISchedulerConfigParser(args);
            cliParser.parseConfig();
            Assert.fail("Configuration succeeded when it should have failed");
        } catch (ConfigurationException ce) {
            Assert.assertEquals("Error During Configuration Parsing: Invalid JSON - Expected a ':' after a key at 20 [character 21 line 1]", ce.getMessage());
        }
    }
    
    @Test
    public void testNoInput() {
        String[] args = {};
                try {
            CLISchedulerConfigParser cliParser = new CLISchedulerConfigParser(args);
            cliParser.parseConfig();
            Assert.fail("Configuration succeeded when it should have failed");
        } catch (ConfigurationException ce) {
            Assert.assertEquals("Error During Configuration Parsing: No Configuration Passed In", ce.getMessage());
        }
    }
    
}
