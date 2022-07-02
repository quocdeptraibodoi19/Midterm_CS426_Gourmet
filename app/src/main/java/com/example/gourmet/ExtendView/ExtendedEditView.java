package com.example.gourmet.ExtendView;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gourmet.Adapter.RecipeAdapter;
import com.example.gourmet.Network.RecipeInflater;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class ExtendedEditView extends androidx.appcompat.widget.AppCompatEditText {
    private long delays;
    private long last_text_edit;
    private Handler handler;
    private Runnable input_finish_checker;

    public void search(RecipeInflater inflater, RecipeAdapter recipeAdapter, WeakReference<ProgressBar> progressBarWeakReference){
        delays = 1050;
        last_text_edit = 0;
        handler = new Handler();
        input_finish_checker = new Runnable() {
            public void run() {
                if (System.currentTimeMillis() > (last_text_edit + delays - 500)) {
                    // TODO: do what you need here
                    // ............
                    // ............
                    if(inflater != null)
                        inflater.RecipeSearch(Objects.requireNonNull(getText()).toString(),recipeAdapter);
                }
            }
        };
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeCallbacks(input_finish_checker);
                progressBarWeakReference.get().setVisibility(VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(input_finish_checker, delays);
                }
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