package com.example.gourmet.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.gourmet.Activity_Fragment.RecipeListFragment;
import com.example.gourmet.DataElement.RecipeObj;
import com.example.gourmet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecipeAdapter extends ArrayAdapter<RecipeObj> {
    private ArrayList<RecipeObj> recipeObjArrayList;
    private int ItemLayoutId;
    private final RecipeListFragment recipeListFragment;
    public RecipeAdapter(@NonNull Context context, RecipeListFragment recipeListFragment, int resource, @NonNull ArrayList<RecipeObj> objects) {
        super(context, resource, objects);
        recipeObjArrayList = objects;
        ItemLayoutId = resource;
        this.recipeListFragment = recipeListFragment;
    }

    @Override
    public int getCount() {
        return recipeObjArrayList.size();
    }

    public void setRecipeObjArrayList(ArrayList<RecipeObj> recipeObjArrayList){
        this.recipeObjArrayList = recipeObjArrayList;
        notifyDataSetChanged();
    }
    public ArrayList<RecipeObj> getRecipeObjArrayList(){
        return recipeObjArrayList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(ItemLayoutId,null);
        ImageView recipeAvartarImg = convertView.findViewById(R.id.recipe_cell_avatar_id);
        TextView nameRecipe = convertView.findViewById(R.id.recipe_cell_name_id);
        Glide.with(getContext())
                .load(recipeObjArrayList.get(position).getImgUrl())
                .centerCrop()
                .into(recipeAvartarImg);
        nameRecipe.setText(recipeObjArrayList.get(position).getRecipeName());
        recipeAvartarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("Recipe_Name_String",recipeObjArrayList.get(position).getRecipeName());
                bundle.putString("Recipe_Url_String",recipeObjArrayList.get(position).getImgUrl());
                bundle.putStringArrayList("Recipe_Ingredient_ArrayList",recipeObjArrayList.get(position).getRecipeIngredientList());
                bundle.putStringArrayList("Recipe_Instruction_ArrayList",recipeObjArrayList.get(position).getInstructionList());

                NavHostFragment.findNavController(recipeListFragment).navigate(R.id.action_recipeListFragment_to_recipeDetailFragment,bundle);
            }
        });
        return convertView;
    }
}
