package com.example.myapplication;

public class Voiture {
    private int id_voiture;
    private String marque;
    private String modele;
    private String moteur;
    private String energie;
    private float vitesse_max;
    private String boite_a_vitesse;
    private float consommation_sur_100km;
    private float puissance_max_ch;
    private boolean dispo;
    private float prix_jour;

    public String getCinRenter() {
        return cinRenter;
    }

    public void setCinRenter(String cinRenter) {
        this.cinRenter = cinRenter;
    }

    private String cinRenter;

    public Voiture(){};

    public Voiture(int id_voiture, String marque,String modele,String moteur,String energie,float vitesse_max,String boite_a_vitesse,float consommation_sur_100km, float puissance_max_ch,boolean dispo,float prix_jour, String cinRenter){
        this.id_voiture=id_voiture;
        this.marque=marque;
        this.modele=modele;
        this.moteur=moteur;
        this.energie=energie;
        this.vitesse_max=vitesse_max;
        this.boite_a_vitesse=boite_a_vitesse;
        this.consommation_sur_100km=consommation_sur_100km;
        this.puissance_max_ch=puissance_max_ch;
        this.dispo=dispo;
        this.prix_jour=prix_jour;
        this.cinRenter=cinRenter;
    }

    public int getId_V() {
        return id_voiture;
    }

    public void setId_V(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMoteur() {
        return moteur;
    }

    public void setMoteur(String moteur) {
        this.moteur = moteur;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public float getVitesse_max() {
        return vitesse_max;
    }

    public void setVitesse_max(float vitesse_max) {
        this.vitesse_max = vitesse_max;
    }

    public String getBoite_a_vitesse() {
        return boite_a_vitesse;
    }

    public void setBoite_a_vitesse(String boite_a_vitesse) {
        this.boite_a_vitesse = boite_a_vitesse;
    }

    public float getConsommation_sur_100km() {
        return consommation_sur_100km;
    }

    public void setConsommation_sur_100km(float consommation_sur_100km) {
        this.consommation_sur_100km = consommation_sur_100km;
    }

    public float getPuissance_max_ch() {
        return puissance_max_ch;
    }

    public void setPuissance_max_ch(float puissance_max_ch) {
        this.puissance_max_ch = puissance_max_ch;
    }

    public int getDispo() {

        if(dispo){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public float getPrix_jour() {
        return prix_jour;
    }

    public void setPrix_jour(float prix_jour) {
        this.prix_jour = prix_jour;
    }
}
