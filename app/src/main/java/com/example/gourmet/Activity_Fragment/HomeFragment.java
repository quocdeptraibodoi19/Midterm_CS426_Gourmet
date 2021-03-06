package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.CategoryAdapter;
import com.example.gourmet.DataElement.CategoryObj;
import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment,container,false);

        View Navfragment = rootView.findViewById(R.id.navigationbarID_homefrag);
        View ActionBarFragment = rootView.findViewById(R.id.actionBar_homefrag_id);
        TextView namefragment = ActionBarFragment.findViewById(R.id.name_fragment_id);
        namefragment.setText("Gourmet");
        ActionBarFragment.findViewById(R.id.cart_icon_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_cartFragment);
            }
        });

        List<CategoryObj> categoryList = new ArrayList<>();

        // Populate categories
        categoryList.add(new CategoryObj("meat","Th???t, C??, Tr???ng","https://suckhoe123.vn/uploads/suc-khoe/2021_05/20190614_114413_121485_thit-do.max-1800x1800_2.png"));
        categoryList.add(new CategoryObj("dairy","S???a","data:image/jpeg;base64,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"));
        categoryList.add(new CategoryObj("vegetable","Rau c???","https://images.foody.vn/res/g109/1087610/prof/s1242x600/foody-upload-api-foody-mobile-co-9ff99895-210720095933.jpg"));
        categoryList.add(new CategoryObj("fruit","Tr??i c??y","https://www.cleanipedia.com/images/5iwkm8ckyw6v/jQPbszwiYvh52wdGEhRZR/fd8faa032296b89e1c5fe34c69347eab/Y2h1bmctdHJhaS1jYXktbmdheS10ZXQuanBn/990w-660h/ch%C6%B0ng-tr%C3%A1i-c%C3%A2y-ng%C3%A0y-t%E1%BA%BFt.jpg"));
        categoryList.add(new CategoryObj("","T???t c???","https://cdn.tgdd.vn/2021/01/CookProduct/2-1200x676-6.jpg"));

        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.product_list_recyclerview_id);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),HomeFragment.this);

        categoryAdapter.setCategoryObjList(categoryList);
        categoryAdapter.setOnCategoryClickListener(category -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", category.getName());
            bundle.putString("name", category.getDisplayName());
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_productListFragment, bundle);
        });

        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Navfragment.findViewById(R.id.shopIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_mapsFragment);
            }
        });
        Navfragment.findViewById(R.id.transactionIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_transactionHistoryFragment);
            }
        });
        Navfragment.findViewById(R.id.recipeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_recipeListFragment);
            }
        });

        if(Navfragment != null) Log.d("Quoc", "onCreateView: not null Navfragment");
        if(rootView == null) Log.d("Quoc", "onCreateView: null");

        return rootView;
    }
    @Override
    //Pressed return button - returns to the results menu
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    getActivity().finish();

                    return true;
                }
                return false;
            }
        });
    }
}
