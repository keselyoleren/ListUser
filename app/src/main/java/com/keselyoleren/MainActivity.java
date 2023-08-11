package com.keselyoleren;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.keselyoleren.adapter.DataAdapter;
import com.keselyoleren.api.JSONResponse;
import com.keselyoleren.api.RequestInterface;
import com.keselyoleren.model.ListUsers;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ListUsers> data;
    private DataAdapter adapter;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJson();

    }


    public void loadJson(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://merck.neurabot.ai")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonReader = response.body();
                data = new ArrayList<>(Arrays.asList(jsonReader.getUsers()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

}