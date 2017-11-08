package ru.nikita.controllers;

import ru.nikita.domain.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nikita.repositories.CategoryRepository;

import java.util.Optional;

@Controller
public class IndexController {
    CategoryRepository categoryRepository;

    public IndexController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"*", "*/", "*/index"})
    public String getIndexPage(){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("american");

        System.out.println(categoryOptional.get().getId());
        System.out.println(categoryOptional.get().getDescription());
        return "index";
    }
}
