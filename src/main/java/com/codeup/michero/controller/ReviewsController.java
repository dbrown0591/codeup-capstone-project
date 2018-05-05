package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Review;
import com.codeup.michero.models.User;
import com.codeup.michero.services.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewsController {
    // 1. Create an instance variable with your dependency
    private final ReviewService reviewService;
    private final UsersRepository usersRepository;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ReviewsController(ReviewService reviewService, UsersRepository usersRepository) {
        this.reviewService = reviewService; // This the first time we assign something to postService
        this.usersRepository = usersRepository;
    }

    @RequestMapping("/reviews")
    public String index(Model viewAndModel) {
//        List<Review> reviews = Arrays.asList(
//            new Review("Review A", "Body A"),
//            new Review("Review B", "Body B"),
//            new Review("Review C", "Body C")
//        );
        Iterable<Review> list_of_reviews = reviewService.findAll();
            viewAndModel.addAttribute("list_of_reviews", list_of_reviews);
        return "reviews/index";
    }

    @RequestMapping("/reviews/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        //Review post = new Review("Test post", "Test body");
        Review post = reviewService.findOne(id);
        viewAndModel.addAttribute("post", post);
        return "reviews/show";
    }

    @RequestMapping("/reviews/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Review());
        return "reviews/create";
    }

    @PostMapping("/reviews/create")
    public String createPost(@ModelAttribute Review post) {
        User user = usersRepository.findOne(2L);
        post.setUsers(user);
        reviewService.save(post);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Review post = reviewService.findOne(id);
        viewAndModel.addAttribute("post", post);
        return "reviews/edit";
    }

    @PostMapping("/reviews/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Review post) {
        post.setId(id);
        reviewService.save(post);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/{id}/delete")
    public String delete(@PathVariable long id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }

}
