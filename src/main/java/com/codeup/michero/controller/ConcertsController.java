package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Review;
import com.codeup.michero.models.User;
import com.codeup.michero.services.ConcertService;
import com.codeup.michero.services.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConcertsController {
    // 1. Create an instance variable with your dependency
    private final ConcertService concertService;
    private final UsersRepository usersRepository;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ConcertsController(ConcertService concertService, UsersRepository usersRepository) {
        this.concertService = concertService; // This the first time we assign something to postService
        this.usersRepository = usersRepository;
    }

    @RequestMapping("/concerts")
    public String index(Model viewAndModel) {
//        List<Review> reviews = Arrays.asList(
//            new Review("Review A", "Body A"),
//            new Review("Review B", "Body B"),
//            new Review("Review C", "Body C")
//        );
        Iterable<Concert> list_of_concerts = concertService.findAll();
        viewAndModel.addAttribute("list_of_concerts", list_of_concerts);
        return "concerts/index";
    }

    @RequestMapping("/concerts/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        //Review post = new Review("Test post", "Test body");
        Concert concert = concertService.findOne(id);
        viewAndModel.addAttribute("concert", concert);
        return "concerts/show";
    }

    @RequestMapping("/concerts/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("concertPost", new Concert());
        return "concerts/create";
    }
//
    @PostMapping("/concerts/create")
    public String createPost(@ModelAttribute Concert post) {
        User user = usersRepository.findOne(2L);
        post.setUsers(user);
        concertService.save(post);
        return "redirect:/concerts";
    }
//
//    @GetMapping("/reviews/{id}/edit")
//    public String showEditForm(@PathVariable long id, Model viewAndModel) {
//        Review review = reviewService.findOne(id);
//        viewAndModel.addAttribute("review", review);
//        return "reviews/edit";
//    }
//
//    @PostMapping("/reviews/{id}/edit")
//    public String updatePost(@PathVariable long id, @ModelAttribute Review post) {
//        post.setId(id);
//        reviewService.save(post);
//        return "redirect:/reviews";
//    }
//
//    @PostMapping("/reviews/{id}/delete")
//    public String delete(@PathVariable long id) {
//        reviewService.delete(id);
//        return "redirect:/reviews";
//    }
}
