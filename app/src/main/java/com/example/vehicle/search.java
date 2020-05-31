package com.example.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class search extends AppCompatActivity {
    EditText t;
    LinearLayout ll2;
    Dialog d;
    TextView tt,ts1,ts2,ts3,ts4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        t=findViewById(R.id.s_et);
        ll2=findViewById(R.id.ll2);

        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(search.this, "Hello", Toast.LENGTH_SHORT).show();
                try{
                    ll2.removeAllViews();
                    Log.d("abc","working1");
                    SQLiteDatabase sb=openOrCreateDatabase("vech",MODE_PRIVATE,null);
                    Log.d("abc","working2");
                    String ss="select * from details where make like '"+t.getText().toString()+"%'";
                    Log.d("abc","working3");
                    Cursor c=sb.rawQuery(ss,null);
                    Log.d("abc","working4");
                    //Toast.makeText(search.this, ""+c.getString(1), Toast.LENGTH_SHORT).show();
                    if(c.moveToFirst())
                    {
                        do {
                            TextView tv=new TextView(search.this);
                            String uu=c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3);
                            tv.setText(uu);
                            ll2.addView(tv);
                            registerForContextMenu(tv);
                        }while (c.moveToNext());
                    }
                    else{

                        Toast.makeText(search.this, "Not Available", Toast.LENGTH_SHORT).show();
                    }
                    sb.close();
                }
                catch (Exception e)
                {
                    Toast.makeText(search.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        tt=(TextView)v;

        MenuInflater mi=new MenuInflater(search.this);
        mi.inflate(R.menu.smain,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1)
        {
            d=new Dialog(search.this);
            d.setContentView(R.layout.mydialog);
            Button b=d.findViewById(R.id.db);
            TextView tv=d.findViewById(R.id.dt1);
            TextView tv2=d.findViewById(R.id.dt2);
            TextView tv3=d.findViewById(R.id.dt3);
            TextView tv4=d.findViewById(R.id.dt4);
            String ss=tt.getText().toString();
            String s1[]=ss.trim().split(" ");
            tv.setText(s1[0]);
            tv2.setText(s1[1]);
            tv3.setText(s1[2]);
            tv4.setText(s1[3]);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d.dismiss();
                }
            });
            d.show();
            Window window=d.getWindow();
            window.setLayout(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
        }
        if(item.getItemId()==R.id.m2)
        {
            d=new Dialog(search.this);
            d.setContentView(R.layout.dialoge);
            Button bb=d.findViewById(R.id.d_b);
            ts1=d.findViewById(R.id.d_et1);
            ts2=d.findViewById(R.id.d_et2);
            ts3=d.findViewById(R.id.d_et3);
            ts4=d.findViewById(R.id.d_et4);
            String ss=tt.getText().toString();
            String s1[]=ss.trim().split(" ");
            ts1.setText(s1[0]);
            ts2.setText(s1[1]);
            ts3.setText(s1[2]);
            ts4.setText(s1[3]);
            bb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase sb=openOrCreateDatabase("vech",MODE_PRIVATE,null);
                    String ss=tt.getText().toString();
                    String s1[]=ss.trim().split(" ");
                    String que="update details set make='"+ts1.getText().toString()+"',vno='"+ts2.getText().toString()+"',vmodel='"+ts3.getText().toString()+"',vvariant='"+ts4.getText().toString()+"' where vno='"+s1[1]+"'";
                    sb.execSQL(que);
                    sb.close();
                    d.dismiss();
                }
            });
            d.show();
            Window window=d.getWindow();
            window.setLayout(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
            //Toast.makeText(this, "Prssed edit", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.m3)
        {
            SQLiteDatabase sb=openOrCreateDatabase("vech",MODE_PRIVATE,null);
            String ss=tt.getText().toString();
            String s1[]=ss.trim().split(" ");
            String que="delete from details where make='"+s1[0]+"' and vno='"+s1[1]+"'";
            sb.execSQL(que);
            sb.close();
            ll2.removeView(tt);
            //Toast.makeText(this, "Prssed Delete", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

}
