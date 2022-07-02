package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DataElement.TransactionSingleton;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.ProductViewModel;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class ProductDetailFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView description;
    private TextView amount;
    private ProductViewModel viewModel;

    private ImageView increase;
    private ImageView decrease;
    private int numOfProducts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail_fragment,container,false);

        image = rootView.findViewById(R.id.productImageId);
        name = rootView.findViewById(R.id.productNameI);
        price = rootView.findViewById(R.id.productPriceID);
        description = rootView.findViewById(R.id.productDescriptionID);
        amount = rootView.findViewById(R.id.numberproductID);
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        increase = rootView.findViewById(R.id.plusID);
        decrease = rootView.findViewById(R.id.minusID);

        numOfProducts = Integer.parseInt(amount.getText().toString());

        int productId = getArguments().getInt("ProductId", -1);
        Log.d("Details", "ProductId: " + String.valueOf(productId));

        viewModel.getProductById(productId).observe(getViewLifecycleOwner(), new Observer<ProductElement>() {
            @Override
            public void onChanged(ProductElement productElement) {
                Glide.with(ProductDetailFragment.this)
                        .load(productElement.getImageUrl())
                        .centerCrop()
                        .into(image);
                name.setText(productElement.getNameProduct());
                price.setText(String.format("%d/%s", (int)productElement.getPrice(), productElement.getDataUnit()));
                description.setText(productElement.getProductDescription());
            }
        });

        increase.setOnClickListener(view -> setAmount(1));
        decrease.setOnClickListener(view -> setAmount(-1));

        Button cartingBtn = rootView.findViewById(R.id.cartingButtonID);
        cartingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionSingleton transactionSingleton = TransactionSingleton.getInstance();
                try {
                    ProductElement productElement = viewModel.getProductElement_ID_WithoutLiveData(productId);
                    transactionSingleton.addProductElementArrayList(productElement,numOfProducts);
                    Log.d("QuocTransaction", "onClick: Transaction Successfully "+productElement.getNameProduct() + " "+ String.valueOf(transactionSingleton.getNumberProduct_Id(productId)));
                    getActivity().onBackPressed();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }

    void setAmount(int n){
        numOfProducts += n;
        amount.setText(String.valueOf(numOfProducts));
    }
}
