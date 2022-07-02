package com.example.gourmet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.DataElement.TransactionElement;
import com.example.gourmet.R;

import java.util.ArrayList;

public class HistoryTransactionAdapter extends RecyclerView.Adapter<HistoryTransactionAdapter.HistoryTransactionViewHolder> {
    private final LayoutInflater layoutInflater;
    private ArrayList<TransactionElement> transactionElements;
    public HistoryTransactionAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        transactionElements = new ArrayList<>();
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
        public HistoryTransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextview = itemView.findViewById(R.id.transactiondatetxtviewID);
            transactionIdTextview = itemView.findViewById(R.id.codetransactiontxtviewID);
            totalsSumTextview = itemView.findViewById(R.id.costTransactiontxtviewID);
        }
    }
}
