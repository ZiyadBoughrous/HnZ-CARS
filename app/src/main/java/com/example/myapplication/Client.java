package com.example.myapplication;

public class Client {
    int id_clt;
    String nom_clt;
    String prenom_clt;
    String cin;
    String phone;
    String adress;
    String date_debut;
    String date_fin;
    String hor_debut;
    String hor_fin;



    public Client(){};
    public Client(int id_clt, String nom_clt, String prenom_clt, String cin, String phone, String adress, String date_debut, String date_fin, String hor_debut, String hor_fin) {
        this.id_clt = id_clt;
        this.nom_clt = nom_clt;
        this.prenom_clt = prenom_clt;
        this.cin = cin;
        this.phone = phone;
        this.adress = adress;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.hor_debut=hor_debut;
        this.hor_fin=hor_fin;

    }

    public int getID() {
        return this.id_clt;
    }

    public void setID(int id){
        this.id_clt=id;
    }
    public String getNom(){
        return this.nom_clt;
    }
    public void setNom(String nom){
        this.nom_clt=nom;
    }
    public String getPrenom(){
        return this.prenom_clt;
    }
    public void setPrenom(String prenom){
        this.prenom_clt=prenom;
    }
    public String getCin(){
        return this.cin;
    }
    public void setCin(String cin){
        this.cin=cin;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getAdress(){
        return this.adress;
    }
    public void setAdress(String adress){
        this.adress=adress;
    }
    public String getDate_debut(){
        return date_debut;
    }
    public void setDate_debut(String date_debut){
        this.date_debut=date_debut;
    }
    public String getDate_fin(){
        return date_fin;
    }
    public void setDate_fin(String date_fin){
        this.date_fin=date_fin;
    }
    public String getHor_debut() {
        return hor_debut;
    }
    public void setHor_debut(String hor_debut) {
        this.hor_debut = hor_debut;
    }
    public String getHor_fin(){
        return hor_fin;
    }
    public void setHor_fin(String hor_fin){
        this.hor_fin=hor_fin;
    }

}
