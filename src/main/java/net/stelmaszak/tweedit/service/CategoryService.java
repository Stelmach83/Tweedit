package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getCategoryById(Long id);

    void saveCategory(Category category);

}
