package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Review;
import com.codeup.michero.models.User;
import com.codeup.michero.services.ConcertService;
import com.codeup.michero.services.ReviewService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {
    // 1. Create an instance variable with your dependency
    private final ReviewService reviewService;
    private final ConcertService concertService;
    private final UsersRepository usersRepository;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ReviewController(ReviewService reviewService, ConcertService concertService, UsersRepository usersRepository) {
        this.reviewService = reviewService; // This the first time we assign something to postService
        this.concertService = concertService;
        this.usersRepository = usersRepository;
    }

    @RequestMapping("/reviews")
    public String index(Model viewAndModel) {

        Iterable<Review> list_of_reviews = reviewService.findAll();
        viewAndModel.addAttribute("list_of_reviews", list_of_reviews);
        return "reviews/index";
    }

    @RequestMapping("/reviews/concertReviews/{id}")
    public String concertReviewsIndex(@PathVariable long id, Model viewAndModel){
        // get reviews for single concert
        Iterable<Review> concertReviews = this.reviewService.findByConcert_id(id);
        // send the list to the view
        viewAndModel.addAttribute("concertReviews", concertReviews);
        return "reviews/concertReviewsIndex";
    }

    @RequestMapping("/reviews/show/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        // fetch the review
        Review review = reviewService.findOne(id);
        viewAndModel.addAttribute("review", review);
        return "reviews/show";
    }

    @RequestMapping("/reviews/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Review());
        return "reviews/create";
    }

    @PostMapping("/reviews/create")
    public String createPost(@ModelAttribute Review post) {
        // get current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // set user to review post
        post.setUser(user);

        reviewService.save(post);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/create-concert-review/{concert_id}")
    public String createConcertReview(@ModelAttribute Review concertReview,
                                      @PathVariable long concert_id){
        // need concertReview object, concert id, and current user
        // save concertReview
        // assign user
        // get current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // get current concert
        Concert c = concertService.findOne(concert_id);
        // set user to concert review post
        concertReview.setUser(user);
        // set concert to concert review post
        concertReview.setConcert(c);

        // save post
        reviewService.save(concertReview);

        // return to original concert post via id
        return "redirect:/concerts";
    }

    @GetMapping("/reviews/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Review review = reviewService.findOne(id);
        viewAndModel.addAttribute("review", review);
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
