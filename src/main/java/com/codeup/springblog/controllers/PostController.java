package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String postsIndex(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("This is post1", "This is post1s body"));
        posts.add(new Post("This is post2", "This is post2s body"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable int id, Model model) {
        Post post = new Post("Post demo title", "Post demo body");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePost() {
        return "<h1>This is the create post page</h1>" +
                "<form method=\"post\"><button type=\"submit\">Create post</button></form>";
    }

//    @RequestMapping(path ="/posts/create", method = RequestMethod.POST)
    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreatePost() {
        return "<h1>Created a new post</h1>";
    }

}
