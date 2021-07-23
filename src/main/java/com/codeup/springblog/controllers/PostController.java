package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {
    private final PostRepository postDao;

//    @GetMapping
//    public ModelAndView redirect() {
//        return new ModelAndView("redirect:http://localhost:8080/posts");
//    }

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam("deleteButton") long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit")
    public String editPost(@RequestParam("editButton") long id, Model model) {
        model.addAttribute("post", postDao.findById(id));
        return "posts/editpost";
    }

    @PostMapping("/posts/edit")
    public String editPost(@RequestParam("postId") long postId, @RequestParam("title") String title, @RequestParam("body") String body) {
        Post post = new Post(postId, title, body);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findById(id));
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
