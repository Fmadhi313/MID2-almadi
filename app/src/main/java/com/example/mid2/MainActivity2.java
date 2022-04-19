package com.example.mid2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new DatabaseHelper(MainActivity2.this);
        Button add = (Button) findViewById(R.id.add);
        Button first = (Button) findViewById(R.id.view);
        Button third = (Button) findViewById(R.id.delete);
        EditText ID = (EditText) findViewById(R.id.EID);
        EditText Name = (EditText) findViewById(R.id.Ename);
        EditText lname = (EditText) findViewById(R.id.Esalary);
        EditText nID = (EditText) findViewById(R.id.esalary);




        add.setBackgroundColor(Color.rgb(0, 128, 0));
        first.setBackgroundColor(Color.rgb(255,140,0));
        third.setBackgroundColor(Color.rgb(255,69,0));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDn = ID.getText().toString();
                String Name1 = Name.getText().toString();
                String lastname = lname.getText().toString();
                String nationalid = nID.getText().toString();


                if (IDn.equals("") || Name1.equals("") || lastname.equals("") || nationalid.equals(""))
                    Toasty.error(getBaseContext(), "make sure all fealds are filed", Toast.LENGTH_SHORT, true).show();

                else {
                    if (!myDb.addData(IDn, Name1, lastname, nationalid)) {
                        Toasty.error(getBaseContext(), "Insert Failed", Toast.LENGTH_SHORT, true).show();


                    } else {
                        Toasty.success(getBaseContext(), "Insert Successful", Toast.LENGTH_SHORT, true).show();
                    }

                }
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));

            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });

    }

}