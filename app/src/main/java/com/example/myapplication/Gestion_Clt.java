package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Gestion_Clt extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    public ImageButton addClient;
    public ImageButton delClient;
    public ImageButton listClient;
    public ImageButton previous_gestion;
    public Spinner spin;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_client);
        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Gestion_Clt.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        addListenerOnAddButton();
        addListenerOnDelButton();
        addListenerOnListButton();
        addListenerOnPrevious();
    }
    public void addListenerOnAddButton(){
        addClient=findViewById(R.id.ajout);
        addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Intent intent=new Intent(context,Add_Clt.class);
                context.startActivity(intent);
            }
        });
    }
    public void addListenerOnDelButton(){
        delClient=findViewById(R.id.del);
        delClient.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(Gestion_Clt.this,Delete_Clt.class);
                Gestion_Clt.this.startActivity(i);
            }
        });
    }

    public void addListenerOnListButton(){
        listClient=findViewById(R.id.list_clt);
        listClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Gestion_Clt.this,Liste.class);
                Gestion_Clt.this.startActivity(i);
            }
        });
    }
    public void addListenerOnPrevious(){
        previous_gestion=findViewById(R.id.previous_gestion);
        previous_gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Gestion_Clt.this,My_Dashboard.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                break;
            case 1:
                Intent intent = new Intent(Gestion_Clt.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Gestion_Clt.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}
