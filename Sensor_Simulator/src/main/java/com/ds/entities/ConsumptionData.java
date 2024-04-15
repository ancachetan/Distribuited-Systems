package com.ds.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ConsumptionData implements Serializable {
    private LocalDateTime date;
    private String deviceId;
    private float measurementValue;

    public ConsumptionData(LocalDateTime date, String deviceId, float measurementValue) {
        this.date = date;
        this.deviceId = deviceId;
        this.measurementValue = measurementValue;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(float measurementValue) {
        this.measurementValue = measurementValue;
    }

    @Override
    public String toString() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"date\":\"").append(date).append("\",");
        jsonBuilder.append("\"deviceId\":").append(deviceId).append("\",");
        jsonBuilder.append("\"measurementValue\":").append(measurementValue);
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
