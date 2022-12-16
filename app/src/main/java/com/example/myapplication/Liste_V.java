package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Liste_V extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    private SearchView searchById_v;
    private ArrayList<Voiture> voiturearray;
    private DatabaseHandler db;
    private ImageButton previous_list_v;
    private Spinner spin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_voitures);
        db=new DatabaseHandler(this);

        voiturearray = new ArrayList<>();
        voiturearray = db.getVoituresList();
        Customize_Adapter_V adapter_v=new Customize_Adapter_V(this, voiturearray);
        ListView voiture_listview = findViewById(R.id.list_v);

        voiture_listview.setAdapter(adapter_v);
        voiture_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Liste_V.this,Modif_Voiture.class);
                Voiture itemValue= (Voiture) adapterView.getItemAtPosition(i);
                String string_id = Integer.toString(itemValue.getId_V());
                intent.putExtra("id_v",string_id);

                startActivity(intent);
            }
        });

        previous_list_v=findViewById(R.id.previous_list_v);
        previous_list_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Liste_V.this,Gestion_Voiture.class);
                startActivity(intent);
            }
        });
        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Liste_V.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                break;
            case 1:
                Intent intent = new Intent(Liste_V.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Liste_V.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}
