package com.learnkotlin.livedata.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.learnkotlin.livedata.R;
import com.learnkotlin.livedata.databinding.TodoItemBinding;
import com.learnkotlin.livedata.ui.main.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    private MutableLiveData<List<Todo>> todos;
    private AppCompatActivity activity;
    private OnItemClickListener listener;

    public TodoAdapter(AppCompatActivity activity, MutableLiveData<List<Todo>> todos, OnItemClickListener listener){

        this.listener = listener;
        this.activity = activity;
        this.todos = todos;
        init();

    }

    private void init(){
        todos.observe(activity, todos -> notifyDataSetChanged());
    }


    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TodoItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, null));
        binding.getRoot().setOnClickListener(v -> {
            if(listener != null){
                listener.onItemClick(binding.getTodo());
            }
        });
        return new TodoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        holder.bind(todos.getValue().get(position));
    }

    @Override
    public int getItemCount() {
        return todos.getValue() != null ? todos.getValue().size() : 0;
    }

    class TodoHolder extends RecyclerView.ViewHolder {

        private TodoItemBinding binding = null;

        public TodoHolder(TodoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Todo todo){
            binding.setTodo(todo);
        }

    }
}
