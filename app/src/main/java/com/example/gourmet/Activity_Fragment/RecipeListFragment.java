package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gourmet.Adapter.RecipeAdapter;
import com.example.gourmet.ExtendView.ExtendedEditView;
import com.example.gourmet.Network.RecipeInflater;
import com.example.gourmet.R;

import java.lang.ref.WeakReference;

public class RecipeListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_list_fragment,container,false);
        View fragment = rootView.findViewById(R.id.navigationbarID_recipelistfrag);

        View ActionBarFragment = rootView.findViewById(R.id.actionBar_homefrag_id);
        TextView namefragment = ActionBarFragment.findViewById(R.id.name_fragment_id);

        namefragment.setText("Công thức nấu ăn");
        ActionBarFragment.findViewById(R.id.cart_icon_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RecipeListFragment.this).navigate(R.id.action_recipeListFragment_to_cartFragment);
            }
        });

        fragment.findViewById(R.id.homeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecipeListFragment.this).navigate(R.id.action_recipeListFragment_to_homeFragment);
            }
        });
        fragment.findViewById(R.id.shopIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecipeListFragment.this).navigate(R.id.action_recipeListFragment_to_mapsFragment);
            }
        });
        fragment.findViewById(R.id.transactionIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecipeListFragment.this).navigate(R.id.action_recipeListFragment_to_transactionHistoryFragment);
            }
        });
        ProgressBar progressBar = rootView.findViewById(R.id.recipe_list_progress_id);
        progressBar.setVisibility(View.GONE);
        RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(),RecipeListFragment.this, R.layout.recipecell_layout,null);
        RecipeInflater recipeInflater = new RecipeInflater(rootView.findViewById(R.id.recipe_list_gridview_id),progressBar);
        ExtendedEditView extendedEditView = rootView.findViewById(R.id.findingBarID_recipelistfrag);
        extendedEditView.search(recipeInflater,recipeAdapter,new WeakReference<ProgressBar>(progressBar));

        return rootView;
    }
}
