package com.example.gameshop;

public class claseconstructor {
    private String namegame,fecha,nameuser;
    private String precio,saldo;

    public claseconstructor(String nameuser, String namegame, String fecha, String precio) {
        this.nameuser=nameuser;
        this.namegame=namegame;
        this.fecha=fecha;
        this.precio=precio;
        this.saldo=saldo;
    }

    public String getNamegame() {
        return namegame;
    }
    public void setNamegame(String namegame) {
        this.namegame = namegame;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNameuser() {
        return nameuser;
    }
    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}