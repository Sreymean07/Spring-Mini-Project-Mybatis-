package org.kshrd.controller;

import org.kshrd.model.Role;
import org.kshrd.model.SignUpWith;
import org.kshrd.model.User;
import org.kshrd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    @RequestMapping("/admin/user-cu")
    public String user_cu(ModelMap model) {
        User user = new User();
        SignUpWith signUpWith = new SignUpWith(1, null);
        String userHash = UUID.randomUUID().toString();
        user.setUserHash(userHash);
        user.setSignUpWith(signUpWith);
        model.addAttribute("USER", user);
        return "/admin/user-cu";
    }

    @RequestMapping(value = "/user-cu", method = RequestMethod.POST)
   // @ResponseBody
    public User userC(@ModelAttribute User user) {
        return user;
    }

    @RequestMapping("/admin/role-cu")
    public String roleCU(ModelMap model) {
        model.addAttribute("ROLE", new Role());
        return "/admin/role-cu";
    }

    @RequestMapping("/admin/role-list")
    public String roleList(){
        return "/admin/role-list";
    }

    @RequestMapping(value = "/role-cu", method = RequestMethod.POST)
    @ResponseBody
    public Role roleC(@ModelAttribute Role role) {
        return role;
    }

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin/user-list")
	@ResponseBody
    public ModelAndView findAll(ModelAndView model) {
        List<User> user = new ArrayList<>();
        user = userService.findAll();
        for (User users : user) {
            System.out.println(users.getPassword());
        }
        model.setViewName("/admin/user-list");
        model.addObject("LIST", user);
        return model;
    }

    @RequestMapping("/admin/detail/{user_hash}")
    public String search(@PathVariable("user_hash") String userHash, ModelMap model) {
        model.addAttribute("USER", userService.search(userHash));
        return "/admin/detail";
    }

    @RequestMapping("/admin/update/{user_hash}")
    public String update_user(@PathVariable("user_hash") String userHash, ModelMap model) {
        model.addAttribute("user", userService.search(userHash));
        return "/admin/update-user";
    }

    @PostMapping("/update/{userHash}")
    public String update(@PathVariable("userHash") String userHash ,@ModelAttribute User user) {
        user.setUserHash(userHash);
        System.out.println(user.getUserHash());
        userService.updateByUserHash(user);
        return "redirect:/admin/user-list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    // @ResponseBody
    public String save(@ModelAttribute User user) {
        System.out.print(user.getUserHash());
        userService.save(user);
        System.out.print(user.getUserHash());
        return "redirect:/admin/user-list";
    }


    @RequestMapping("/delete/{user_hash}")
    public String delete(@PathVariable("user_hash") String userHash, ModelAndView model) {
        userService.deleteByUserHash(userHash);
        return "redirect:/admin/user-list";
    }

    @RequestMapping({"/","/admin", "/admin/", "/admin/dashboard","/admin/dashboard/"})
    public String count(ModelMap model){
        model.addAttribute("COUNTTOTAL", userService.countTotal());
        model.addAttribute("COUNTMALE", userService.countMale());
        model.addAttribute("COUNTFEMALE", userService.countFemale());
        return "/admin/dashboard";

    }

}
