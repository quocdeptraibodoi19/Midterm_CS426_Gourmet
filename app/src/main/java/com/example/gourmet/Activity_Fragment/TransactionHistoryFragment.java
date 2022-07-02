package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.HistoryTransactionAdapter;
import com.example.gourmet.DataElement.TransactionElement;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.TransactionViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionHistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transaction_history_fragment,container,false);
        View NavFragment = rootView.findViewById(R.id.navigationbarID_transactionhistoryfrag);
        View ActionBarFragment = rootView.findViewById(R.id.action_bar_historytransaction);
        TextView namefragment = ActionBarFragment.findViewById(R.id.name_fragment_id);
        namefragment.setText("Lịch Sử Đặt Hàng");
        ImageView cartIcon = ActionBarFragment.findViewById(R.id.cart_icon_id);
        cartIcon.setVisibility(View.GONE);
        NavFragment.findViewById(R.id.shopIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TransactionHistoryFragment.this).navigate(R.id.action_transactionHistoryFragment_to_mapsFragment);
            }
        });
        NavFragment.findViewById(R.id.homeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TransactionHistoryFragment.this).navigate(R.id.action_transactionHistoryFragment_to_homeFragment);
            }
        });
        NavFragment.findViewById(R.id.recipeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TransactionHistoryFragment.this).navigate(R.id.action_transactionHistoryFragment_to_recipeListFragment);
            }
        });

        HistoryTransactionAdapter historyTransactionAdapter = new HistoryTransactionAdapter(getContext(),TransactionHistoryFragment.this);
        RecyclerView historyRecyclerView = rootView.findViewById(R.id.transactionhistoryrecyclerviewID);
        historyRecyclerView.setAdapter(historyTransactionAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // populate view model
        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.getTransactionList().observe(getViewLifecycleOwner(), new Observer<List<TransactionElement>>() {
            @Override
            public void onChanged(List<TransactionElement> transactionElements) {
                if(transactionElements != null)
                historyTransactionAdapter.setTransactionElements((ArrayList<TransactionElement>) transactionElements);
            }
        });
        return rootView;
    }
}
