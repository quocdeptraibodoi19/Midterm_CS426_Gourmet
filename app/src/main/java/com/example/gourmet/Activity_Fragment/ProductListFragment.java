package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.ProductListAdapter;
import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.ExtendView.ExtendedEditView;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.ProductViewModel;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private ProductViewModel viewModel;
    private ExtendedEditView editView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_list_fragment,container,false);
        View fragment = rootView.findViewById(R.id.navigationbarID_productlistfrag);
        View ActionBarFragment = rootView.findViewById(R.id.actionBar_homefrag_id);
        TextView namefragment = ActionBarFragment.findViewById(R.id.name_fragment_id);

        String category = getArguments().getString("category", "");
        String name = getArguments().getString("name", "");

        namefragment.setText(name);
        ActionBarFragment.findViewById(R.id.cart_icon_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProductListFragment.this).navigate(R.id.action_productListFragment_to_cartFragment);
            }
        });

        editView = rootView.findViewById(R.id.findingBarID_productlistfrag);
        adapter = new ProductListAdapter();
        viewModel =  new ViewModelProvider(this).get(ProductViewModel.class);

        recyclerView = rootView.findViewById(R.id.product_list_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        adapter.setOnProductClickListener(new ProductListAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(ProductElement product) {
                int id = product.getProductID();

                Bundle bundle = new Bundle();
                bundle.putInt("ProductId", id);

                NavHostFragment.findNavController(ProductListFragment.this).navigate(R.id.action_productListFragment_to_productDetailFragment, bundle);
            }
        });

        viewModel.getProductList(category).observe(getViewLifecycleOwner(), productElements -> {
            adapter.setProducts(productElements);
        });

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
