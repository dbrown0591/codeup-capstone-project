package com.codeup.michero.controller;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder;

    public UserController(UsersRepository usersRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    @GetMapping("/users/sign-up")
    public String showSignUpForm(Model viewAndModel) {
        viewAndModel.addAttribute("user", new User());
        return "users/sign-up";
    }

    @GetMapping("/users/delete/")
    public String delete(HttpServletRequest r, HttpServletResponse resp){
        // grab id
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        // log the user out
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        r.getSession().invalidate();
        SecurityContextHolder.clearContext();
        // delete the user
        usersRepository.delete(id);
        // redirect to home
        return "redirect:/";
    }
    @PostMapping("/sign-up")
    public String singUpNewUser(@ModelAttribute User user) {
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setprofile_pic("");
        //Was getting error after running program saying that
        //profile_pic cannot be null.
        //Added user.setprofile_pic("") <---empty string
        //needed a place to add a profile pic on the sign up page.
        usersRepository.save(user);
        return "redirect:/login";
    }


}
