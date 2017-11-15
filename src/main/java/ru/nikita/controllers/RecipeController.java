package ru.nikita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikita.commands.RecipeCommand;
import ru.nikita.services.RecipeService;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show/")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("/recipe/")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + recipeCommand.getId() + "/show/";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String delete(@PathVariable String id){
        recipeService.deleteById(new Long(id));
        return "redirect:/";
    }
}
