package com.example.crudone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class view extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    Button logout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        logout = findViewById(R.id.btnlogout);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from record", null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int des = c.getColumnIndex("des");
        int cas = c.getColumnIndex("cas");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<movie> stud = new ArrayList<movie>();


        if (c.moveToFirst()) {
            do {
                movie stu = new movie();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.des = c.getString(des);
                stu.cas = c.getString(cas);
                stud.add(stu);

                titles.add(c.getString(id) + " \n " + c.getString(name) + " \n " + c.getString(des) + " \n " + c.getString(cas));

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();


        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                movie stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(), edit.class);
                i.putExtra("id", stu.id);
                i.putExtra("name", stu.name);
                i.putExtra("des", stu.des);
                i.putExtra("cas", stu.cas);
                startActivity(i);

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.this, LoginActivity.class));

            }
        });


    }
 } 
