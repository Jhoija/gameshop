package com.example.gameshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;

import java.io.IOException;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2;
    private CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        cb=findViewById(R.id.cb);
        getSupportActionBar().hide();
    }
/*    @Override
    protected void onStart(){
        super.onStart();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select activo,name from user where activo="+true, null);
        if (fila!=null) {
            if(fila.moveToFirst()){
                Intent i = new Intent(this,Main.class);
                i.putExtra("name",fila.getString(1));
                db.close();
                startActivity(i);
            }
        }
    }*/
    public void ingresar(View view){
        String numero= et1.getText().toString();
        String contraseña=et2.getText().toString();
        if(numero.isEmpty()){
            et1.setError("Por favor ingrese su Numero");
        }else if(contraseña.isEmpty()){
            et2.setError("Por favor Ingrese su Contraseña");
        }else {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admin", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor fila = db.rawQuery("select name,saldo from user where numero='" + numero + "' and password='" + contraseña + "'", null);
            if (fila.moveToFirst()) {
                if(cb.isChecked()==true){
                    ContentValues registro = new ContentValues();
                    registro.put("activo",true);
                    db.update("user",registro,"numero='"+numero+"'",null);
                }
                Intent i = new Intent(this, Main.class);
                i.putExtra("name",fila.getString(0));
                startActivity(i);
                finish();
            } else {
                et2.setError("Contraseña o Usuario incorrecto");
            }
            db.close();
        }
    }
    public void registrarse(View v){
        Intent i = new Intent(this, Register.class );
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed(){
        alert1();
    }
    public void alert1(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Seguro, desea salir de la aplicacion ?");
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
    /*public static Toast makeText(Context context, CharSequence text, @BaseTransientBottomBar.Duration int duration) {
        return makeText(context, null, text, duration);
    }*/
}