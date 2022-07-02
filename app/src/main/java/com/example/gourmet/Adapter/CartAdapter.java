package com.example.gourmet.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DataElement.TransactionProductUnit;
import com.example.gourmet.DataElement.TransactionSingleton;
import com.example.gourmet.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private ArrayList<TransactionProductUnit> transactionProductUnits;
    private final LayoutInflater inflater;
    private final TextView totalTextview;
    public CartAdapter(Context context, TextView totalTextview){
        transactionProductUnits = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        this.totalTextview = totalTextview;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItemView = inflater.inflate(R.layout.sanpham_carted_fragment,parent,false);
        return new CartViewHolder(cartItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final int[] numberProduct = {transactionProductUnits.get(position).getNumProduct()};
        ProductElement productElement = transactionProductUnits.get(position).getProductElement();
        TransactionSingleton transactionSingleton = TransactionSingleton.getInstance();

        Glide.with(inflater.getContext())
                .load(productElement.getImageUrl())
                .centerCrop()
                .into(holder.productAvatar);
        holder.productName.setText(productElement.getNameProduct());
        String price = "";
        price = String.valueOf(productElement.getPrice())+"/"+productElement.getDataUnit();
        holder.productPrice.setText(price);
        holder.countTextView.setText(String.valueOf(numberProduct[0]));
        holder.decreaseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberProduct[0] = numberProduct[0] - 1;
                transactionSingleton.setNumberProduct(productElement.getProductID(),numberProduct[0]);
                if(numberProduct[0] == 0)
                { Log.d("test", "onClick: hola"+ String.valueOf(transactionSingleton.getProductElementArrayList().size()));
                    setTransactionProductUnits(transactionSingleton.getTransactionProductUnitList());}
                else
                    holder.countTextView.setText(String.valueOf(numberProduct[0]));
                totalTextview.setText(String.valueOf(transactionSingleton.getTotalMoneyTransaction()));
            }
        });
        holder.increaseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberProduct[0] = numberProduct[0] + 1;
                transactionSingleton.setNumberProduct(productElement.getProductID(),numberProduct[0]);
                holder.countTextView.setText(String.valueOf(numberProduct[0]));
                totalTextview.setText(String.valueOf(transactionSingleton.getTotalMoneyTransaction()));
            }
        });
    }
    public void setTransactionProductUnits(ArrayList<TransactionProductUnit> transactionProductUnits){
        this.transactionProductUnits = transactionProductUnits;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(transactionProductUnits != null) return transactionProductUnits.size();
        return 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        private ImageView productAvatar, increaseIcon, decreaseIcon;
        private TextView countTextView, productName, productPrice;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productAvatar = itemView.findViewById(R.id.carted_product_id);
            decreaseIcon = itemView.findViewById(R.id.carted_minus_id);
            increaseIcon = itemView.findViewById(R.id.carted_plus_id);
            productName = itemView.findViewById(R.id.carted_product_name_ID);
            productPrice = itemView.findViewById(R.id.carted_product_price_id);
            countTextView = itemView.findViewById(R.id.cart_product_number_id);
        }
    }
}
