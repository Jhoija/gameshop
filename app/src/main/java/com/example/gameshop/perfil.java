package com.example.gameshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class perfil extends AppCompatActivity{
    private TextView tv,tv1;
    List<claseconstructor> elementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tv=findViewById(R.id.textView4);
        tv1 = findViewById(R.id.textView7);

        init();
    }
    public void init(){
        elementos = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String saldo = bundle.getString("saldo");
        tv1.setText(saldo);
        tv.setText(name);
        Cursor fila = db.rawQuery("select nameuser,namegame,saldo,precio from history where nameuser='"+name+"'", null);
        for (int i = 0;i<fila.getCount();){
            if(fila.moveToNext()){
                elementos.add(new claseconstructor(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3)));
            }
            i++;
        }
        ListAdapter listAdapter = new ListAdapter(elementos,this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
    public void cerrar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String name = tv.getText().toString();
        registro.put("activo",false);
        int x= db.update("user",registro,"name='"+name+"'",null);
        Intent i = new Intent(this,MainActivity.class);
        db.close();
        startActivity(i);
        finish();
    }
}