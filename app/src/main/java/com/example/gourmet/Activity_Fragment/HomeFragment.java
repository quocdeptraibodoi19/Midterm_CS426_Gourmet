package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gourmet.R;

import java.util.Objects;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment,container,false);
        View fragment = rootView.findViewById(R.id.navigationbarID_homefrag);
        fragment.findViewById(R.id.shopIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_mapsFragment);
            }
        });
        fragment.findViewById(R.id.transactionIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_transactionHistoryFragment);
            }
        });
        fragment.findViewById(R.id.recipeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_recipeListFragment);
            }
        });
        if(fragment != null) Log.d("Quoc", "onCreateView: not null fragment");
        if(rootView == null) Log.d("Quoc", "onCreateView: null");
        return rootView;
    }
}
