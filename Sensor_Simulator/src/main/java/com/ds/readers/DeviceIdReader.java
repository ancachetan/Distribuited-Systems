package com.ds.readers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceIdReader {
    private final String fileName;

    public DeviceIdReader(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readDeviceId() {
        List<String> deviceNumbers = new ArrayList<>();

        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
            if(Objects.nonNull(in)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                Pattern pattern = Pattern.compile("device:(.*)");
                while ((line = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        deviceNumbers.add(matcher.group(1));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return deviceNumbers;
    }
}
