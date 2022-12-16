package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText nom_user;
    public EditText pass_user;
    public Button confirm;
    public DatabaseHandler db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){}
        RelativeLayout currentLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        currentLayout.setBackgroundColor(Color.WHITE);
        nom_user=findViewById(R.id.user);
        pass_user=findViewById(R.id.pass);
        confirm=findViewById(R.id.confirmer);
        db=new DatabaseHandler(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom_user.getText().toString().equals("admin") && pass_user.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Connecté avec Succès", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), My_Dashboard.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login ou Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}