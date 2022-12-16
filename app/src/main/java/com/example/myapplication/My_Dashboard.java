package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class My_Dashboard extends android.app.Activity{
    public ImageButton gestionClient;
    public ImageButton gestionVoiture;
    public DatabaseHandler db=new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dashboard);
        addListenerOnImageButton();
        addListenerOnCarButton();
    }
    public void addListenerOnImageButton(){
        gestionClient=findViewById(R.id.gestionClient);
        gestionClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(My_Dashboard.this,Gestion_Clt.class);
                startActivity(intent);
            }
        });
    }
    public void addListenerOnCarButton(){
        gestionVoiture=findViewById(R.id.gest_voit);
        gestionVoiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(My_Dashboard.this,Gestion_Voiture.class);
                startActivity(intent);
            }
        });
    }
}