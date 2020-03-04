package com.learnkotlin.livedata.ui.main.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    int id ;

    @SerializedName("name")
    String name;

    @SerializedName("username")
    String username;

    @SerializedName("email")
    String email;

}
