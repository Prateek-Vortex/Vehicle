package com.example.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class add extends AppCompatActivity {
    Button b;
    EditText t1,t2,t3,t4;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        b=findViewById(R.id.ab);
        t1=findViewById(R.id.et1);
        t2=findViewById(R.id.et2);
        t3=findViewById(R.id.et3);
        t4=findViewById(R.id.et4);
        ll=findViewById(R.id.ll);

        SQLiteDatabase sb=openOrCreateDatabase("vech",MODE_PRIVATE,null);
        sb.execSQL("create table if not exists details(make varchar(30),vno varchar(30),vmodel varchar(30),vvariant varchar(30))");
        sb.execSQL("insert into details values('','','','')");
        String ss="select * from details";
        Cursor c=sb.rawQuery(ss,null);
        if(c.moveToFirst())
        {
            do{
                String t=c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3);
                TextView tv=new TextView(add.this);
                tv.setText(t);
                ll.addView(tv);
            }while(c.moveToNext());
            sb.close();
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sb=openOrCreateDatabase("vech",MODE_PRIVATE,null);
                //sb.execSQL("create table if not exists details(make varchar(30),vno varchar(30),)");
                sb.execSQL("create table if not exists details(make varchar(30),vno varchar(30),vmodel varchar(30),vvariant varchar(30))");
                sb.execSQL("insert into details values('"+t1.getText().toString()+"','"+t2.getText().toString()+"','"+t3.getText().toString()+"','"+t4.getText().toString()+"') ");
                Toast.makeText(add.this, "Contact saved", Toast.LENGTH_SHORT).show();
                String f=t1.getText().toString()+" "+t2.getText().toString()+" "+t3.getText().toString()+" "+t4.getText().toString();
                TextView tt=new TextView(add.this);
                tt.setText(f);
                ll.addView(tt);

            }
        });


    }
}
