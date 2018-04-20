package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Reviews;
import com.codeup.michero.models.User;
import com.codeup.michero.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewsController {
    // 1. Create an instance variable with your dependency
    private final PostService postService;
    private final UsersRepository usersRepository;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ReviewsController(PostService postService, UsersRepository usersRepository) {
        this.postService = postService; // This the first time we assign something to postService
        this.usersRepository = usersRepository;
    }

    @RequestMapping("/reviews")
    public String index(Model viewAndModel) {
        /*List<Reviews> reviews = Arrays.asList(
            new Reviews("Reviews A", "Body A"),
            new Reviews("Reviews B", "Body B"),
            new Reviews("Reviews C", "Body C")
        );*/
        Iterable<Reviews> post = postService.findAll();

        viewAndModel.addAttribute("posts", post);

        return "reviews/index";
    }

    @RequestMapping("/reviews/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        //Reviews post = new Reviews("Test post", "Test body");
        Reviews post = postService.findOne(id);

        viewAndModel.addAttribute("post", post);

        return "reviews/show";
    }

    @RequestMapping("/reviews/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Reviews());
        return "reviews/create";
    }

    @PostMapping("/reviews/create")
    public String createPost(@ModelAttribute Reviews post) {
        User user = usersRepository.findOne(2L);
        post.setUsers(user);
        postService.save(post);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Reviews post = postService.findOne(id);
        viewAndModel.addAttribute("post", post);
        return "reviews/edit";
    }

    @PostMapping("/reviews/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Reviews post) {
        post.setId(id);
        postService.save(post);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/{id}/delete")
    public String delete(@PathVariable long id) {
        postService.delete(id);
        return "redirect:/reviews";
    }

}
