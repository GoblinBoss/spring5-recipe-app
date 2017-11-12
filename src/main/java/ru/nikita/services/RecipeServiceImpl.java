package ru.nikita.services;

import lombok.extern.slf4j.Slf4j;
import ru.nikita.commands.RecipeCommand;
import ru.nikita.converters.RecipeCommandToRecipe;
import ru.nikita.converters.RecipeToRecipeCommand;
import ru.nikita.domain.Recipe;
import ru.nikita.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe, RecipeRepository recipeRepository) {
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }
        return recipeOptional.get();
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        if(recipeCommand == null){
            return null;
        }

        Recipe recipeNotSave = recipeCommandToRecipe.convert(recipeCommand);
        Recipe recipeSave = recipeRepository.save(recipeNotSave);
        return recipeToRecipeCommand.convert(recipeSave);
    }
}
