package com.learnkotlin.livedata.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.learnkotlin.livedata.R;
import com.learnkotlin.livedata.ui.main.model.Todo;
import com.learnkotlin.livedata.ui.tododetail.TodoDetailFragment;

public class TodoDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_detail_activity);
        if (savedInstanceState == null) {
            Todo todo = (Todo) getIntent().getSerializableExtra("todo");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TodoDetailFragment.newInstance(todo))
                    .commitNow();
        }
    }
}
