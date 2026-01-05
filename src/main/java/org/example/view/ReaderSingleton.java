package org.example.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderSingleton {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private ReaderSingleton() {
    }

    public static BufferedReader getReader() {
        return reader;
    }

    public static String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
