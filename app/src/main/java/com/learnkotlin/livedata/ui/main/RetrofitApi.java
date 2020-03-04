package com.learnkotlin.livedata.ui.main;

import com.learnkotlin.livedata.ui.main.model.Todo;
import com.learnkotlin.livedata.ui.main.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("todos")
    Call<List<Todo>> getTodos();

    @GET("user/{userId}")
    Call<User> getUser(@Path("userId") String userId);

}
