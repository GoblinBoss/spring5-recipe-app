package ru.nikita.services;

import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikita.commands.IngredientCommand;
import ru.nikita.converters.IngredientCommandToIngredient;
import ru.nikita.converters.IngredientToIngredientCommand;
import ru.nikita.domain.Ingredient;
import ru.nikita.domain.Recipe;
import ru.nikita.repositories.IngredientRepository;
import ru.nikita.repositories.RecipeRepository;
import ru.nikita.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientCommandToIngredient ingredientCommandToIngredient, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<Ingredient> getIngredient() {
        Set<Ingredient> set = new HashSet<>();
        ingredientRepository.findAll().iterator().forEachRemaining(set::add);
        return set;
    }

    @Override
    public Ingredient findById(Long id) {
        Optional<Ingredient> optional = ingredientRepository.findById(id);
        if(!optional.isPresent()){
            throw new RuntimeException("Ingredient not present");
        }
        return optional.get();
    }

    @Override
    public IngredientCommand findIngredientCommandById(Long id) {
        return ingredientToIngredientCommand.convert(findById(id));
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if(!recipeOptional.isPresent()){
            log.error("Recipe not found! id=" + command.getRecipeId());
            return new IngredientCommand();
        }else{
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()) {
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(command.getDescription());
                ingredient.setAmount(command.getAmount());
                ingredient.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM not found!")));
            }else{
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe saveRecipe = recipeRepository.save(recipe);

            return saveRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .map(ingredientToIngredientCommand::convert)
                    .findFirst()
                    .get();
        }

    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
