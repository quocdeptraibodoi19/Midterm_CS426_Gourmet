package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gourmet.R;

public class ProductListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_list_fragment,container,false);
        View fragment = rootView.findViewById(R.id.navigationbarID_productlistfrag);
        fragment.findViewById(R.id.homeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductListFragment.this).navigate(R.id.action_productListFragment_to_homeFragment);
            }
        });
        fragment.findViewById(R.id.shopIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductListFragment.this).navigate(R.id.action_productListFragment_to_mapsFragment);
            }
        });
        fragment.findViewById(R.id.recipeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductListFragment.this).navigate(R.id.action_productListFragment_to_recipeListFragment);

            }
        });
        fragment.findViewById(R.id.transactionIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductListFragment.this).navigate(R.id.action_productListFragment_to_transactionHistoryFragment);

            }
        });
        return rootView;
    }
}
