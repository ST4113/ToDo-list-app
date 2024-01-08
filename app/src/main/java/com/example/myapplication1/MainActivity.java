package com.example.myapplication1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;
import com.example.myapplication1.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private ArrayList<Task> tasks;
    private ArrayAdapter<Task> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listViewTasks.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            Task task = tasks.get(position);
            task.setDone(!task.isDone());
            adapter.notifyDataSetChanged();
        });

        listViewTasks.setOnItemLongClickListener((parent, view, position, id) -> {
            editOrDeleteTask(position);
            return true;
        });
    }

    private void addTask() {
        String taskDescription = editTextTask.getText().toString().trim();
        if (!taskDescription.isEmpty()) {
            Task newTask = new Task(taskDescription);
            tasks.add(newTask);
            adapter.notifyDataSetChanged();
            editTextTask.getText().clear();
        }
    }


    private void editOrDeleteTask(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit or Delete Task");
        builder.setItems(new CharSequence[]{"Edit", "Delete"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    editTask(position);
                    break;
                case 1:
                    deleteTask(position);
                    break;
            }
        });
        builder.create().show();
    }

    private void editTask(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText editText = new EditText(this);
        editText.setText(tasks.get(position).getDescription());
        builder.setView(editText);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newDescription = editText.getText().toString().trim();
            tasks.get(position).setDescription(newDescription);
            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
        });

        builder.create().show();
    }

    private void deleteTask(final int position) {
        tasks.remove(position);
        adapter.notifyDataSetChanged();
    }

}
