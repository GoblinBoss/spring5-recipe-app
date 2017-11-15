package ru.nikita.services;

import ru.nikita.commands.IngredientCommand;
import ru.nikita.commands.RecipeCommand;
import ru.nikita.domain.Ingredient;
import ru.nikita.domain.Recipe;

import java.util.Set;

public interface IngredientService {
    Set<Ingredient> getIngredient();

    Ingredient findById(Long id);

    IngredientCommand findIngredientCommandById(Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand IngredientCommand);

    void deleteById(Long id);

}
