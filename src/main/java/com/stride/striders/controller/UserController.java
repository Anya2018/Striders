package com.stride.striders.controller;

import com.stride.striders.entity.SpeedTraining;
import com.stride.striders.entity.User;
import com.stride.striders.repository.SpeedTrainingRepository;
import com.stride.striders.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@Controller
public class UserController {

    private UserRepository userRepository;
    private SpeedTrainingRepository speedTrainingRepository;

    public UserController(UserRepository userRepository,
                          SpeedTrainingRepository speedTrainingRepository) {
        this.userRepository = userRepository;
        this.speedTrainingRepository = speedTrainingRepository;
    }

    @GetMapping("/")
    public String viewHomePage(Principal prin, @Valid Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println("username: " + userName);
        model.addAttribute("userName", userName);
        if (prin == null) {
            model.addAttribute("currentuser", "noBodyHome");
        } else {
            model.addAttribute("currentuser", prin.getName());
        }
        return "index";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        populateList(model);
        return "signupForm";
    }

    @GetMapping("/training")
    public String getTraining() {
        return "training1";
    }

    @GetMapping("/trainingg")
    public String getTrainingg() {
        return "training2";
    }

    @GetMapping("/speedTrainings")
    public String getAllSpeedTrainings(Model model) {
        Iterable<SpeedTraining> listOfSpeedTrainings = speedTrainingRepository.findAll();
        model.addAttribute("listOfSpeedTrainings", listOfSpeedTrainings);
        model.addAttribute("byDate", Comparator.comparing(SpeedTraining::getDate,
                Comparator.reverseOrder()));
        return "allSpeedTrainings";
    }

    @GetMapping("/users")
    public String getUsers(@Valid Model model) {
        Iterable<User> listOfUsers = userRepository.findAll();
        model.addAttribute("listOfUsers", listOfUsers);
        return "users";
    }


//    @PostMapping("/addUser")
//    @ResponseBody
//    public User addUser(@RequestBody User user) {
//        userRepository.save(user);
//        return user;
//    }

    @PostMapping("/processRegister")
    public String processRegister(@Validated User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_success";
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            userRepository.findById(id).get();
            return new ResponseEntity<User>(userRepository.findById(id).get(),
                    HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        System.out.println("Deleted");
    }

    @RequestMapping(value = "/populateDropDownList", method = RequestMethod.GET)
    public String populateList(Model model) {

        List<String> countriesList = new ArrayList<String>();
        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countriesList.add(obj.getDisplayCountry(Locale.UK));
            Collections.sort(countriesList);
        }
        model.addAttribute("options", countriesList);
        return "dropDownList/dropDownList.html";
    }


    //To display the name of Authenticated user (just to check)
    @GetMapping("/tt")
    public String showHomePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("username: " + auth.getName());
        return "index";
    }

}
