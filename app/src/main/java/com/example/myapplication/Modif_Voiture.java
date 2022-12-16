package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class Modif_Voiture extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    private EditText marque_v;
    private EditText model_v;
    private EditText moteur_v;
    private EditText energie_v;
    private EditText vitesse_max_v;
    private EditText boite_a_vitesse_v;
    private EditText consommation_v;
    private EditText puissance_v;
    private EditText prix_par_jour;
    private EditText cinRenter;
    private CheckBox dispo;
    private Button confirm;
    private ImageButton previous_modif_v;
    private DatabaseHandler db;
    private Spinner spin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_voiture);
        Intent receiver=getIntent();
        int i= Integer.parseInt(receiver.getStringExtra("id_v"));
        db=new DatabaseHandler(this);
        Voiture v=db.getVoitureByIdV(i);
        marque_v=findViewById(R.id.marque_modif);
        marque_v.setText(v.getMarque());
        model_v=findViewById(R.id.model_modif);
        model_v.setText(v.getModele());
        moteur_v=findViewById(R.id.moteur_modif);
        moteur_v.setText(v.getMoteur());
        energie_v=findViewById(R.id.energie_modif);
        energie_v.setText(v.getEnergie());
        vitesse_max_v=findViewById(R.id.vitesse_modif);
        vitesse_max_v.setText(Double.toString(v.getVitesse_max()));
        boite_a_vitesse_v=findViewById(R.id.boite_a_vitesse_modif);
        boite_a_vitesse_v.setText(v.getBoite_a_vitesse());
        consommation_v=findViewById(R.id.consommation_modif);
        consommation_v.setText(Double.toString(v.getConsommation_sur_100km()));
        puissance_v=findViewById(R.id.puissance_modif);
        puissance_v.setText(Double.toString(v.getPuissance_max_ch()));
        prix_par_jour=findViewById(R.id.prix_modif);
        prix_par_jour.setText(Double.toString(v.getPrix_jour()));
        cinRenter=findViewById(R.id.cinRenter);
        cinRenter.setText(v.getCinRenter());


        confirm=findViewById(R.id.confirm);
        this.spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Modif_Voiture.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        addListenerOnConfirmButton();
        addListenerOnPrevious();
    }


    public String getMarque_v() {
        return marque_v.getText().toString();
    }
    public String getModel_v(){
        return model_v.getText().toString();
    }
    public String getMoteur_v(){
        return moteur_v.getText().toString();
    }
    public String getEnergie(){
        return energie_v.getText().toString();
    }
    public float getVitesse_max_v(){
        return Float.parseFloat(vitesse_max_v.getText().toString());
    }

    public String getBoite_a_vitesse_v() {
        return boite_a_vitesse_v.getText().toString();
    }
    public float getConsommation_v(){
        return Float.parseFloat(consommation_v.getText().toString());
    }
    public float getPuissance_v(){
        return Float.parseFloat(puissance_v.getText().toString());
    }
    public float getPrix_par_jour(){
        return Float.parseFloat(prix_par_jour.getText().toString());
    }
    public String getCinRenter(){
        return cinRenter.getText().toString();
    }


    public Voiture collect_info_v(){
        Voiture voiture = new Voiture();
        voiture.setMarque(this.getMarque_v());
        voiture.setModele(this.getModel_v());
        voiture.setBoite_a_vitesse(this.getBoite_a_vitesse_v());
        voiture.setEnergie(this.getEnergie());
        voiture.setMoteur(this.getMoteur_v());
        voiture.setPrix_jour(this.getPrix_par_jour());
        voiture.setPuissance_max_ch(this.getPuissance_v());
        voiture.setConsommation_sur_100km(this.getConsommation_v());
        voiture.setVitesse_max(this.getVitesse_max_v());
        voiture.setCinRenter(this.getCinRenter());
        return voiture;
    }
    public void addListenerOnConfirmButton(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent receiver=getIntent();
                int i= Integer.parseInt(receiver.getStringExtra("id_v"));
                Voiture e=db.getVoitureByIdV(i);
                db.del_Voiture(e);
                Voiture voiture = collect_info_v();
                if(voiture.getCinRenter().equals("")){
                    Toast.makeText(Modif_Voiture.this, "Voiture disponible ajoutée", Toast.LENGTH_SHORT).show();
                    db.add_Voiture(voiture);
                }
                else if(db.CheckInColumn("Clients", "cin", voiture.getCinRenter())){
                    db.add_Voiture(voiture);
                    Toast.makeText(Modif_Voiture.this, "Voiture louée ajoutée", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Modif_Voiture.this, "Veuillez inserez un cin valide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void addListenerOnPrevious(){
        previous_modif_v=findViewById(R.id.previous_modif_v);
        previous_modif_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Modif_Voiture.this,Gestion_Voiture.class);
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
                Intent intent = new Intent(Modif_Voiture.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Modif_Voiture.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}
