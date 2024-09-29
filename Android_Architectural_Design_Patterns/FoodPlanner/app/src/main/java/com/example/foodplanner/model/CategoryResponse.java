package com.example.foodplanner.model;

import java.util.List;

public class CategoryResponse {
    private List<Category_Pojo> categories;

    public List<Category_Pojo> getCategories() {
        return categories;
    }

    public void setCategories(List<Category_Pojo> categories) {
        this.categories = categories;
    }
}

