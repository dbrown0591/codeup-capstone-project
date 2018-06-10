package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Image;
import com.codeup.michero.models.Review;
import com.codeup.michero.models.User;
import com.codeup.michero.services.ConcertService;
import com.codeup.michero.services.ImageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ProfileController {

    // 1. Create an instance variable with your dependency
    private final ConcertService concertService;
    private final UsersRepository usersRepository;
    private final FileUploadController fileUploadController;
    private final ImageService is;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ProfileController(ConcertService concertService,
                             UsersRepository usersRepository,
                             ImageService imageService,
                             FileUploadController fc) {
        this.concertService = concertService;
        this.usersRepository = usersRepository;
        this.fileUploadController = fc;
        this.is = imageService;
    }

    @RequestMapping("/profile/")
    public String index(Model viewAndModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // get list of concerts
        Iterable<Concert> list_of_concerts = concertService.findAll();

        viewAndModel.addAttribute("user", user);
        viewAndModel.addAttribute("list_of_concerts", list_of_concerts);

        return "profile/index";
    }


    //    @RequestMapping("/concerts/{id}")
//    public String show(@PathVariable long id, Model viewAndModel) {
//
//        // get all the images for this concert
//        Iterable<Image> concertImages = this.is.findByConcert_id(id);
//        List<String> url_list = new ArrayList<>();
//
//        // extract URLs
//        for(Image i : concertImages){
//            url_list.add(i.getUrl());
//        }
//
//        // get the concert object
//        Concert concert = concertService.findOne(id);
//        viewAndModel.addAttribute("concert", concert);
//        viewAndModel.addAttribute("url_list", url_list);
//        viewAndModel.addAttribute("concertReview", new Review());
//        return "concerts/show";
//    }
//
//        // get the concert object
//        Concert concert = concertService.findOne(id);
//        viewAndModel.addAttribute("concert", concert);
//        viewAndModel.addAttribute("url_list", url_list);
//        viewAndModel.addAttribute("concertReview", new Review());

//
//    @RequestMapping("/concerts/create")
//    public String showCreateForm(Model viewModel) {
//        viewModel.addAttribute("concertPost", new Concert());
//        return "concerts/create";
//    }
//
//    @PostMapping("/concerts/create")
//    public String createPost(HttpServletRequest req,
//                             @RequestParam(name="image") List<MultipartFile> mf,
//                             @ModelAttribute Concert post,
//                             Model model) {
//
//        // save the concert post
//        // fill out the concert object
//        // get current user
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // set user to concert post
//        post.setUser(user);
//        // save the concert post
//        concertService.save(post);
//
//        // save the images
//        fileUploadController.uploadImages(req, post, model, mf);
//
//        return "redirect:/concerts";
//    }
//
////    @GetMapping("/reviews/{id}/edit")
////    public String showEditForm(@PathVariable long id, Model viewAndModel) {
////        Review review = reviewService.findOne(id);
////        viewAndModel.addAttribute("review", review);
////        return "reviews/edit";
////    }
//
////    @PostMapping("/reviews/{id}/edit")
////    public String updatePost(@PathVariable long id, @ModelAttribute Review post) {
////        post.setId(id);
////        reviewService.save(post);
////        return "redirect:/reviews";
////    }
//
//    @PostMapping("/concerts/{id}/delete")
//    public String delete(@PathVariable long id) {
//        concertService.delete(id);
//        return "redirect:/concerts";
//    }

    }
