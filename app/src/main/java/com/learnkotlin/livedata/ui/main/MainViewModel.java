package com.learnkotlin.livedata.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learnkotlin.livedata.ui.main.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {


    MutableLiveData<List<Todo>> items = new MutableLiveData<>();


    public MainViewModel(){

        RetrofitProvider.retrofitApi.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                items.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.i("TAG", "" + t);
            }
        });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
