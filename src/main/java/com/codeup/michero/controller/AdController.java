package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;

import com.codeup.michero.models.Ad;
import com.codeup.michero.models.User;
import com.codeup.michero.services.AdService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    // Auto-wiring
    private AdService adsService;
    private UsersRepository usersRepository;

    public AdController(AdService service, UsersRepository usersRepository) {
        this.adsService = service;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/ads")
    public String showAllAds(Model viewAndModel) {
        Iterable<Ad> ads = adsService.findAll();

        viewAndModel.addAttribute("ads", ads);

        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showIndividualAd(@PathVariable long id, Model viewModel) {
        Ad ad = adsService.findOne(id);

        viewModel.addAttribute("ad", ad);

        return "ads/show";
    }

    @GetMapping("/ads/new")
    public String showCreateAdForm(Model viewModel) {
        Ad ad = new Ad();
        viewModel.addAttribute("ad", ad);
        return "ads/new";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String saveAd(@ModelAttribute Ad ad) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ad.setOwner(usersRepository.findOne(owner.getId()));
        adsService.save(ad);
        return "redirect:/ads";
    }

    @ResponseBody
    @GetMapping("/tests")
    public String test() {
        User user = new User("zach", "codeup", "example.@mail.com");
        // Setter injection
        //Ad ad = new Ad("Ad title", "Ad description");
        // Somebody might forget to call the setter, this is why it's called optional dependency
        //ad.setOwner(user);
        // Constructor injection is required, because there is no way to create a Post without a User
        Ad ad = new Ad("Ad title", "Ad description", user);
        adsService.save(ad);
        return "Saved!";
    }
}
