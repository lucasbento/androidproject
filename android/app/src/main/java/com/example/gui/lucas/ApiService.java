package com.example.gui.lucas;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ApiService {

    static String API_URL = "http://lucas-happilife.rhcloud.com/";

    @POST("/cadastro")
    void cadastrar(@Body User user, Callback<User> callback);



}
