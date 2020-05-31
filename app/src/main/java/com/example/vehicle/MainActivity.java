package com.example.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.i1)
        {
            Intent in= new Intent(MainActivity.this,add.class);
            startActivity(in);
            SQLiteDatabase sb=openOrCreateDatabase("vech",MODE_PRIVATE,null);


            //Toast.makeText(this, "pressed add contact", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.i2)
        {Intent in= new Intent(MainActivity.this,search.class);
            startActivity(in);
            // Toast.makeText(this, "pressed search contact", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);


    }
}
