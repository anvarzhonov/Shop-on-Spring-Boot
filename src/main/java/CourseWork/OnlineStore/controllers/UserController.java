package CourseWork.OnlineStore.controllers;

import CourseWork.OnlineStore.models.Product;
import CourseWork.OnlineStore.models.Role;
import CourseWork.OnlineStore.models.User;
import CourseWork.OnlineStore.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "AdminPanel/userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "AdminPanel/userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/admin/user";
    }
    @GetMapping("/delete/{user}")
    public String userDelete(@PathVariable("user") long id, Model model) {
        userRepo.deleteById(id);
        model.addAttribute("user", userRepo.findAll());
        return "redirect:/admin/user";
    }


}
