package ru.nikita.bootstraps;

import ru.nikita.domain.Difficulty;
import ru.nikita.domain.Recipe;

public class RecipeBootstrap {
    public static void bootstrap(){
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Cornbread Stuffing with Green Olives and Pecans");
        recipe1.setPrepTime(45);
        recipe1.setCookTime(40);
        recipe1.setDifficulty(Difficulty.EASY);
        recipe1.setDirection("1 Preheat the oven to 350ºF. Butter a 9x13-inch baking dish.\n" +
                "2 Toast the pecans: Scatter the pecans on a baking sheet and toast in the oven until slightly darkened and fragrant, 10 to 15 minutes.\n" +
                "Transfer the pecans to a cutting board and roughly chop while still warm. Set aside until needed.\n" +
                "3 Cut the cornbread into cubes and toast: Use a serrated knife to cut the cornbread into roughly 1/2-inch cubes. Be gently as you slice to avoid crumbling the cubes. You should have about 8 cups cubed.\n" +
                "Transfer the cubes and any crumbs the baking sheet used to toast the pecans. Toast for 15 to 20 minutes, stirring partway through, until the cubes look golden around the edges and feel dry to the touch.\n" +
                "Cornbread Stuffing with Olives and Pecans\n" +
                "4 Meanwhile, cook the vegetables: In a large skillet over medium heat, melt the butter. Add the onions and celery, and cook for 5 minutes.\n" +
                "Add the sage, parsley, thyme, salt, pepper, olives, and cranberries. Cook for 3 to 4 minutes longer, or until the vegetables soften and the herbs are very aromatic. Let cool for 10 minutes.\n" +
                "Cornbread Stuffing with Olives and Pecans\n" +
                "5 Mix the stuffing: Gently stir the cooked vegetable mixture into the bowl with the cornbread cubes. Stir in the pecans.\n" +
                "In a small bowl, whisk the eggs, stock and cream together. Pour over the stuffing and toss gently to combine, using your hands if necessary to avoid crumbling the cornbread too much. The stuffing should feel evenly moist; sprinkle with additional chicken stock as needed.\n" +
                "Transfer to the baking dish.\n" +
                "Cornbread Stuffing with Olives and Pecans\n" +
                "5 Bake for 35 to 40 minutes, or until the top is brown and crisp.\n" +
                "6 Serve: Serve the stuffing while warm. Stuffing can also be made a day or two ahead and warmed in the oven before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/cornbread_stuffing_with_green_olives_and_pecans/#ixzz4xqtHVM56");

    }
}