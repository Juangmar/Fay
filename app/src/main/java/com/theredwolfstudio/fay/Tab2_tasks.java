package com.theredwolfstudio.fay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.theredwolfstudio.fay.database.DbController;

import java.util.ArrayList;

/**
 * Created by Koldur on 24/01/2018.
 */

public class Tab2_tasks extends Fragment{

    DbController dbController;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;

    View rootView;
    Context context;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2_tasks, container, false);

        context = rootView.getContext();

        dbController = new DbController(context);

        lstTask = rootView.findViewById(R.id.taskList);
        loadTaskList();

        FloatingActionButton add = rootView.findViewById(R.id.addTask);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        return rootView;
    }


    private void loadTaskList() {
        ArrayList<String> tasks = dbController.getTasksList();
        if(mAdapter == null){
            mAdapter = new ArrayAdapter<>(rootView.getContext(), R.layout.row, R.id.taskTitle, tasks);

            lstTask.setAdapter(mAdapter);
        } else{
            mAdapter.clear();
            mAdapter.addAll(tasks);
            mAdapter.notifyDataSetChanged();
        }
    }


    public boolean add() {

                final EditText taskEditText = new EditText(context);
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Add new task")
                        .setMessage("Qué tarea quieres apuntar?")
                        .setView(taskEditText)
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = String.valueOf(taskEditText.getText());
                                dbController.insertSimpleTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create();
                dialog.show();

        return true;
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = parent.findViewById(R.id.taskTitle);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbController.deleteSimpleTask(task);
        loadTaskList();
    }
}
