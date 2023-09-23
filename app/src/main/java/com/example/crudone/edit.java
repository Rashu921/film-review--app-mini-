package com.example.crudone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button b1,b2,b3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.des);
        ed3 = findViewById(R.id.cast);
        ed4 = findViewById(R.id.id);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);


        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("des").toString();
        String t4 = i.getStringExtra("cas").toString();

        ed4.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t4);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);

            }
        });




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

    }

    public void Delete()
    {
        try
        {
            String id = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);


            String sql = "delete from record where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit()
    {
        try
        {
            String name = ed1.getText().toString();
            String des = ed2.getText().toString();
            String cas = ed3.getText().toString();
            String id = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);


            String sql = "update record set name = ?,des=?,cas=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,des);
            statement.bindString(3,cas);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Record Updateddd",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }


}