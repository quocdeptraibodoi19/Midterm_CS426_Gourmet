package com.example.gourmet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gourmet.Activity_Fragment.HomeFragment;
import com.example.gourmet.DataElement.CategoryObj;
import com.example.gourmet.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<CategoryObj> categoryObjList;
    private final LayoutInflater inflater;
    private final HomeFragment homeFragment;
    private Context context;
    public CategoryAdapter(Context context, HomeFragment fragment){
        categoryObjList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        homeFragment = fragment;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.categories_view,parent,false);
       return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.materialButton.setText(categoryObjList.get(position).getName());
        Glide.with(context).
               load(categoryObjList.get(position).getUrl())
                .centerCrop()
                .into(holder.imageView);
    }
    public void setCategoryObjList(List<CategoryObj> list){
        this.categoryObjList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return categoryObjList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private MaterialButton materialButton;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.overlayID);
            materialButton = itemView.findViewById(R.id.catte_buttonID);
            materialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(homeFragment).navigate(R.id.action_homeFragment_to_productListFragment);
                }
            });
        }
    }
}
