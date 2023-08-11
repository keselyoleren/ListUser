package com.keselyoleren.api;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("api/list-user/?format=json")
    Call<JSONResponse> getJSON();

}
