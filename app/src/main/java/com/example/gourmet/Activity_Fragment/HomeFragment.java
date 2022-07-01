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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.CategoryAdapter;
import com.example.gourmet.DataElement.CategoryObj;
import com.example.gourmet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment,container,false);
        View fragment = rootView.findViewById(R.id.navigationbarID_homefrag);
        List<CategoryObj> categoryObjList = new ArrayList<>();

        // Populate categories
        categoryObjList.add(new CategoryObj("Thit,Ca,Trung","https://suckhoe123.vn/uploads/suc-khoe/2021_05/20190614_114413_121485_thit-do.max-1800x1800_2.png"));
        categoryObjList.add(new CategoryObj("Rau Cu","https://images.foody.vn/res/g109/1087610/prof/s1242x600/foody-upload-api-foody-mobile-co-9ff99895-210720095933.jpg"));
        categoryObjList.add(new CategoryObj("Trai cay","https://www.cleanipedia.com/images/5iwkm8ckyw6v/jQPbszwiYvh52wdGEhRZR/fd8faa032296b89e1c5fe34c69347eab/Y2h1bmctdHJhaS1jYXktbmdheS10ZXQuanBn/990w-660h/ch%C6%B0ng-tr%C3%A1i-c%C3%A2y-ng%C3%A0y-t%E1%BA%BFt.jpg"));
        categoryObjList.add(new CategoryObj("Tat Ca","https://cdn.tgdd.vn/2021/01/CookProduct/2-1200x676-6.jpg"));
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),HomeFragment.this);
        categoryAdapter.setCategoryObjList(categoryObjList);
        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.product_list_recyclerview_id);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


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
