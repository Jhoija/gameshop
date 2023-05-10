package com.example.gameshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText et3,et4,et5,et6,et7;
    private String user,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        et7=findViewById(R.id.et7);
        et5=findViewById(R.id.et5);
    }
    public void regresar(View v){
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
        finish();
    }
    public void registrar(View v){
        String saldo = "1000000";
        String name = et6.getText().toString();
        for(int i = 0;i<name.length();i++){
            if(name.charAt(i)==' '){
                et6.setError("Por favor evite usar espacios");
                return;
            }
        }
        String lastname =et7.getText().toString();
        String numero = et3.getText().toString();
        if(numero.length()!=10){
            et3.setError("digite 10 numeros");
            return;
        }else if(!numero.startsWith("3")){
            et3.setError("Debe iniciar por 3");
            return;
        }
        String password = et4.getText().toString();
        String cpassword = et5.getText().toString();
        Boolean activo = false;
        if(name.isEmpty() || numero.isEmpty() || password.isEmpty() || lastname.isEmpty()){
            Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(password.length()<8 || password.length()>15){
                et4.setError("Contraseña debe contener 8 numeros minimo");
                return;
            }
            if(existe()){
                et6.setError("Nombre o Numero no disponible");
            }else if(cpassword.equals(password)){
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin",null,1);
                SQLiteDatabase db = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("name",name);
                registro.put("lastname",lastname);
                registro.put("numero",numero);
                registro.put("password",password);
                registro.put("saldo",saldo);
                registro.put("activo",activo);
                db.insert("user",null,registro);
                db.close();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }else {
                et5.setError("Las contraseñas no coinciden");
            }
        }
    }
    public boolean existe(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admin",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        user = et6.getText().toString();
        num = et3.getText().toString();
        Cursor cursor = db.rawQuery("select name from user where name='"+user+"' or numero='"+ num+"'",null);
        if(cursor.moveToFirst())
            return true;
        return false;
    }
    public String clear(){
        String z="";
        return z ;
    }
}