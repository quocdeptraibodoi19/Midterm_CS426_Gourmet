package com.example.gourmet.Activity_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.CartAdapter;
import com.example.gourmet.DataElement.TransactionSingleton;
import com.example.gourmet.R;

public class CartFragment extends Fragment {
    TransactionSingleton transactionSingleton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_fragment,container,false);
        transactionSingleton = TransactionSingleton.getInstance();


        TextView totalTextview = rootView.findViewById(R.id.SoTienTextViewID);
        totalTextview.setText(String.valueOf(transactionSingleton.getTotalMoneyTransaction()));

        CartAdapter cartAdapter = new CartAdapter(getContext(),totalTextview);
        cartAdapter.setTransactionProductUnits(transactionSingleton.getTransactionProductUnitList());
        RecyclerView cartRecyclerView = rootView.findViewById(R.id.cartedProductRecyclerViewID);
        cartRecyclerView.setAdapter(cartAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button DeleteCartBtn = rootView.findViewById(R.id.deletecartbtnID);
        Button InsertCartBtn = rootView.findViewById(R.id.insertcartbtnid);

        DeleteCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionSingleton.clear();
                rootView.findViewById(R.id.SoTienTextViewID);
                cartAdapter.setTransactionProductUnits(transactionSingleton.getTransactionProductUnitList());
            }
        });
        InsertCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_cartFragment_to_homeFragment);
            }
        });
        EditText addressedit, phoneedit, nameedit;
        addressedit = rootView.findViewById(R.id.DiaChiEditID);
        phoneedit = rootView.findViewById(R.id.SoDienThoaiEditID);
        nameedit = rootView.findViewById(R.id.HoVaTenEditID);

        rootView.findViewById(R.id.datHangBtnID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionSingleton.setAddress(addressedit.getText().toString());
                transactionSingleton.setNameUser(phoneedit.getText().toString());
                transactionSingleton.setPhoneNumber(nameedit.getText().toString());

            }
        });
        return rootView;
    }
}
