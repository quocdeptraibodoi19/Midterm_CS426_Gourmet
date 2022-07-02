package com.example.gourmet.Adapter;

import android.animation.LayoutTransition;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<ProductElement> products = new ArrayList<>();
    private OnProductClickListener listener;

    public ProductListAdapter() {
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_element, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
        holder.name.setText("Tên sản phẩm");
        holder.price.setText("Giá");
        holder.avatar.setImageResource(R.drawable.raucu);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<ProductElement> products){
        this.products = products;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ImageView avatar;
        private final TextView name;
        private final TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.productAvatarID);
            name = (TextView) itemView.findViewById(R.id.productNameID);
            price = (TextView) itemView.findViewById(R.id.productPriceID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(listener != null && pos != RecyclerView.NO_POSITION){
                        listener.onProductClick(products.get(pos));
                    }
                }
            });
        }
    }

    public interface OnProductClickListener{
        void onProductClick(ProductElement product);
    }

    public void setOnProductClickListener(OnProductClickListener listener){
        this.listener = listener;
    }
}
