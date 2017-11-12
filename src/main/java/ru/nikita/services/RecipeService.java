package ru.nikita.services;

import ru.nikita.commands.RecipeCommand;
import ru.nikita.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand testRecipeCommand);
}
