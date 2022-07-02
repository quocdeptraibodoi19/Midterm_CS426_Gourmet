package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.gourmet.R;

import java.util.ArrayList;

public class RecipeDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_detail_fragment,container,false);

        TextView nameRecipeTextView = rootView.findViewById(R.id.nameRecipeID);
        nameRecipeTextView.setText(getArguments().getString("Recipe_Name_String"));

        TextView ingredientTextView = rootView.findViewById(R.id.recipeIngredientID);
        ArrayList<String> ingredientList;
        ingredientList = getArguments().getStringArrayList("Recipe_Ingredient_ArrayList");
        String ingredients = "";
        for(int i=0;i<ingredientList.size(); i++)
            ingredients = ingredients + ingredientList.get(i) + "\n";
        ingredientTextView.setText(ingredients);

        TextView instructionTextView = rootView.findViewById(R.id.instructionrecipID);
        ArrayList<String> instructionList;
        instructionList = getArguments().getStringArrayList("Recipe_Instruction_ArrayList");
        String instruction = "";
        for(int i=0; i< instructionList.size(); i++)
            instruction = instruction + instructionList.get(i) + "\n";
        instructionTextView.setText(instruction);

        Glide.with(getContext()).load(getArguments().getString("Recipe_Url_String"))
                .centerCrop()
                .into((ImageView) rootView.findViewById(R.id.RecipeImageID));

        return rootView;
    }
}
