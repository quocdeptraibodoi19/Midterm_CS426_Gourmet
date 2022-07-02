package com.example.gourmet.Network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.example.gourmet.Adapter.RecipeAdapter;
import com.example.gourmet.DataElement.RecipeObj;
import com.example.gourmet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private final Context layoutContext;
    private final GridView gridView;

    public RecipeInflater(Context context, GridView gridView){
        this.layoutContext = context;
        this.gridView = gridView;
    }

    public void RecipeSearch(String search){
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                ArrayList<RecipeObj> recipeObjArrayList = new ArrayList<>();
                recipeObjArrayList = (ArrayList<RecipeObj>) msg.obj;
                Log.d("Quoc", "handleMessage: name"+ recipeObjArrayList.get(0).getRecipeName());
                RecipeAdapter recipeAdapter = new RecipeAdapter(layoutContext, R.layout.recipecell_layout,recipeObjArrayList);
                gridView.setAdapter(recipeAdapter);
            }
        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://recipesapi2.p.rapidapi.com/recipes/"+"tomato%20soup"+"?maxRecipes=2")
                        .get()
                        .addHeader("X-RapidAPI-Key", "54cba44633msh1cb81c3e7a90a1dp18697ajsn9159f3970b57")
                        .addHeader("X-RapidAPI-Host", "recipesapi2.p.rapidapi.com")
                        .build();

                try {
                    Log.d("Hoai", "run: in flater");
                    Response response = client.newCall(request).execute();
                    JSONArray recipeJsonArray = new JSONObject( Objects.requireNonNull(response.body()).string()).getJSONArray("data");
                    ArrayList<RecipeObj> recipeObjArrayList = new ArrayList<>();
                    Log.d("Hoai", "run: in flater pass 1");
                    for(int i=0; i<recipeJsonArray.length();i++){
                        String recipeName;
                        ArrayList<String> recipeStepList = new ArrayList<>();
                        ArrayList<String> instructionList =  new ArrayList<>();
                        String imgUrl;
                        recipeName = recipeJsonArray.getJSONObject(i).getString("name");
                        imgUrl = recipeJsonArray.getJSONObject(i).getString("image");
                        Log.d("QuocJson", "run: "+ recipeJsonArray.getJSONObject(i).toString());
                        for(int k = 0; k< recipeJsonArray.getJSONObject(i).getJSONArray("ingredients").length();k++){
                            recipeStepList.add(recipeJsonArray.getJSONObject(i).getJSONArray("ingredients").getString(k));
                        }
                        for(int k = 0; k< recipeJsonArray.getJSONObject(i).getJSONArray("instructions").length();k++){
                            recipeStepList.add(recipeJsonArray.getJSONObject(i).getJSONArray("instructions").getString(k));
                        }
                        recipeObjArrayList.add(new RecipeObj(recipeName,recipeStepList,instructionList,imgUrl));
                    }
                    Message message = new Message();
                    message.obj = recipeObjArrayList;
                    handler.sendMessage(message);
                } catch (IOException | JSONException e) {
                    Log.d("Hoai", "run: fail");
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();
    }
}
