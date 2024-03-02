package com.vrp.qachallenge.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CSVReader {
    @SneakyThrows
    public static <T> List<T> readCSV(String filePath, Class<T> clazz) {
        List<T> data = new ArrayList<>();
        try {
            InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(filePath);
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String[] headers = reader.readLine().split(","); // Assuming the first line contains headers

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                T rowData = mapRowToObject(values, headers, clazz);
                data.add(rowData);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return data;
    }

    @SneakyThrows
    private static <T> T mapRowToObject(String[] values, String[] headers, Class<T> clazz) {
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (int i = 0; i < headers.length; i++) {
            String fieldName = headers[i];
            Field field = getField(clazz, fieldName);
            if (field != null) {
                field.setAccessible(true);
                field.set(instance, values[i]);
            }
        }

        return instance;
    }

    private static <T> Field getField(Class<T> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
