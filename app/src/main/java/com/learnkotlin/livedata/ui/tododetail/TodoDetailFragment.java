package com.learnkotlin.livedata.ui.tododetail;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learnkotlin.livedata.R;
import com.learnkotlin.livedata.ui.main.model.Todo;

public class TodoDetailFragment extends Fragment {

    private TodoDetailViewModel mViewModel;
    private Todo todo;

    public static TodoDetailFragment newInstance(Todo todo) {
        TodoDetailFragment fragment = new TodoDetailFragment();
        fragment.todo = todo;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.todo_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider.NewInstanceFactory().create(TodoDetailViewModel.class);
    }

}
