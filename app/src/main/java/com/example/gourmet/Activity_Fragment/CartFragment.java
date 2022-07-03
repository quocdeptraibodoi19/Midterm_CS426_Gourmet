package com.example.gourmet.Activity_Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gourmet.Adapter.CartAdapter;
import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.DataElement.TransactionElement;
import com.example.gourmet.DataElement.TransactionSingleton;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.TransactionDetailViewModel;
import com.example.gourmet.ViewModel.TransactionViewModel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Todo: save data from the configuration change - Quoc
// (Note that when backstack, fragment is not dead -> data from editview is remained but (in this case oncreaveview is still re-invoked)
// The configuration changes occurs will lead to the death of the old fragment and the birth of the new fragment
// -> data is not remained but the oncreateview method still runs -> may get you confused with the previous case
public class CartFragment extends Fragment {
    TransactionSingleton transactionSingleton;
    private final static String username_code = "com.example.gourmet.Activity_Fragment.ProductListFragment_name";
    private final static String useradd_code = "com.example.gourmet.Activity_Fragment.ProductListFragment_address";
    private final static String userphone_code = "com.example.gourmet.Activity_Fragment.ProductListFragment_phone";
    private final static String userstore_code = "com.example.gourmet.Activity_Fragment.ProductListFragment_store";
    private EditText addressedit, phoneedit, nameedit;
    private RadioButton storebtn1,storebtn2,storebtn3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            addressedit.setText(savedInstanceState.getString(useradd_code));
            phoneedit.setText(savedInstanceState.getString(userphone_code));
            nameedit.setText(savedInstanceState.getString(username_code));
            switch (savedInstanceState.getInt(userstore_code)){
                case 1:
                    storebtn1.setChecked(true);
                    break;
                case 2:
                    storebtn2.setChecked(true);
                    break;
                case 3:
                    storebtn3.setChecked(true);
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Nhi", "onCreateView: cart onbakaview");
        View rootView = inflater.inflate(R.layout.cart_fragment,container,false);

        View ActionBarFragment = rootView.findViewById(R.id.actionBar_homefrag_id);
        TextView namefragment = ActionBarFragment.findViewById(R.id.name_fragment_id);
        ImageView backIncon = ActionBarFragment.findViewById(R.id.back_icon_id);
        backIncon.setVisibility(View.VISIBLE);
        backIncon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        namefragment.setText("Giỏ hàng");
        ActionBarFragment.findViewById(R.id.cart_icon_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_mapsFragment_to_cartFragment);
            }
        });

        transactionSingleton = TransactionSingleton.getInstance();
        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

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
                getActivity().onBackPressed();
            }
        });
        InsertCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_cartFragment_to_homeFragment);
            }
        });
        addressedit = rootView.findViewById(R.id.DiaChiEditID);
        phoneedit = rootView.findViewById(R.id.SoDienThoaiEditID);
        nameedit = rootView.findViewById(R.id.HoVaTenEditID);
        addressedit.setHint(transactionSingleton.getAddress());
        phoneedit.setHint(transactionSingleton.getPhoneNumber());
        nameedit.setHint(transactionSingleton.getNameUser());


        storebtn1 = ((RadioButton)rootView.findViewById(R.id.store1optionid));
        storebtn2 = ((RadioButton)rootView.findViewById(R.id.store2optionid));
        storebtn3 = ((RadioButton)rootView.findViewById(R.id.store3optionid));

        switch (transactionSingleton.getStoreid()){
            case 1:
                storebtn1.setChecked(true);
                break;
            case 2:
                storebtn2.setChecked(true);
                break;
            case 3:
                storebtn3.setChecked(true);
                break;
        }

        rootView.findViewById(R.id.datHangBtnID).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                transactionSingleton.setAddress((addressedit.getText().toString().equals(""))? transactionSingleton.getAddress() : addressedit.getText().toString());
                transactionSingleton.setNameUser(nameedit.getText().toString().equals("")? transactionSingleton.getNameUser(): nameedit.getText().toString());
                transactionSingleton.setPhoneNumber(phoneedit.getText().toString().equals("")?transactionSingleton.getPhoneNumber(): phoneedit.getText().toString());
                if(storebtn1.isChecked()){
                    transactionSingleton.setStoreid(1);
                }
                else if(storebtn2.isChecked()){
                    transactionSingleton.setStoreid(2);
                }
                else if(storebtn3.isChecked()){
                    transactionSingleton.setStoreid(3);
                }
                if(transactionSingleton.getProductElementArrayList().size() == 0){
                    getActivity().onBackPressed();
                    Toast.makeText(getContext(),"The transaction can not be performed...",Toast.LENGTH_SHORT).show();
                    return;
                }
                String transactiondate;
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy' - 'HH:mm:ss",Locale.CHINA);
                LocalDateTime now = LocalDateTime.now();
                transactiondate = dateFormat.format(now);
                TransactionElement transactionElement = new TransactionElement(transactiondate,transactionSingleton.getStoreid(),transactionSingleton.getNameUser(),transactionSingleton.getPhoneNumber(),transactionSingleton.getAddress(),transactionSingleton.getTotalMoneyTransaction());
                transactionViewModel.insertTransactionAndDetail(transactionElement);
//                transactionViewModel.insertTransactionElement(transactionElement);
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(storebtn1.isChecked())
            outState.putInt(userstore_code,1);
        else if(storebtn2.isChecked())
            outState.putInt(userstore_code,2);
        else if(storebtn3.isChecked())
            outState.putInt(userstore_code,3);
        outState.putString(username_code,nameedit.getText().toString());
        outState.putString(useradd_code,addressedit.getText().toString());
        outState.putString(userphone_code,phoneedit.getText().toString());
    }
}
