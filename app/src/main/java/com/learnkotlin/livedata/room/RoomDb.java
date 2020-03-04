package com.learnkotlin.livedata.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.learnkotlin.livedata.ui.main.model.Todo;
import com.learnkotlin.livedata.ui.main.model.TodoDao;

@Database(entities = {Todo.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {

    public abstract TodoDao todoDao();

}
