package org.example.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriterSingleton {

    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    private WriterSingleton(){}

    public static BufferedWriter getWriter(){
        return writer;
    }

    public static void writeAndFlush(String str)  {
        try {
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
