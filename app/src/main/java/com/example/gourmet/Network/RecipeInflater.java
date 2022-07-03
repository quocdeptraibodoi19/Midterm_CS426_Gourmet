package com.example.gourmet.Network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gourmet.Adapter.RecipeAdapter;
import com.example.gourmet.DataElement.RecipeObj;
import com.example.gourmet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeInflater {
    private final GridView gridView;
    private final WeakReference<ProgressBar> progressBarWeakReference;
    private final WeakReference<TextView> notifyWeakReference;
    public RecipeInflater(GridView gridView, ProgressBar progressBar, TextView notifyTextview){
        this.gridView = gridView;
        progressBarWeakReference = new WeakReference<>(progressBar);
        notifyWeakReference = new WeakReference<>(notifyTextview);
    }

    public void RecipeSearch(String search,RecipeAdapter recipeAdapter){
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1){
                    progressBarWeakReference.get().setVisibility(View.GONE);
                    notifyWeakReference.get().setVisibility(View.VISIBLE);
                    return;
                }
                ArrayList<RecipeObj> recipeObjArrayList = new ArrayList<>();
                recipeObjArrayList = (ArrayList<RecipeObj>) msg.obj;
                if(recipeObjArrayList.size() != 0)
                {
                    Log.d("Quoc", "handleMessage: name"+ recipeObjArrayList.get(0).getRecipeName());
                    recipeAdapter.setRecipeObjArrayList(recipeObjArrayList);
                    progressBarWeakReference.get().setVisibility(View.GONE);
                    gridView.setAdapter(recipeAdapter);
                }
            }
        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://recipesapi2.p.rapidapi.com/recipes/"+new String(search.getBytes(StandardCharsets.UTF_8))+"?maxRecipes=10")
                        .get()
                        .addHeader("X-RapidAPI-Key", "81851a88camsh166337f6f3bcee2p16c514jsn5a6266161150")
                        .addHeader("X-RapidAPI-Host", "recipesapi2.p.rapidapi.com")
                        .build();

                try {
                    Log.d("TrongCount", "run: "+ search);
                    Log.d("Hoai", "run: in flater");
                    Response response = client.newCall(request).execute();
                    JSONArray recipeJsonArray = new JSONObject( Objects.requireNonNull(response.body()).string()).getJSONArray("data");
                    ArrayList<RecipeObj> recipeObjArrayList = new ArrayList<>();
                    Log.d("Hoai", "run: in flater pass 1");
                    for(int i=0; i<recipeJsonArray.length();i++){
                        String recipeName;
                        ArrayList<String> recipeIngredientList = new ArrayList<>();
                        ArrayList<String> instructionList =  new ArrayList<>();
                        String imgUrl;
                        recipeName = recipeJsonArray.getJSONObject(i).getString("name");
                        imgUrl = recipeJsonArray.getJSONObject(i).getString("image");
                        Log.d("QuocJson", "run: "+ recipeJsonArray.getJSONObject(i).toString());
                        for(int k = 0; k< recipeJsonArray.getJSONObject(i).getJSONArray("ingredients").length();k++){
                            recipeIngredientList.add(recipeJsonArray.getJSONObject(i).getJSONArray("ingredients").getString(k));
                        }
                        Log.d("HoaiTrong", "run: the size of ingredient"+ String.valueOf(recipeIngredientList.size()));
                        for(int k = 0; k< recipeJsonArray.getJSONObject(i).getJSONArray("instructions").length();k++){
                            instructionList.add(recipeJsonArray.getJSONObject(i).getJSONArray("instructions").getString(k));
                        }
                        Log.d("HoaiTrong", "run:  size of recipe" + String.valueOf(instructionList.size()));

                        recipeObjArrayList.add(new RecipeObj(recipeName,recipeIngredientList,instructionList,imgUrl));
                    }
                    Message message = new Message();
                    message.obj = recipeObjArrayList;
                    handler.sendMessage(message);
                } catch (IOException | JSONException e) {
                    Log.d("Hoai", "run: fail");
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();
    }
}
