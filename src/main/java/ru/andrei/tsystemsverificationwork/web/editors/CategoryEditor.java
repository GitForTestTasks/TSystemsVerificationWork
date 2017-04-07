package ru.andrei.tsystemsverificationwork.web.editors;

import ru.andrei.tsystemsverificationwork.database.models.Category;

import java.beans.PropertyEditorSupport;

public class CategoryEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        Category category = new Category();
        category.setName(text);
        this.setValue(category);
    }

    @Override
    public String getAsText() {
        Category category = (Category) this.getValue();

        if (category == null) {
            return "";
        } else return category.getName();
    }
}