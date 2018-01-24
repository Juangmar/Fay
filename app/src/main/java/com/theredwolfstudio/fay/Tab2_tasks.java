package com.theredwolfstudio.fay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Koldur on 24/01/2018.
 */

public class Tab2_tasks extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_tasks, container, false);

        return rootView;
    }

    /*

    DbController dbController;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbController = new DbController(this);

        lstTask = findViewById(R.id.taskList);
        loadTaskList();
    }

    private void loadTaskList() {
        ArrayList<String> tasks = dbController.getTasksList();
        if(mAdapter == null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.taskTitle, tasks);
            lstTask.setAdapter(mAdapter);
        } else{
            mAdapter.clear();
            mAdapter.addAll(tasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.taskTitle);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbController.deleteSimpleTask(task);
        loadTaskList();
    }
     */
}
