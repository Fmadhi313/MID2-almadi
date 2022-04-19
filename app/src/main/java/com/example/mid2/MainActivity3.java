package com.example.mid2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myDb = new DatabaseHelper(MainActivity3.this);
        Button view = (Button) findViewById(R.id.viewdata);
        Button delete = (Button) findViewById(R.id.deletedata);
        EditText ID = (EditText) findViewById(R.id.idtodelete);
        TextView fulldata = (TextView) findViewById(R.id.alldata);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDb.ViewEmployee();
                StringBuffer buffer = new StringBuffer();

                while(cur.moveToNext()){
                    buffer.append("id: "+cur.getString(0)+"\n");
                    buffer.append("Name: "+cur.getString(1)+"\n");
                    buffer.append("Salary: "+cur.getString(2)+"\n\n");
                }

                fulldata.setText(buffer.toString());



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delID = ID.getText().toString();
                if (ID.getText().toString().equals("")){
                    Toasty.error(getBaseContext(), "Insert the ID", Toast.LENGTH_SHORT, true).show();

                }
                else {
                    myDb.deleteData(delID);
                    Toasty.success(getBaseContext(), "Delete Successful", Toast.LENGTH_SHORT, true).show();

                }
            }
        });
    }
}