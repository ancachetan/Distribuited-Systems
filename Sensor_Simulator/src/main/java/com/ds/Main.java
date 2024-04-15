package com.ds;

import com.ds.readers.DeviceIdReader;
import com.ds.readers.SensorDataReader;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        SensorDataReader sensorDataReader = new SensorDataReader("sensor.csv");
        DeviceIdReader deviceIdReader = new DeviceIdReader("config.txt");
        List<String> deviceIds = deviceIdReader.readDeviceId();
        List<Float> sensorValues = sensorDataReader.readSensorData();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(deviceIds.size());
        for (String id : deviceIds) {
            scheduler.scheduleAtFixedRate(new MessageProducer(id, sensorValues), 0, 10, TimeUnit.SECONDS);
        }
    }
}