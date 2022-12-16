package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Modif_Clt extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    public ImageButton photo;
    public EditText id;
    public EditText nom;
    public EditText prenom;
    public EditText cin;
    public EditText phone;
    public EditText adress;
    public DatePicker d_pick1;
    public TimePicker t_pick1;
    public DatePicker d_pick2;
    public TimePicker t_pick2;
    public Button add_btn;
    public ImageButton previous_modif;
    public DatabaseHandler db;
    private Spinner spin;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_clt);
        Intent receiver=getIntent();
        int i= Integer.parseInt(receiver.getStringExtra("id"));
        db=new DatabaseHandler(this);
        Client e=db.getClientByID(i);
        this.photo=findViewById(R.id.photo);
        this.nom=findViewById(R.id.nom);
        nom.setText(e.getNom());
        this.prenom=findViewById(R.id.prenom);
        prenom.setText(e.getPrenom());
        this.cin=findViewById(R.id.cin);
        cin.setText(e.getCin());
        this.phone=findViewById(R.id.portable);
        phone.setText(e.getPhone());
        this.adress=findViewById(R.id.text5);
        adress.setText(e.getAdress());
        this.d_pick1=findViewById(R.id.DatePicker);
        String[] Ddebut = e.getDate_debut().split("/");
        int year =Integer.parseInt(Ddebut[2]);
        int month =Integer.parseInt(Ddebut[1]);
        int day =Integer.parseInt(Ddebut[0]);
        d_pick1.init(year,month, day,null );
        this.d_pick2=findViewById(R.id.DatePicker1);
        String[] Dfin = e.getDate_fin().split("/");
        int yearf =Integer.parseInt(Dfin[2]);
        int monthf =Integer.parseInt(Dfin[1]);
        int dayf =Integer.parseInt(Dfin[0]);
        d_pick2.init(yearf,monthf, dayf,null );
        this.t_pick1=findViewById(R.id.TimePicker);
        String[] Tdebut = e.getHor_debut().split(":");
        int hour =Integer.parseInt(Tdebut[0]);
        t_pick1.setHour(hour);
        int minute =Integer.parseInt(Tdebut[1]);
        t_pick1.setMinute(minute);
        this.t_pick2=findViewById(R.id.TimePicker1);
        String[] Tfin = e.getHor_fin().split(":");
        int hourf =Integer.parseInt(Tfin[0]);
        t_pick2.setHour(hourf);
        int minutef =Integer.parseInt(Tfin[1]);
        t_pick2.setMinute(minutef);
        this.add_btn=findViewById(R.id.button);
        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Modif_Clt.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        addOnClickListener();
        addListenerOnPrevious();
    }
    public void addListenerOnPhoto(){
        photo=findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });
    }

    public String getNomClient(){
        String s=nom.getText().toString();
        return s;
    }
    public String getPrenomClient(){
        String s=prenom.getText().toString();
        return s;
    }
    public String getCinClient(){
        String s=cin.getText().toString();
        return s;
    }
    public String getPhoneClient(){
        String s=phone.getText().toString();
        return s;
    }
    public String getAdressClient(){
        String s=adress.getText().toString();
        return s;
    }
    public String getSelectionnedDate1(){
        StringBuilder builder=new StringBuilder();
        builder.append((d_pick1.getDayOfMonth())+"/");
        builder.append((d_pick1.getMonth()+1)+"/");
        builder.append((d_pick1.getYear()));
        return builder.toString();
    }
    public String getSelectionnedDate2(){
        StringBuilder builder=new StringBuilder();
        builder.append((d_pick2.getDayOfMonth())+"/");
        builder.append((d_pick2.getMonth()+1)+"/");
        builder.append((d_pick2.getYear()));
        return builder.toString();
    }
    public String getSelectionnedTime1(){
        String s=new String();
        s=t_pick1.getHour()+":"+t_pick1.getMinute();
        return s;
    }
    public String getSelectionnedTime2(){
        String s=new String();
        s=t_pick2.getHour()+":"+t_pick2.getMinute();
        return s;
    }
    private Client collectInfo() {
        Client client = new Client();
        client.setNom(this.getNomClient());
        client.setPrenom(this.getPrenomClient());
        client.setCin(this.getCinClient());
        client.setPhone(this.getPhoneClient());
        client.setAdress(this.getAdressClient());
        client.setDate_debut(this.getSelectionnedDate1());
        client.setDate_fin(this.getSelectionnedDate2());
        client.setHor_debut(this.getSelectionnedTime1());
        client.setHor_fin(this.getSelectionnedTime2());
        return client;
    }
    public void addOnClickListener(){
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent receiver=getIntent();
                int i= Integer.parseInt(receiver.getStringExtra("id"));
                Client e=db.getClientByID(i);
                db.del_Client(e);
                Client client_new = collectInfo();
                db.add_Client(client_new);

                Toast.makeText(getApplicationContext(),getNomClient(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addListenerOnPrevious(){
        previous_modif=findViewById(R.id.previous_modif);
        previous_modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Modif_Clt.this,Gestion_Clt.class);
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
                Intent intent = new Intent(Modif_Clt.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Modif_Clt.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}
