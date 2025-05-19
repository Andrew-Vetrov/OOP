package ru.nsu.chepik.pizzeria;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter @Setter
public class Config {
    private int bakers;
    private int[] bakers_speeds;
    private int couriers;
    private int[] couriers_capacities;
    private int store_capacity;

    public static Config createConfig(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Config.class);
    }
}
