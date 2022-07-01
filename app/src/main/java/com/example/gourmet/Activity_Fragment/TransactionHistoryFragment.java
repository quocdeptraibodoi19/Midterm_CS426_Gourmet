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

import java.util.Objects;

public class TransactionHistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transaction_history_fragment,container,false);
        View fragment = rootView.findViewById(R.id.navigationbarID_transactionhistoryfrag);
        fragment.findViewById(R.id.shopIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TransactionHistoryFragment.this).navigate(R.id.action_transactionHistoryFragment_to_mapsFragment);
            }
        });
        fragment.findViewById(R.id.homeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TransactionHistoryFragment.this).navigate(R.id.action_transactionHistoryFragment_to_homeFragment);
            }
        });
        fragment.findViewById(R.id.recipeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TransactionHistoryFragment.this).navigate(R.id.action_transactionHistoryFragment_to_recipeListFragment);
            }
        });
        return rootView;
    }
}
