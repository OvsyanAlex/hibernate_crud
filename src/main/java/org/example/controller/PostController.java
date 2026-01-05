package org.example.controller;


import org.example.model.Post;
import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.PostRepositoryImpl;
import org.example.repository.WriterRepositoryImpl;
import org.example.service.PostService;


import java.util.List;


public class PostController {
    PostService postService = new PostService(new PostRepositoryImpl(), new WriterRepositoryImpl());
    WriterController writerController = new WriterController();


    public Post createPost(String title, String content, Long writerId) {
        Writer writer = writerController.getWriter(writerId);
        if (writer == null) {
            return null;
        }
        return postService.save(new Post(null, title, content, Status.ACTIVE, writerId));
    }

    public Post updatePost(Long id, String title, String content) {
        Post post = getPost(id);
        if (post == null) {
            return null;
        } else if (post.getStatus().equals(Status.DELETED)) {
            return null;
        }
        post.setTitle(title);
        post.setContent(content);
        return postService.update(post);

    }

    public Post getPost(Long id) {
        return postService.getById(id);
    }

    public String deletePost(Long id) {
        Post post = getPost(id);
        if (post == null) {
            return "post not found, please create post";
        } else if (post.getStatus().equals(Status.DELETED)) {
            return "post not found, please create post";
        }
        postService.deleteById(post.getId());
        return "post removed";
    }

    public List<Post> getAllPosts() {
        return postService.findAll();
    }
}
