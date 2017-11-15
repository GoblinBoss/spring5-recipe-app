package ru.nikita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikita.commands.IngredientCommand;
import ru.nikita.services.IngredientService;
import ru.nikita.services.RecipeService;
import ru.nikita.services.UnitOfMeasureService;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId") String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(recipeId)));
        return "/recipe/ingredient/list";
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showById(@PathVariable("recipeId") String recipeId, @PathVariable("ingredientId") String ingredientId, Model model){
//        model.addAttribute("recipe", recipeService.findById(new Long(recipeId)));
        model.addAttribute("ingredient", ingredientService.findById(new Long(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipe(Model model, @PathVariable("recipeId") String recipeId){
        model.addAttribute("ingredient", new IngredientCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipe(@PathVariable("recipeId") String recipeId, @PathVariable("ingredientId") String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findIngredientCommandById(new Long(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command, @PathVariable("recipeId") String recipeId){
        IngredientCommand ingredientCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredients/";
    }
//
//    @GetMapping
//    @RequestMapping("/recipe/{recipeId}/delete")
//    public String delete(@PathVariable("recipeId") String recipeId){
//        recipeService.deleteById(new Long(recipeId));
//        return "redirect:/recipe/" + recipeId + "/ingredient"
//    }
}
