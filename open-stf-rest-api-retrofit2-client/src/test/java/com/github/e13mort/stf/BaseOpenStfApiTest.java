package com.github.e13mort.stf;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pavel
 */

class BaseOpenStfApiTest {
    static String baseUrl;
    static String apiKey;
    static String testDeviceSerial;

    static void initProperties() throws IOException {
        final Properties properties = new Properties();
        properties.load(new FileReader("stf.properties"));
        baseUrl = properties.getProperty("stf.url");
        apiKey = properties.getProperty("stf.key");
        testDeviceSerial = properties.getProperty("stf.device_serial");
        assertNotNull(baseUrl);
        assertNotNull(apiKey);
        assertNotNull(testDeviceSerial);
    }
}
