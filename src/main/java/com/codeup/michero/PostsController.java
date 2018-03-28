package com.codeup.michero;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Reviews;
import com.codeup.michero.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {
    // 1. Create an instance variable with your dependency
    private final PostService postService;
    private final UsersRepository usersRepository;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public PostsController(PostService postService, UsersRepository usersRepository) {
        this.postService = postService; // This the first time we assign something to postService
        this.usersRepository = usersRepository;
    }

    @RequestMapping("/posts")
    public String index(Model viewAndModel) {
        /*List<Reviews> posts = Arrays.asList(
            new Reviews("Reviews A", "Body A"),
            new Reviews("Reviews B", "Body B"),
            new Reviews("Reviews C", "Body C")
        );*/
        Iterable<Reviews> posts = postService.findAll();

        viewAndModel.addAttribute("posts", posts);

        return "posts/index";
    }

    @RequestMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        //Reviews post = new Reviews("Test post", "Test body");
        Reviews post = postService.findOne(id);

        viewAndModel.addAttribute("post", post);

        return "posts/show";
    }

    @RequestMapping("/posts/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Reviews());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Reviews post) {
        Users users = usersRepository.findOne(2L);
        post.setUsers(users);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Reviews post = postService.findOne(id);
        viewAndModel.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Reviews post) {
        post.setId(id);
        postService.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

}
