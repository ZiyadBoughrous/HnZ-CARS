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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Liste extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatabaseHandler DB;
    private ArrayList<Client> clientarray;
    public ImageButton previous_list;
    public SearchView searchById;
    public Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clt);
        DB = new DatabaseHandler(this);
        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Liste.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        clientarray = new ArrayList<>();
        clientarray = DB.getClientsList();


        Customize_Adapter clientArrayAdapter = new Customize_Adapter(this, clientarray);

        ListView client_listview = findViewById(R.id.list);

        client_listview.setAdapter(clientArrayAdapter);
        previous_list=findViewById(R.id.previous_list);
        previous_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Liste.this,Gestion_Clt.class);
                startActivity(intent);
            }
        });

        Customize_Adapter adapter = new Customize_Adapter(this, DB.getClientsList());

        previous_list=findViewById(R.id.previous_list);
        previous_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Liste.this,Gestion_Clt.class);
                startActivity(intent);
            }
        });
        client_listview.setClickable(true);
        client_listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent intent=new Intent(Liste.this, Modif_Clt.class);
                Client client = (Client) parent.getItemAtPosition(position);
                String string_id = Integer.toString(client.getID());
                intent.putExtra("id",string_id);

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
                Intent intent = new Intent(Liste.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Liste.this, MainActivity.class);
                startActivity(t);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}