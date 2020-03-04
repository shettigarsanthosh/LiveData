package com.learnkotlin.livedata.ui.main.model;

import android.database.Cursor;
import android.icu.text.Replaceable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Todo... todos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Todo> todos);

    @Delete
    void delete(Todo todo);

    @Query("Select * from todo WHERE title LIKE '%et%' LIMIT 2")
    List<Todo> selectedTodos();


    @Query("Select * from todo WHERE id LIKE :id LIMIT 1")
    Todo todo(int id);

    @Query("Select * from todo WHERE id LIKE :id LIMIT 1")
    Cursor todoCursor(int id);

    @Query("Select * from todo")
    List<Todo> todos();

}
