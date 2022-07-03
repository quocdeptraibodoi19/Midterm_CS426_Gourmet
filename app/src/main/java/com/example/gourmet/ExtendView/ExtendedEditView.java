package com.example.gourmet.ExtendView;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.gourmet.Activity_Fragment.ProductListFragment;
import com.example.gourmet.Adapter.ProductListAdapter;
import com.example.gourmet.Adapter.RecipeAdapter;
import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.Network.RecipeInflater;
import com.example.gourmet.ViewModel.ProductViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class ExtendedEditView extends androidx.appcompat.widget.AppCompatEditText {
    private long delays;
    private long last_text_edit;
    private Handler handler;
    private Runnable input_finish_checker;
    private boolean isPrevPopulated;
    public void search(RecipeInflater inflater, RecipeAdapter recipeAdapter, WeakReference<ProgressBar> progressBarWeakReference,boolean isPopulated){
        this.isPrevPopulated = isPopulated;
        delays = 1050;
        last_text_edit = 0;
        handler = new Handler();
        input_finish_checker = new Runnable() {
            public void run() {
                if (System.currentTimeMillis() > (last_text_edit + delays - 500)) {
                    if(inflater != null)
                        inflater.RecipeSearch(Objects.requireNonNull(getText()).toString(),recipeAdapter);
                }
            }
        };
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("hoaitrong", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeCallbacks(input_finish_checker);
                if(!isPrevPopulated)
                    progressBarWeakReference.get().setVisibility(VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 ) {
                    last_text_edit = System.currentTimeMillis();
                    if(!isPrevPopulated)
                    handler.postDelayed(input_finish_checker, delays);
                    isPrevPopulated = false;
                }
            }
        });
    }
    public void searchRecipe(String category, ProductListFragment productListFragment, ProductViewModel productViewModel, ProductListAdapter productListAdapter,WeakReference<ProgressBar> progressBarWeakReference){
        delays = 1050;
        last_text_edit = 0;
        handler = new Handler();
        input_finish_checker = new Runnable() {
            public void run() {
                if (System.currentTimeMillis() > (last_text_edit + delays - 500)) {
                    if(!getText().toString().equals(""))
                    productViewModel.getProductByNameCategory(getText().toString(),category).observe(productListFragment.getViewLifecycleOwner(), new Observer<List<ProductElement>>() {
                        @Override
                        public void onChanged(List<ProductElement> productElements) {
                            progressBarWeakReference.get().setVisibility(GONE);
                            productListAdapter.setProducts(productElements);
                        }
                    });
                    else{
                        productViewModel.getProductList(category).observe(productListFragment.getViewLifecycleOwner(), new Observer<List<ProductElement>>() {
                            @Override
                            public void onChanged(List<ProductElement> productElements) {
                                progressBarWeakReference.get().setVisibility(GONE);
                                productListAdapter.setProducts(productElements);
                            }
                        });
                    }
                }
            }
        };
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("hoaitrong", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeCallbacks(input_finish_checker);
                productListAdapter.setProducts(new ArrayList<>());
                progressBarWeakReference.get().setVisibility(VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                last_text_edit = System.currentTimeMillis();
                handler.postDelayed(input_finish_checker, delays);
            }
        });
    }
    public ExtendedEditView(@NonNull Context context) {
        super(context);
    }

    public ExtendedEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
