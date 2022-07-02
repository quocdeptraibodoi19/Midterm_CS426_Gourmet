package com.example.gourmet.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Activity_Fragment.TransactionHistoryFragment;
import com.example.gourmet.DataElement.TransactionElement;
import com.example.gourmet.R;

import java.util.ArrayList;

public class HistoryTransactionAdapter extends RecyclerView.Adapter<HistoryTransactionAdapter.HistoryTransactionViewHolder> {
    private final LayoutInflater layoutInflater;
    private ArrayList<TransactionElement> transactionElements;
    private final TransactionHistoryFragment transactionHistoryFragment;
    public HistoryTransactionAdapter(Context context, TransactionHistoryFragment fragment){
        layoutInflater = LayoutInflater.from(context);
        transactionElements = new ArrayList<>();
        transactionHistoryFragment = fragment;
    }
    @NonNull
    @Override
    public HistoryTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.transaction_item_fragment,parent,false);
        return new HistoryTransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryTransactionViewHolder holder, int position) {
        holder.dateTextview.setText(transactionElements.get(position).getTransDate());
        holder.totalsSumTextview.setText(String.valueOf(transactionElements.get(position).getTotal()));
        holder.transactionIdTextview.setText(String.valueOf(transactionElements.get(position).getID()));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Log.d("Nhan", "onClick: Address"+transactionElements.get(position).getAddressUser());
                bundle.putInt("TransID",transactionElements.get(holder.getAdapterPosition()).getID());
                bundle.putString("UserName",transactionElements.get(holder.getAdapterPosition()).getNameUser());
                bundle.putString("UserAddress",transactionElements.get(holder.getAdapterPosition()).getAddressUser());
                bundle.putString("UserPhone",transactionElements.get(holder.getAdapterPosition()).getPhoneUser());
                bundle.putString("TransDate",holder.dateTextview.getText().toString());
                bundle.putFloat("TotalPrice",transactionElements.get(holder.getAdapterPosition()).getTotal());
                NavHostFragment.findNavController(transactionHistoryFragment).navigate(R.id.action_transactionHistoryFragment_to_transactionHistoryDetailFragment,bundle);
            }
        });
    }

    public void setTransactionElements(ArrayList<TransactionElement> transactionElements){
        this.transactionElements = transactionElements;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(transactionElements != null) return transactionElements.size();
        return 0;
    }

    class HistoryTransactionViewHolder extends RecyclerView.ViewHolder{
        private TextView dateTextview,transactionIdTextview, totalsSumTextview;
        private ConstraintLayout itemLayout;
        public HistoryTransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextview = itemView.findViewById(R.id.transactiondatetxtviewID);
            transactionIdTextview = itemView.findViewById(R.id.codetransactiontxtviewID);
            totalsSumTextview = itemView.findViewById(R.id.costTransactiontxtviewID);
            itemLayout = itemView.findViewById(R.id.transaction_item_layout_id);
        }
    }
}
