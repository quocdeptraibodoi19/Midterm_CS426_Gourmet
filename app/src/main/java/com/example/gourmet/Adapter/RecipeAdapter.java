package com.example.gourmet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.gourmet.DataElement.RecipeObj;
import com.example.gourmet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecipeAdapter extends ArrayAdapter<RecipeObj> {
    private ArrayList<RecipeObj> recipeObjArrayList;
    private int ItemLayoutId;

    public RecipeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RecipeObj> objects) {
        super(context, resource, objects);
        recipeObjArrayList = objects;
        ItemLayoutId = resource;
    }

    @Override
    public int getCount() {
        return recipeObjArrayList.size();
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
        return convertView;
    }
}
