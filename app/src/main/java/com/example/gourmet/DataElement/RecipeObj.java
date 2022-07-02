package com.example.gourmet.DataElement;

import java.util.ArrayList;

public class RecipeObj {
    String recipeName;
    ArrayList<String> recipeStepList;
    ArrayList<String> instructionList;
    String imgUrl;
    RecipeObj(){
        recipeStepList = new ArrayList<>();
        instructionList = new ArrayList<>();
        recipeName = "";
        imgUrl = "";
    }

    public RecipeObj(String recipeName, ArrayList<String> recipeStepList, ArrayList<String> instructionList, String imgUrl) {
        this.recipeName = recipeName;
        this.recipeStepList = recipeStepList;
        this.instructionList = instructionList;
        this.imgUrl = imgUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<String> getRecipeStepList() {
        return recipeStepList;
    }

    public ArrayList<String> getInstructionList() {
        return instructionList;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setRecipe(String recipeName, ArrayList<String> recipeStepList, ArrayList<String>instructionList, String imgUrl ){
        this.recipeName = recipeName;
        this.recipeStepList = recipeStepList;
        this.instructionList = instructionList;
        this.imgUrl = imgUrl;
    }
}
