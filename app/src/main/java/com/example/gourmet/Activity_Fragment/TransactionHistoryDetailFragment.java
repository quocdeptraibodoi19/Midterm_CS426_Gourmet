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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.DetailHistoryAdapter;
import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.TransactionDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transaction_detail_fragment,container,false);
        View actionBarFragment = rootView.findViewById(R.id.action_bar_transactiodetailfrag);
        actionBarFragment.findViewById(R.id.back_icon_id).setVisibility(View.VISIBLE);
        actionBarFragment.findViewById(R.id.back_icon_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        actionBarFragment.findViewById(R.id.cart_icon_id).setVisibility(View.INVISIBLE);
        TextView fragmentName = actionBarFragment.findViewById(R.id.name_fragment_id);
        fragmentName.setText("Thông tin đơn hàng");

        TextView nameuser,phone,address,transid,transdate,total;
        nameuser = rootView.findViewById(R.id.user_name_id);
        phone = rootView.findViewById(R.id.phone_user_id);
        address = rootView.findViewById(R.id.trans_add_id);
        transid = rootView.findViewById(R.id.trans_code_id);
        transdate = rootView.findViewById(R.id.trans_date_id);
        total = rootView.findViewById(R.id.total_sum_id);
        nameuser.setText(getArguments().getString("UserName"));
        phone.setText(getArguments().getString("UserPhone"));
        Log.d("Nhan", "onCreateView: indesti: "+ getArguments().getString("UserAddress"));
        address.setText(getArguments().getString("UserAddress"));
        transid.setText(String.valueOf(getArguments().getInt("TransID")));
        transdate.setText(getArguments().getString("TransDate"));
        total.setText(String.valueOf(getArguments().getFloat("TotalPrice")));

        DetailHistoryAdapter detailHistoryAdapter = new DetailHistoryAdapter(getContext(),this);
        RecyclerView historyRecyclerView = rootView.findViewById(R.id.detail_history_view_id);
        historyRecyclerView.setAdapter(detailHistoryAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TransactionDetailViewModel transactionDetailViewModel = new ViewModelProvider(this).get(TransactionDetailViewModel.class);
        transactionDetailViewModel.getTransactionDetailList_OnTransaction(getArguments().getInt("TransID")).observe(getViewLifecycleOwner(), new Observer<List<TransactionDetailElement>>() {
            @Override
            public void onChanged(List<TransactionDetailElement> transactionDetailElements) {
                if(transactionDetailElements != null)
                 detailHistoryAdapter.setTransactionDetailElements((ArrayList<TransactionDetailElement>) transactionDetailElements);
            }
        });

        return rootView;
    }
}
