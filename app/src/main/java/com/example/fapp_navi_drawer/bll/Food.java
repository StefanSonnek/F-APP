package com.example.fapp_navi_drawer.bll;

import java.io.Serializable;

public class Food implements Serializable {

    private String name;
    private int calories;
    private int protein;
    private int fat;
    private int carbs;

    public Food(String name, int calories, int protein, int fat, int carbs) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return name + " || Calories: "+ calories + " || Protein: " + protein + " || Carbs: "+ carbs + " || Fat: "+ fat;

    }
}
