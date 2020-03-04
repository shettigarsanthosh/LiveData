package com.learnkotlin.livedata.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learnkotlin.livedata.ContentDelivery;
import com.learnkotlin.livedata.R;
import com.learnkotlin.livedata.room.RoomDb;
import com.learnkotlin.livedata.ui.TodoDetail;
import com.learnkotlin.livedata.ui.main.model.Todo;
import com.learnkotlin.livedata.ui.tododetail.TodoDetailFragment;

import java.io.Serializable;
import java.util.List;

public class MainFragment extends Fragment implements OnItemClickListener, Serializable {

    public static String TAG = "MainFragment";

    private MainViewModel mViewModel;

    public static final Uri CONTENT_URI = Uri.parse("content://" + ContentDelivery.TAG +
            "/" + ContentDelivery.TODO);


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RoomDb db = Room.databaseBuilder(getActivity().getApplicationContext(), RoomDb.class, "RoomDb").build();

        mViewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new TodoAdapter((AppCompatActivity) getActivity(), mViewModel.items, this::onItemClick));

        mViewModel.items.observe(getActivity(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {

                new Thread(){

                    @Override
                    public void run() {
                        db.todoDao().insert(todos);
                        List<Todo> todoList = db.todoDao().todos();
                        Log.i(TAG, "" + db.todoDao().selectedTodos());

                        ContentResolver resolver = getContext().getContentResolver();
                        String[] projection = {"id", "user_id", "title"};

                        Cursor cursor = resolver.query(CONTENT_URI , projection, null, null, null);
                        cursor.moveToFirst();

                        do {

                            // Read the values of a row in the table using the indexes acquired above
                            final long id = cursor.getInt(cursor.getColumnIndex("id"));
                            final String name = cursor.getString(cursor.getColumnIndex("title"));

                            Log.i(TAG, " Name is " + name);

                        } while (cursor.moveToNext());
                    }
                }.start();

            }
        });
    }

    @Override
    public void onItemClick(Todo todo) {

        Intent intent = new Intent(getContext(), TodoDetail.class);
        intent.putExtra("todo", todo);
        startActivity(intent);


//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, TodoDetailFragment.newInstance(todo)).addToBackStack("todo").commit();

    }
}
