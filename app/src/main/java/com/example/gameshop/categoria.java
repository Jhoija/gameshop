package com.example.gameshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class categoria extends AppCompatActivity {
    private String name2;
    private ConstraintLayout confirmarcompra;
    private TableLayout lista;
    TextView tv51,tv6,hola,nombrejuego,nombrejuego1,nombrejuego2,tvjuego,tvprecio;
    private ImageView iv1,iv2,iv3,ivjuego;
    private int categoria,saldo,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        hola = findViewById(R.id.textView45);
        tv51=findViewById(R.id.tv51);
        tv6=findViewById(R.id.textView6);
        tvjuego=findViewById(R.id.tvjuego);
        tvprecio=findViewById(R.id.tvprecio);

        Bundle bundle = getIntent().getExtras();
        name2 = bundle.getString("name");
        categoria = bundle.getInt("cate");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("select saldo from user where name='" + name2 + "'", null);
        if (cursor.moveToFirst()) {
            tv51.setText(cursor.getString(0));
        }
        hola.setText(name2);

        iv1= findViewById(R.id.imageView6);
        iv2=findViewById(R.id.imageView7);
        iv3=findViewById(R.id.imageView8);
        nombrejuego=findViewById(R.id.nombrejuego);
        nombrejuego1=findViewById(R.id.nombrejuego1);
        nombrejuego2=findViewById(R.id.nombrejuego2);
        confirmarcompra = findViewById(R.id.confirmarcompra);
        switch(categoria){
            case 1 :
                iv1.setImageResource(R.drawable.days);
                nombrejuego.setText("7 Days to die");
                iv2.setImageResource(R.drawable.theforest);
                nombrejuego1.setText("Sons of The Forest");
                iv3.setImageResource(R.drawable.scum);
                nombrejuego2.setText("Scum");
                tv6.setText("Aventura");
                return;
            case 2:
                iv1.setImageResource(R.drawable.destiny);
                nombrejuego.setText("Destiny 2");
                iv2.setImageResource(R.drawable.gtav);
                nombrejuego1.setText("Grand theft Auto V ");
                iv3.setImageResource(R.drawable.resident);
                nombrejuego2.setText("Resident evil 4");
                tv6.setText("Accion");
                return;
            case 3:
                iv1.setImageResource(R.drawable.fifa);
                nombrejuego.setText("Fifa22");
                iv2.setImageResource(R.drawable.beat);
                nombrejuego1.setText("Beat Saber");
                iv3.setImageResource(R.drawable.forza);
                nombrejuego2.setText("Forza Horizon 5");
                tv6.setText("Deportes");
        }
    }
    public void venta1(View v){
        confirmarcompra.setVisibility(View.VISIBLE);
        nombrejuego.setVisibility(View.GONE);
        nombrejuego1.setVisibility(View.GONE);
        nombrejuego2.setVisibility(View.GONE);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);
        String name = nombrejuego.getText().toString();
        tvjuego.setText(name);
        int num = (int)(Math.random()*13)*10000;
        String pre=num+"";
        tvprecio.setText(pre);
    }
    public void venta2(View v){
        confirmarcompra.setVisibility(View.VISIBLE);
        nombrejuego.setVisibility(View.GONE);
        nombrejuego1.setVisibility(View.GONE);
        nombrejuego2.setVisibility(View.GONE);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);
        String name = nombrejuego1.getText().toString();
        tvjuego.setText(name);
        int num = (int)(Math.random()*13)*10000;
        String pre=num+"";
        tvprecio.setText(pre);
    }
    public void venta3(View v){
        confirmarcompra.setVisibility(View.VISIBLE);
        nombrejuego.setVisibility(View.GONE);
        nombrejuego1.setVisibility(View.GONE);
        nombrejuego2.setVisibility(View.GONE);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);
        String name = nombrejuego2.getText().toString();
        tvjuego.setText(name);
        int num = (int)(Math.random()*13)*10000;
        String pre=num+"";
        tvprecio.setText(pre);
    }
    public void cancelc(View v){
        confirmarcompra.setVisibility(View.GONE);
        nombrejuego.setVisibility(View.VISIBLE);
        nombrejuego1.setVisibility(View.VISIBLE);
        nombrejuego2.setVisibility(View.VISIBLE);
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.VISIBLE);
        iv3.setVisibility(View.VISIBLE);
    }
    public void comprar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String name1 = hola.getText().toString();
        int aux= Integer.parseInt(tv51.getText().toString());
        int aux1 = Integer.parseInt(tvprecio.getText().toString());
        if(aux>=aux1){
            ContentValues registro = new ContentValues();
            int saldo=Integer.parseInt(tv51.getText().toString());
            int x = saldo-aux1;
            tv51.setText(x+"");
            String namegame = tvjuego.getText().toString();
            registro.put("saldo", x);
            String precio = tv51.getText().toString();
            db.update("user", registro, "name='"+name1+"'", null);
            Toast.makeText(this, "Compra exitosa", Toast.LENGTH_SHORT).show();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
            String datetimeString = sdf.format(new Date());
            ContentValues registro1 = new ContentValues();
            registro1.put("fecha",datetimeString);
            registro1.put("namegame",namegame);
            registro1.put("saldo",x);
            registro1.put("precio",precio);
            registro1.put("nameuser",name1);
            db.insert("history",null,registro1);
            db.close();
        }else{
            confirmarcompra.setVisibility(View.GONE);
            Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
        }
        confirmarcompra.setVisibility(View.GONE);
        nombrejuego.setVisibility(View.VISIBLE);
        nombrejuego1.setVisibility(View.VISIBLE);
        nombrejuego2.setVisibility(View.VISIBLE);
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.VISIBLE);
        iv3.setVisibility(View.VISIBLE);
    }
}