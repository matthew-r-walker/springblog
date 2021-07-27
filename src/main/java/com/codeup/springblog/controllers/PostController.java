package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

//    @GetMapping
//    public ModelAndView redirect() {
//        return new ModelAndView("redirect:http://localhost:8080/posts");
//    }

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getById(id));
        return "posts/editpost";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
        post.setUser(userDao.getById(1L));
        postDao.save(post);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreatePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/createpost";
    }

//    @RequestMapping(path ="/posts/create", method = RequestMethod.POST)
    @PostMapping("/posts/create")
    public String postCreatePost(@ModelAttribute Post post) {
        post.setUser(userDao.getById(1L));
        postDao.save(post);
        return "redirect:/posts";
    }

}
