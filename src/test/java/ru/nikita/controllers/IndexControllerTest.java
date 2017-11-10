package ru.nikita.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ru.nikita.domain.Recipe;
import ru.nikita.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }


    @Test
    public void testMockMVC() throws Exception {

    }

    @Test
    public void getIndexPage() throws Exception {
        //given
        Set<Recipe> recipeSet = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipeSet.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipeSet.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        //when
        String viewName = indexController.getIndexPage(model);
        //then
        assertEquals(2, recipeService.getRecipes().size());
        assertEquals("index", viewName);
        verify(model, times(1)).addAttribute(eq("recipes"), argThat((Set set) -> set.size() == 2));
        verify(recipeService, times(1));
    }

}