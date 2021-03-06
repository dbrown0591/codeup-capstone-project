package com.codeup.michero.controller;

import com.codeup.michero.daos.ConcertsRepository;
import com.codeup.michero.models.Concert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private ConcertsRepository adDao;

    public HomeController(ConcertsRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/dao-test")
    @ResponseBody
    public Iterable<Concert> daoTest() {
//        Iterable<AdController> ads = adDao.findAll();
//
//        for (AdController ad : ads) {
//            System.out.println("---");
//            System.out.println("  #" + ad.getId());
//            System.out.println("  title: " + ad.getTitle());
//            System.out.println("  description: " + ad.getDescription());
//        }

//        // we are hardcoding values for demonstration, in practice, these would come from a form
//        AdController ad = new AdController("title c", "description c");
//        adDao.save(ad);

        return adDao.findAll();
    }
}
