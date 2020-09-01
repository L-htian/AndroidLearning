package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;
import com.byted.camp.todolist.operation.activity.DatabaseActivity;
import com.byted.camp.todolist.operation.activity.DebugActivity;
import com.byted.camp.todolist.operation.activity.SettingActivity;
import com.byted.camp.todolist.ui.NoteListAdapter;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 1002;

    private RecyclerView recyclerView;
    private NoteListAdapter notesAdapter;

    private TodoDbHelper todoDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, NoteActivity.class),
                        REQUEST_CODE_ADD);
            }
        });

        recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(new NoteOperator() {
            @Override
            public void deleteNote(Note note) {
                MainActivity.this.deleteNote(note);
                notesAdapter.refresh(loadNotesFromDatabase());
            }

            @Override
            public void updateNote(Note note) {
                MainActivity.this.updateNode(note);
                notesAdapter.refresh(loadNotesFromDatabase());
            }
        });
        todoDbHelper = new TodoDbHelper(this);
        recyclerView.setAdapter(notesAdapter);
        notesAdapter.refresh(loadNotesFromDatabase());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoDbHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.action_debug:
                startActivity(new Intent(this, DebugActivity.class));
                return true;
            case R.id.action_database:
                startActivity(new Intent(this, DatabaseActivity.class));
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD
                && resultCode == Activity.RESULT_OK) {
            notesAdapter.refresh(loadNotesFromDatabase());
        }
    }

    private List<Note> loadNotesFromDatabase() {
        // TODO 从数据库中查询数据，并转换成 JavaBeans
        SQLiteDatabase database = todoDbHelper.getReadableDatabase();
        if (database == null) return Collections.EMPTY_LIST;
        List<Note> re = new LinkedList<>();
        Cursor cursor = database.query(TodoContract.TodoEntry.TABLE_NAME, null,
                null, null,
                null, null,
                TodoContract.TodoEntry.TABLE_STATE );
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(TodoContract.TodoEntry._ID));
            long date = cursor.getLong(cursor.getColumnIndex(TodoContract.TodoEntry.TABLE_DATE));
            String content = cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry.TABLE_CONTENT));
            int state = cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.TABLE_STATE));
//            int priority = cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.TABLE_PRIORITY));
            Note note = new Note(id);
            note.setContent(content);
            note.setDate(new Date(date));
            note.setState(State.from(state));
            re.add(note);
        }
        cursor.close();
        return re;
    }

    private void deleteNote(Note note) {
        // TODO 删除数据
        SQLiteDatabase database = todoDbHelper.getWritableDatabase();
        if (database == null) return;
        String selection = TodoContract.TodoEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(note.id)};
        int deleteRows = database.delete(TodoContract.TodoEntry.TABLE_NAME, selection, selectionArgs);

    }

    private void updateNode(Note note) {
        // TODO 更新数据
        SQLiteDatabase database = todoDbHelper.getWritableDatabase();
        if (database == null) return;
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoContract.TodoEntry.TABLE_STATE, note.getState().intValue);
        String selection = TodoContract.TodoEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(note.id)};
        int updateRows = database.update(TodoContract.TodoEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }

}
