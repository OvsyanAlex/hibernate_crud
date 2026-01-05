package org.example.view;

import org.example.controller.LabelController;
import org.example.model.Label;

import java.util.List;

import static org.example.util.GsonSerialize.getGson;
import static org.example.view.ReaderSingleton.read;
import static org.example.view.WriterSingleton.writeAndFlush;

public class LabelView {
    LabelController labelController = new LabelController();

    public static String mainLabelMenu;

    static {
        mainLabelMenu = "new label\n" + "get label\n" + "change label\n" + "delete label\n" + "get labels\n";
    }

    public void getLabelMenu() {
        writeAndFlush(mainLabelMenu);
        String read = read();
        if (read.equalsIgnoreCase("new label")) {
            writeAndFlush("enter post id:\n");
            String id = read();
            writeAndFlush("enter label name:\n");
            String name = read();
            Label label = labelController.addLabelToPost(name, Long.parseLong(id));
            if (label == null) {
                writeAndFlush("post not found, please create post");
            } else writeAndFlush(getGson().toJson(label));
        }
        if (read.equalsIgnoreCase("get label")) {
            writeAndFlush("enter label id:\n");
            String id = read();
            Label label = labelController.getLabel(Long.parseLong(id));
            if (label == null) {
                writeAndFlush("label not found, please create label");
            } else writeAndFlush(getGson().toJson(label));
        }
        if (read.equalsIgnoreCase("change label")) {
            writeAndFlush("enter label id:\n");
            String id = read();
            writeAndFlush("enter new name:\n");
            String name = read();
            Label label = labelController.updateLabel(Long.parseLong(id), name);
            if (label == null) {
                writeAndFlush("label not found, please create label");
            } else writeAndFlush(getGson().toJson(label));
        }
        if (read.equalsIgnoreCase("delete label")) {
            writeAndFlush("enter label id:\n");
            String id = read();
            writeAndFlush(labelController.deleteLabel(Long.parseLong(id)));
        }
        if (read.equalsIgnoreCase("get labels")) {
            List<Label> allLabels = labelController.getAllLabels();
            if (allLabels == null || allLabels.isEmpty()) {
                writeAndFlush("labels not found, please create label");
            } else writeAndFlush(allLabels.toString());
        }
    }
}
