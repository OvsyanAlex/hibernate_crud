package org.example.controller;


import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Status;
import org.example.repository.LabelRepositoryImpl;
import org.example.repository.PostRepositoryImpl;
import org.example.service.LabelService;

import java.util.ArrayList;
import java.util.List;

public class LabelController {
    LabelService labelService = new LabelService(new LabelRepositoryImpl(), new PostRepositoryImpl());
    PostController postController = new PostController();

    public Label addLabelToPost(String name, Long postId) {
        Post post = postController.getPost(postId);
        if (post == null) {
            return null;
        }
        return labelService.save(new Label(null, name, Status.ACTIVE, postId));
    }

    public List<Label> createLabels(String labelNames, Long postId) {
        String[] names = labelNames.split(" ");
        ArrayList<Label> newLabels = new ArrayList<>();
        for (String name : names) {
            Label newLabel = new Label(null, name, Status.ACTIVE, postId);
            newLabels.add(newLabel);
            labelService.save(newLabel);
        }
        return newLabels;
    }

    public Label updateLabel(Long id, String name) {
        Label oldLabel = getLabel(id);
        if (oldLabel == null) {
            return null;
        } else if (oldLabel.getStatus().equals(Status.DELETED)) {
            return null;
        }
        oldLabel.setName(name);
        labelService.update(oldLabel);
        return oldLabel;
    }

    public Label getLabel(Long id) {
        return labelService.getById(id);
    }

    public String deleteLabel(Long id) {
        Label post = getLabel(id);
        if (post == null) {
            return "label not found, please create label";
        } else if (post.getStatus().equals(Status.DELETED)) {
            return "label not found, please create label";
        }
        labelService.deleteById(post.getId());
        return "label removed";
    }

    public List<Label> getAllLabels() {
        return labelService.findAll();
    }
}
