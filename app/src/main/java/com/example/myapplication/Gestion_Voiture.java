package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class Gestion_Voiture extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    private ImageButton add_v;
    private ImageButton delete_v;
    private ImageButton list_v;
    private ImageButton previous_gestion_v;
    private Spinner spin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_voitures);
        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Gestion_Voiture.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        addOnAjoutListener();
        addOnDelListener();
        addOnListListener();
        addListenerOnprevious();
    }
    public void addOnAjoutListener(){
        add_v=findViewById(R.id.ajout_v);
        add_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Gestion_Voiture.this,Add_Voiture.class);
                startActivity(i);
            }
        });
    }
    public void addOnDelListener(){
        delete_v=findViewById(R.id.del_v);
        delete_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(Gestion_Voiture.this,Delete_Voiture.class);
                startActivity(j);
            }
        });
    }
    public void addOnListListener(){
        list_v=findViewById(R.id.list_v);
        list_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(Gestion_Voiture.this,Liste_V.class);
                startActivity(a);
            }
        });
    }
    public void addListenerOnprevious(){
        previous_gestion_v=findViewById(R.id.previous_gestion_v);
        previous_gestion_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Gestion_Voiture.this,My_Dashboard.class);
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
                Intent intent = new Intent(Gestion_Voiture.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Gestion_Voiture.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}

