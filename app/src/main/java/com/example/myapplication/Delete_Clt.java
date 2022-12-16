package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Delete_Clt extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    public ImageButton delete;
    public AutoCompleteTextView search_id;
    public AutoCompleteTextView search_nom;
    public AutoCompleteTextView search_prenom;
    public ImageButton previous_suppression;
    public Spinner spin;
    private final DatabaseHandler db=new DatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suppression_clt);
        search_id=findViewById(R.id.searchById);
        search_nom=findViewById(R.id.searchByNom);
        search_prenom=findViewById(R.id.searchByPrenom);
        spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Delete_Clt.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        delete=findViewById(R.id.supprimer);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Integer n=Integer.parseInt(search_id.getText().toString());
                    Client client_id=db.getClientByID(n);
                    db.del_Client(client_id);
                }catch(Exception e){e.printStackTrace();}
                try{
                    String snom= search_nom.getText().toString();
                    Client client_nom=db.getClientByName(snom);
                    db.del_Client(client_nom);
                }catch(Exception e){e.printStackTrace();}
                try{
                    String pnom= search_prenom.getText().toString();
                    Client client_prenom=db.getClientByPrenom(pnom);
                    db.del_Client(client_prenom);
                }catch(Exception e){e.printStackTrace();}
                Toast.makeText(getApplicationContext(),"Supprimé", Toast.LENGTH_SHORT).show();
            }

        });
        previous_suppression=findViewById(R.id.previous_suppression);
        previous_suppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delete_Clt.this,Gestion_Clt.class);
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
                Intent intent = new Intent(Delete_Clt.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Delete_Clt.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}
