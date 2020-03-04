package com.learnkotlin.livedata.ui.main.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "todo")
public class Todo implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    int id;

    @ColumnInfo(name = "user_id")
    @SerializedName("userId")
    int userId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    public String title;

    @ColumnInfo(name = "completed")
    @SerializedName("completed")
    boolean completed;

}
