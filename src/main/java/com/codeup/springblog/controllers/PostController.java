package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex() {
        return "<h1>Posts index page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPostById(@PathVariable int id) {
        return "Viewing post from user id: " + id;
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
