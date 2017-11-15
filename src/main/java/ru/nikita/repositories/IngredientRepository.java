package ru.nikita.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nikita.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
}
