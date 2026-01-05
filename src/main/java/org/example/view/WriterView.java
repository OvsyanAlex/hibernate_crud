package org.example.view;



import org.example.controller.WriterController;
import org.example.model.Label;
import org.example.model.Writer;

import java.util.List;

import static org.example.util.GsonSerialize.getGson;
import static org.example.view.ReaderSingleton.read;
import static org.example.view.WriterSingleton.writeAndFlush;


public class WriterView {
    WriterController writerController = new WriterController();
    public static String mainWriterMenu;

    static {
        mainWriterMenu = "new writer\n" + "get writer\n" + "change writer\n" + "delete writer\n" + "get writers\n";
    }

    public void getWriterMenu() {
        writeAndFlush(mainWriterMenu);
        String read = read();
        if (read.equalsIgnoreCase("new writer")) {
            writeAndFlush("enter first name:\n");
            String firstName = read();
            writeAndFlush("enter last name:\n");
            String secondName = read();
            writeAndFlush(writerController.createWriter(firstName, secondName));
        }
        if (read.equalsIgnoreCase("get writer")) {
            writeAndFlush("enter writer id:\n");
            String id = read();
            Writer writer = writerController.getWriter(Long.parseLong(id));
            if (writer == null) {
                writeAndFlush("writer not found, please create writer");
            } else writeAndFlush(getGson().toJson(writer));
        }

        if (read.equalsIgnoreCase("change writer")) {
            writeAndFlush("enter writer id:\n");
            String id = read();
            writeAndFlush("enter name:\n");
            String firstName = read();
            writeAndFlush("enter last name:\n");
            String secondName = read();
            Writer writer = writerController.updateWriter(Long.parseLong(id), firstName, secondName);
            if (writer == null) {
                writeAndFlush("writer not found, please create writer");
            } else writeAndFlush(getGson().toJson(writer));
        }

        if (read.equalsIgnoreCase("delete writer")) {
            writeAndFlush("enter writer id:\n");
            String id = read();
            String result = writerController.deleteWriter(Long.parseLong(id));
            writeAndFlush(getGson().toJson(result));
        }
        if (read.equalsIgnoreCase("get writers")) {
            List<Writer> writers = writerController.getAllWriters();
            if (writers == null || writers.isEmpty()) {
                writeAndFlush("labels not found, please create label");
            } else writeAndFlush(getGson().toJson(writers));
        }
    }
}
