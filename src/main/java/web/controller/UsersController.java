package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("usersList", userService.allUsers());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getById(id));
        modelAndView.setViewName("/show");
        return modelAndView;
    }

    @GetMapping("users/new")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");

        userService.add(user);

        return modelAndView;
    }

    @GetMapping("users/{id}/edit")
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", userService.getById(id));

        return modelAndView;
    }

    @PatchMapping("users/{id}")
    public ModelAndView update(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");

        userService.edit(user);

        return modelAndView;
    }

    @DeleteMapping("users/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");

        userService.delete(id);

        return modelAndView;
    }
}
