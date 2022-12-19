package com.stride.striders.controller;


import com.stride.striders.entity.SpeedTraining;
import com.stride.striders.repository.SpeedTrainingRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SpeedTrainingController {

    private SpeedTrainingRepository speedTrainingRepository;

    public SpeedTrainingController(SpeedTrainingRepository speedTrainingRepository) {
        this.speedTrainingRepository = speedTrainingRepository;
    }

    @GetMapping("/addingtraining")
    public String showSignUpForm(SpeedTraining speedTraining) {
        return "add-Training";
    }

    @PostMapping("/addtraining")
    public String addUser(@Validated SpeedTraining speedTraining, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-Training";
        }
        speedTrainingRepository.save(speedTraining);
        return "redirect:/speedTrainings";
    }

    @GetMapping("/training-details/{id}")
    public String getDetailedTraining(@PathVariable Integer id, Model model) {

        Optional<SpeedTraining> trainingDetails = speedTrainingRepository.findById(id);
        model.addAttribute("trainingDetails", trainingDetails.get());
        return "training-details";
    }

    @GetMapping("/editTraining/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        SpeedTraining speedTraining = speedTrainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Training Id " + id));
        model.addAttribute("speedTraining", speedTraining);
        return "edit-training";
    }

    @PostMapping("updatetraining/{id}")
    public String updateTraining(@PathVariable("id") Integer id,
                                 @Valid SpeedTraining speedTraining,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            speedTraining.setId(id);
            return "edit-training";
        }
        speedTrainingRepository.save(speedTraining);
        return "redirect:/speedTrainings";

    }

    @GetMapping("deleteTraining/{id}")
    public String deleteTraining(@PathVariable("id") Integer id) {
        speedTrainingRepository.deleteById(id);
        return "redirect:/speedTrainings";
    }


}
