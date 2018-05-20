package com.codeup.michero.controller;

import com.codeup.michero.daos.ImageRepository;
import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Review;
import com.codeup.michero.models.User;
import com.codeup.michero.services.ConcertService;
import com.codeup.michero.services.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ConcertController {
    // 1. Create an instance variable with your dependency
    private final ConcertService concertService;
    private final UsersRepository usersRepository;
    private final FileUploadController fileUploadController;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ConcertController(ConcertService concertService,
                             UsersRepository usersRepository,
                             ImageService imageService) {
        this.concertService = concertService; // This the first time we assign something to postService
        this.usersRepository = usersRepository;
        this.fileUploadController = new FileUploadController(imageService);
    }

    @RequestMapping("/concerts")
    public String index(Model viewAndModel) {
        Iterable<Concert> list_of_concerts = concertService.findAll();
        viewAndModel.addAttribute("list_of_concerts", list_of_concerts);
        return "concerts/index";
    }

//    @RequestMapping("/reviews/{id}")
//    public String show(@PathVariable long id, Model viewAndModel) {
//        //Review post = new Review("Test post", "Test body");
//        Review review = reviewService.findOne(id);
//        viewAndModel.addAttribute("review", review);
//        return "reviews/show";
//    }

    @RequestMapping("/concerts/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("concertPost", new Concert());
        return "concerts/create";
    }

    @PostMapping("/concerts/create")
    public String createPost(HttpServletRequest req,
                             @RequestParam(name="image") List<MultipartFile> mf,
                             @ModelAttribute Concert post,
                             Model model) {

        // save the concert post
        // fill out the concert object
        User user = usersRepository.findOne(2L);
        post.setUser(user);
        // save the concert post
        concertService.save(post);

        // save the images
        fileUploadController.uploadImages(req, post, model, mf);

        return "redirect:/concerts";
    }

//    @GetMapping("/reviews/{id}/edit")
//    public String showEditForm(@PathVariable long id, Model viewAndModel) {
//        Review review = reviewService.findOne(id);
//        viewAndModel.addAttribute("review", review);
//        return "reviews/edit";
//    }

//    @PostMapping("/reviews/{id}/edit")
//    public String updatePost(@PathVariable long id, @ModelAttribute Review post) {
//        post.setId(id);
//        reviewService.save(post);
//        return "redirect:/reviews";
//    }

//    @PostMapping("/reviews/{id}/delete")
//    public String delete(@PathVariable long id) {
//        reviewService.delete(id);
//        return "redirect:/reviews";
//    }

}
