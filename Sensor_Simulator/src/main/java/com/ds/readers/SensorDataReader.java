package com.ds.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SensorDataReader {
    private final String fileName;

    public SensorDataReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Float> readSensorData() {
        List<Float> sensorValues = new ArrayList<>();

        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
            if(Objects.nonNull(in)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String value;
                while ((value = br.readLine()) != null) {
                    sensorValues.add(Float.parseFloat(value));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return sensorValues;
    }
}
