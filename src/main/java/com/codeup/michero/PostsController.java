package com.codeup.michero;

import com.codeup.michero.daos.UsersRepository;
import com.codeup.michero.models.Post;
import com.codeup.michero.models.Users;
import com.codeup.michero.services.PostService;
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
        /*List<Post> posts = Arrays.asList(
            new Post("Post A", "Body A"),
            new Post("Post B", "Body B"),
            new Post("Post C", "Body C")
        );*/
        Iterable<Post> post = postService.findAll();

        viewAndModel.addAttribute("posts", post);

        return "posts/index";
    }

    @RequestMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        //Post post = new Post("Test post", "Test body");
        Post post = postService.findOne(id);

        viewAndModel.addAttribute("post", post);

        return "posts/show";
    }

    @RequestMapping("/posts/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        Users users = usersRepository.findOne(2L);
        post.setUsers(users);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Post post = postService.findOne(id);
        viewAndModel.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
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
