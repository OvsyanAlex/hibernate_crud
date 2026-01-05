package org.example.view;


import java.io.*;

import static org.example.view.WriterSingleton.writeAndFlush;


public class ConsoleInput {
    WriterView writerView = new WriterView();
    PostView postView = new PostView();
    LabelView labelView = new LabelView();
    String mainMenu = "\n\nselect menu item:\n" + "writer\n" + "post\n" + "label\n";

    public void inputFromUser() {
        do {
            try {
                writeAndFlush(mainMenu);
                String mainMenuChoice = ReaderSingleton.getReader().readLine();
                if (mainMenuChoice.equalsIgnoreCase("writer")) {
                    writerView.getWriterMenu();
                }
                if (mainMenuChoice.equalsIgnoreCase("post")) {
                    postView.getPostMenu();
                }
                if (mainMenuChoice.equalsIgnoreCase("label")) {
                    labelView.getLabelMenu();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
