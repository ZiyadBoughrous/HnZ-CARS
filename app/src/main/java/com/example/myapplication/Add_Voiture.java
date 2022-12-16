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


public class Add_Voiture extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    private EditText marque_v;
    private EditText model_v;
    private EditText moteur_v;
    private EditText energie_v;
    private EditText vitesse_max_v;
    private EditText boite_a_vitesse_v;
    private EditText consommation_v;
    private EditText puissance_v;
    private EditText prix_par_jour;
    private Button ajout_v;
    private EditText cinRenter;
    private ImageButton previous_ajout_v;
    private Spinner spin;
    private DatabaseHandler db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_voiture);
        db=new DatabaseHandler(this);
        marque_v=findViewById(R.id.marque_v);
        model_v=findViewById(R.id.model_v);
        moteur_v=findViewById(R.id.moteur_v);
        energie_v=findViewById(R.id.energie);
        vitesse_max_v=findViewById(R.id.vitesse_max_v);
        boite_a_vitesse_v=findViewById(R.id.boite_vitesse_v);
        consommation_v=findViewById(R.id.consommation_v);
        puissance_v=findViewById(R.id.puissance_max_v);
        prix_par_jour=findViewById(R.id.prix_par_jour);
        cinRenter=findViewById(R.id.cinRenter);

        ajout_v=findViewById(R.id.ajouter_v);
        addListenerOnAddButton();
        addListenerOnPrevious();
        spin=findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Add_Voiture.this, android.R.layout.simple_spinner_item, Add_Clt.menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
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

    public void addListenerOnAddButton(){
        ajout_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Voiture voiture = collect_info_v();
                if(voiture.getCinRenter().equals("")){
                    Toast.makeText(Add_Voiture.this, "Voiture disponible ajoutée", Toast.LENGTH_SHORT).show();
                    db.add_Voiture(voiture);
                    /*      marque_v=findViewById(R.id.marque_v);
                            model_v=findViewById(R.id.model_v);
                            moteur_v=findViewById(R.id.moteur_v);
                            energie_v=findViewById(R.id.energie);
                            vitesse_max_v=findViewById(R.id.vitesse_max_v);
                            boite_a_vitesse_v=findViewById(R.id.boite_vitesse_v);
                            consommation_v=findViewById(R.id.consommation_v);
                            puissance_v=findViewById(R.id.puissance_max_v);
                            prix_par_jour=findViewById(R.id.prix_par_jour);
                            cinRenter=findViewById(R.id.cinRenter);
                    */
                    marque_v.setText("");
                    model_v.setText("");
                    moteur_v.setText("");
                    energie_v.setText("");
                    vitesse_max_v.setText("");
                }
                else if(db.CheckInColumn("Clients", "cin", voiture.getCinRenter())){
                    db.add_Voiture(voiture);
                    Toast.makeText(Add_Voiture.this, "Voiture louée ajoutée", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Add_Voiture.this, "Veuillez inserez un cin valide", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void addListenerOnPrevious(){
        previous_ajout_v=findViewById(R.id.previous_ajout_v);
        previous_ajout_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Add_Voiture.this,Gestion_Voiture.class);
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
                Intent intent = new Intent(Add_Voiture.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Add_Voiture.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }
}
