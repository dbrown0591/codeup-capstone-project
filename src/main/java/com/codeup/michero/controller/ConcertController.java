package com.codeup.michero.controller;

import com.codeup.michero.daos.ImageRepository;
import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Image;
import com.codeup.michero.models.Review;
import com.codeup.michero.models.User;
import com.codeup.michero.services.ConcertService;
import com.codeup.michero.services.ImageService;
import com.codeup.michero.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ConcertController {

    // 1. Create an instance variable with your dependency
    private final ConcertService concertService;
    private final UsersRepository usersRepository;
    private final FileUploadController fileUploadController;
    private final ImageService is;
    private final ReviewService reviewService;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public ConcertController(ConcertService concertService,
                             UsersRepository usersRepository,
                             ImageService imageService,
                             FileUploadController fc,
                             ReviewService reviewService) {
        this.concertService = concertService; // This the first time we assign something to postService
        this.usersRepository = usersRepository;
        this.fileUploadController = fc;
        this.reviewService = reviewService;
        this.is = imageService;
    }

    @RequestMapping("/concerts")
    public String index(Model viewAndModel) {
        // create a hash map
        HashMap<Concert,List<String>> concertImageMap = new HashMap<>();

        // get list of concerts
        Iterable<Concert> list_of_concerts = concertService.findAll();

        // go through each concert, find its images, and add it to the map
        for(Concert c : list_of_concerts){
            // get all the images for this concert
            Iterable<Image> concertImages = this.is.findByConcert_id(c.getId());
            List<String> url_list = new ArrayList<>();

            // extract URLs
            for(Image i : concertImages){
                url_list.add(i.getUrl());
            }
            // add concert key and image value to map
            concertImageMap.put(c, url_list);
        }

        viewAndModel.addAttribute("map", concertImageMap);
        viewAndModel.addAttribute("list_of_concerts", list_of_concerts);

        return "concerts/index";
    }

    @RequestMapping("/concerts/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
//
//        post.setId(id);
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

        Iterable<Image> concertImages = this.is.findByConcert_id(id);
        List<String> url_list = new ArrayList<>();

        // extract URLs
        for(Image i : concertImages){
            url_list.add(i.getUrl());
        }

        // get the concert object
        Concert concert = concertService.findOne(id);
        viewAndModel.addAttribute("concert", concert);
        viewAndModel.addAttribute("url_list", url_list);
        viewAndModel.addAttribute("concertReview", new Review());
        return "concerts/show";
    }

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
        // get current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // set user to concert post
        post.setUser(user);
        // save the concert post
        concertService.save(post);

        // save the images
        fileUploadController.uploadImages(req, post, model, mf);

        return "redirect:/concerts";
    }

    @GetMapping("/concerts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Concert concert = concertService.findOne(id);
        viewAndModel.addAttribute("concert", concert);
        return "concerts/edit";
    }

    @PostMapping("/concerts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Concert post, HttpServletRequest req,
                             @RequestParam(name="image") List<MultipartFile> mf,
                             Model model) {
        post.setId(id);
        // save the concert post
        // fill out the concert object
        // get current user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // set user to concert post
        post.setUser(user);
        // save the concert post
        concertService.save(post);


        // find the old images associated with this concert
        Iterable<Image> images = is.findByConcert_id(post.getId());
        // delete them
        for (Image image : images) {
            is.delete(image.getId());
        }

        // save the images
        fileUploadController.uploadImages(req, post, model, mf);

        concertService.save(post);
        return "redirect:/concerts";
    }

    @PostMapping("/concerts/{id}/delete")
    public String delete(@PathVariable long id) {

        // get concert
        Concert c = concertService.findOne(id);

        // delete the reviews
        Iterable<Review> reviews = reviewService.findByConcert_id(id);
        for(Review r : reviews){
            reviewService.delete(r.getId());
        }
        // delete the images
        Iterable<Image> images = is.findByConcert_id(id);
        for(Image i : images){
            is.delete(i.getId());
        }
        // delete the concert
        concertService.delete(id);
        return "redirect:/concerts";
    }

}
