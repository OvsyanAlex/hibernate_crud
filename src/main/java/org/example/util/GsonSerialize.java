package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonSerialize {
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private GsonSerialize() {
    }

    public static Gson getGson() {
        return gson;
    }
}
