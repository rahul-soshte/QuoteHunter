package com.hunterlab.hunter.motiva;

/**
 * Created by hunter on 26/11/17.
 */

public class Category {
    public String id;
    public String category_name;

    public Category()
    {

    }
    public Category(String category_name)
    {
        this.category_name=category_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }
}
