package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailSvc;

//    @GetMapping
//    public ModelAndView redirect() {
//        return new ModelAndView("redirect:http://localhost:8080/posts");
//    }

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailSvc) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailSvc = emailSvc;
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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.getById(id);
        if (currentUser.getId() == post.getUser().getId()) {
            model.addAttribute("post", post);
            return "posts/editpost";
        } else
            return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
        postDao.save(post);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        boolean isPostOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isPostOwner = currentUser.getId() == post.getUser().getId();
        }
        model.addAttribute("post", post);
        model.addAttribute("isPostOwner", isPostOwner);
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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
//        emailSvc.prepareAndSend(post, "Created new Post", "Woo you made another post..");
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam String search, Model model) {
        model.addAttribute("posts", postDao.findAllQuery(search));
        return "posts/index";
    }
}
