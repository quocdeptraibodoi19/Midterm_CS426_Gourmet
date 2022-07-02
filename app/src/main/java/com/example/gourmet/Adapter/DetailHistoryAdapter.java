package com.example.gourmet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Activity_Fragment.TransactionHistoryDetailFragment;
import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailHistoryAdapter extends RecyclerView.Adapter<DetailHistoryAdapter.DetailViewHolder> {
    private ArrayList<TransactionDetailElement> transactionDetailElements;
    private LayoutInflater inflater;
    private TransactionHistoryDetailFragment transactionHistoryDetailFragment;
    public DetailHistoryAdapter(Context context, TransactionHistoryDetailFragment transactionHistoryDetailFragment){
        inflater = LayoutInflater.from(context);
        transactionDetailElements = new ArrayList<>();
        this.transactionHistoryDetailFragment = transactionHistoryDetailFragment;
    }
    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.transaction_detail_product_fragment,parent,false);
        return new DetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ProductViewModel productViewModel = new ViewModelProvider(transactionHistoryDetailFragment).get(ProductViewModel.class);
        try {
            ProductElement productElement = productViewModel.getProductElement_ID_WithoutLiveData(transactionDetailElements.get(position).getProductID());
            holder.nameproduct.setText(productElement.getNameProduct());
            holder.numberproduct.setText(String.valueOf(transactionDetailElements.get(position).getNumOfProduct()));
            holder.priceproduct.setText(String.valueOf(productElement.getPrice())+" / "+productElement.getDataUnit());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void setTransactionDetailElements(ArrayList<TransactionDetailElement> transactionDetailElements){
        this.transactionDetailElements = transactionDetailElements;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(transactionDetailElements != null) return  transactionDetailElements.size();
        return 0;
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{
        TextView nameproduct,priceproduct,numberproduct;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            nameproduct = itemView.findViewById(R.id.name_product_id);
            priceproduct = itemView.findViewById(R.id.price_id);
            numberproduct = itemView.findViewById(R.id.number_id);
        }
    }
}
