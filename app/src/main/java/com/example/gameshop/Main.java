package com.example.gameshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {
    private TextView tv3,tv4,tv1;
    private String name,saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("select saldo from user where name='" + name + "'", null);
        tv3 = findViewById(R.id.tv3);
        tv1 = findViewById(R.id.tv5);
        if (cursor.moveToFirst()) {
            saldo = cursor.getString(0);
            tv1.setText(saldo);
        }
        tv4 = findViewById(R.id.tv1);
        tv4.setText(name);
    }
    @Override
    public void onBackPressed(){
        alert1();
    }
    @Override
    protected void onResume(){
        super.onResume();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("select saldo from user where name='" + name + "'", null);
        tv3 = findViewById(R.id.tv3);
        tv1 = findViewById(R.id.tv5);
        if (cursor.moveToFirst()) {
            saldo = cursor.getString(0);
            tv1.setText(saldo);
        }
    }
    public void accion(View v){
        Intent i = new Intent(this,categoria.class);
        i.putExtra("name",name);
        i.putExtra("cate",1);
        startActivity(i);
    }
    public void aventura(View v){
        Intent i = new Intent(this,categoria.class);
        i.putExtra("name",name);
        i.putExtra("cate",2);
        startActivity(i);
    }
    public void deporte(View v){
        Intent i = new Intent(this,categoria.class);
        i.putExtra("name",name);
        i.putExtra("cate",3);
        startActivity(i);
    }
    public void alert1(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Seguro, desea salir ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                finish();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }
    public void perfil(View v){
        Intent i = new Intent(this,perfil.class);
        i.putExtra("name",name);
        i.putExtra("saldo",saldo);
        startActivity(i);
    }

}
