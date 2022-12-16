package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Delete_Voiture extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    public ImageButton delete;
    public AutoCompleteTextView search_id;
    public AutoCompleteTextView search_marque;
    public ImageButton previous_suppression_v;
    public Spinner spin;
    public static ArrayList<Voiture> list=new ArrayList<>(Arrays.asList(new Voiture()));

    private DatabaseHandler db=new DatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suppression_voiture);

        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Delete_Voiture.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        search_id=findViewById(R.id.searchByIdV);
        search_marque=findViewById(R.id.searchByMarque);
        delete=findViewById(R.id.del_voiture);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Integer n=Integer.parseInt(search_id.getText().toString());
                    Voiture voiture_id=db.getVoitureByIdV(n);
                    db.del_Voiture(voiture_id);
                }catch(Exception e){e.printStackTrace();}

                try{
                    String vmarque= search_marque.getText().toString();
                    Voiture voiture_marque=db.getVoitureByMarque(vmarque);
                    db.del_Voiture(voiture_marque);
                }catch(Exception e){e.printStackTrace();}
                Toast.makeText(getApplicationContext(),"Supprimé", Toast.LENGTH_SHORT).show();
            }

        });
        previous_suppression_v=findViewById(R.id.previous_supression_v);
        previous_suppression_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delete_Voiture.this,Gestion_Voiture.class);
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
                Intent intent = new Intent(Delete_Voiture.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Delete_Voiture.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}

