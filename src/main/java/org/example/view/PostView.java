package org.example.view;


import org.example.controller.PostController;
import org.example.model.Label;
import org.example.model.Post;

import java.util.List;

import static org.example.util.GsonSerialize.getGson;
import static org.example.view.ReaderSingleton.read;
import static org.example.view.WriterSingleton.writeAndFlush;

public class PostView {
    PostController postController = new PostController();

    public static String mainPostMenu;

    static {
        mainPostMenu = "new post\n" + "get post\n" + "change post\n" + "delete post\n" + "get posts\n";
    }

    public void getPostMenu() {
        writeAndFlush(mainPostMenu);
        String read = read();
        if (read.equalsIgnoreCase("new post")) {
            writeAndFlush("enter writer id:\n");
            String id = read();
            writeAndFlush("enter title:\n");
            String title = read();
            writeAndFlush("enter content:\n");
            String content = read();
            Post post = postController.createPost(title, content, Long.parseLong(id));
            if (post == null) {
                writeAndFlush("writer for post not found, please create writer");
            } else {
                writeAndFlush(getGson().toJson(post));
            }
        }
        if (read.equalsIgnoreCase("get post")) {
            writeAndFlush("enter post id:\n");
            String id = read();
            Post post = postController.getPost(Long.parseLong(id));
            if (post == null) {
                writeAndFlush("post not found, please create post");
            } else writeAndFlush(getGson().toJson(post));
        }
        if (read.equalsIgnoreCase("change post")) {
            writeAndFlush("enter post id:\n");
            String id = read();
            writeAndFlush("enter title:\n");
            String title = read();
            writeAndFlush("enter content:\n");
            String postText = read();
            Post post = postController.updatePost(Long.parseLong(id), title, postText);
            if (post == null) {
                writeAndFlush("post not found, please create post");
            } else writeAndFlush(getGson().toJson(post));
        }
        if (read.equalsIgnoreCase("delete post")) {
            writeAndFlush("enter post id:\n");
            String id = read();
            writeAndFlush(postController.deletePost(Long.parseLong(id)));
        }
        if (read.equalsIgnoreCase("get posts")) {
            List<Post> posts = postController.getAllPosts();
            if (posts == null || posts.isEmpty()) {
                writeAndFlush("posts not found, please create post");
            } else writeAndFlush(getGson().toJson(posts));
        }
    }
}
