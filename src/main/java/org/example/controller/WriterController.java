package org.example.controller;


import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.WriterRepositoryImpl;
import org.example.service.WriterService;


import java.util.List;

import static org.example.util.GsonSerialize.getGson;


public class WriterController {
    WriterService writerService = new WriterService(new WriterRepositoryImpl());

    public String createWriter(String firstName, String lastName) {
        Writer writer = new Writer(null, firstName, lastName, Status.ACTIVE, null);
        writer = writerService.save(writer);
        return getGson().toJson(writer);
    }

    public Writer updateWriter(Long id, String writerFirstName, String writerLastName) {
        Writer oldWriter = getWriter(id);
        if (oldWriter == null) {
            return null;
        } else if (oldWriter.getStatus().equals(Status.DELETED)) {
            return null;
        }
        return writerService.update(new Writer(oldWriter.getId(), writerFirstName, writerLastName, Status.ACTIVE, null));
    }

    public Writer getWriter(Long id) {
        Writer writer = writerService.getById(id);
        if (writer == null) {
            return null;
        }
        return writerService.getById(id);
    }

    public String deleteWriter(Long id) {
        Writer writer = getWriter(id);
        if (writer == null) {
            return "writer not found, please create writer";
        } else if (writer.getStatus().equals(Status.DELETED)) {
            return "writer not found, please create writer";
        }
        writerService.deleteById(writer.getId());
        return "writer removed";
    }

    public List<Writer> getAllWriters() {
        return writerService.findAll();
    }
}