package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.List;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Auto";
    //Clients Table
    private static final String TABLE_CLIENTS="Clients";
    private static final String KEY_ID="id";
    private static final String KEY_NOM="nom";
    private static final String KEY_PRENOM="prenom";
    private static final String KEY_CIN="cin";
    private static final String KEY_PHONE="phone";
    private static final String KEY_ADRESS="adress";
    private static final String KEY_DATE_DEBUT="date_debut";
    private static final String KEY_DATE_FIN="date_fin";
    private static final String KEY_HORAIRE_DEBUT="hor_debut";
    private static final String KEY_HORAIRE_FIN="hor_fin";


    //Voitures Table
    private static final String TABLE_CARS="Voitures";
    private static final String KEY_ID_V="id_voiture";
    private static final String KEY_MARQUE_V="marque";
    private static final String KEY_MODELE_V="modele";
    private static final String KEY_MOTEUR_V="moteur";
    private static final String KEY_ENERGIE_V="energie";
    private static final String KEY_VITESSE_MAX_V="vitesse_max";
    private static final String KEY_BOITE_A_VITESSE_V="boite_a_vitesse";
    private static final String KEY_CONSOMMATION_V="consommation_sur_100km";
    private static final String KEY_PUISSANCE_MAX_V="puissance_max";
    private static final String KEY_DISPONIBILITE_V="disponibilite";
    private static final String KEY_PRIX_JOUR="prix_par_jour";
    private static final String KEY_CIN_RENTER="loueur";



    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_CLIENTS_TABLE="CREATE TABLE " + TABLE_CLIENTS + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOM + " TEXT, " + KEY_PRENOM + " TEXT, " + KEY_CIN + " TEXT, " + KEY_PHONE + " TEXT, " + KEY_ADRESS + " TEXT, " + KEY_DATE_DEBUT + " TEXT, " + KEY_DATE_FIN + " TEXT, " + KEY_HORAIRE_DEBUT + " TEXT, " + KEY_HORAIRE_FIN + " TEXT);";
    String CREATE_VOITURES_TABLE="CREATE TABLE " + TABLE_CARS + " ( " + KEY_ID_V + " INTEGER PRIMARY KEY," + KEY_MARQUE_V + " TEXT, " + KEY_MODELE_V + " TEXT, " + KEY_MOTEUR_V + " TEXT, " + KEY_ENERGIE_V + " TEXT, " + KEY_VITESSE_MAX_V + " TEXT, " + KEY_BOITE_A_VITESSE_V + " TEXT, " + KEY_CONSOMMATION_V + " TEXT, " + KEY_PUISSANCE_MAX_V + " TEXT, " + KEY_DISPONIBILITE_V + " TEXT, " + KEY_PRIX_JOUR + " TEXT, " + KEY_CIN_RENTER + " TEXT);";

    db.execSQL(CREATE_CLIENTS_TABLE);
    db.execSQL(CREATE_VOITURES_TABLE);

    /*addDefaultUsers();*/
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CARS);

        onCreate(db);
    }
    public static void deleteDatabase(Context mContext) {
        mContext.deleteDatabase(DATABASE_NAME);
    }

    //--------------------------------------TABLE_CLIENTS METHODS---------------------------------------------------------
    //Add clients
    public void add_Client(Client client){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues s=new ContentValues();
        s.put(KEY_NOM,client.getNom());
        s.put(KEY_PRENOM,client.getPrenom());
        s.put(KEY_PHONE,client.getPhone());
        s.put(KEY_CIN,client.getCin());
        s.put(KEY_ADRESS,client.getAdress());
        s.put(KEY_DATE_DEBUT,client.getDate_debut());
        s.put(KEY_DATE_FIN,client.getDate_fin());
        s.put(KEY_HORAIRE_DEBUT,client.getHor_debut());
        s.put(KEY_HORAIRE_FIN,client.getHor_fin());
        db.insert(TABLE_CLIENTS, null, s);
        db.close();
    }

    //Delete Client
    public void del_Client(Client client){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CLIENTS,KEY_ID+"=?",new String[]{String.valueOf(client.getID())});
        db.close();
    }
    //Get Client by ID
    public Client getClientByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLIENTS, new String[]{KEY_ID, KEY_NOM, KEY_PRENOM, KEY_CIN,KEY_PHONE,KEY_ADRESS,KEY_DATE_DEBUT,KEY_DATE_FIN,KEY_HORAIRE_DEBUT,KEY_HORAIRE_FIN}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Client client=new Client(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9));
        return client;
    }
    //Get Client by Nom
    public Client getClientByName(String nom){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CLIENTS,new String[]{KEY_ID,KEY_NOM,KEY_PRENOM,KEY_CIN,KEY_PHONE,KEY_ADRESS,KEY_DATE_DEBUT,KEY_DATE_FIN,KEY_HORAIRE_DEBUT,KEY_HORAIRE_FIN},KEY_NOM+"=?",new String[]{String.valueOf(nom)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Client client=new Client(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4), cursor.getString(5) ,cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9));
        return client;
    }
    //Get Client by Prénom
    public Client getClientByPrenom(String prenom){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CLIENTS,new String[]{KEY_ID,KEY_NOM,KEY_PRENOM,KEY_CIN,KEY_PHONE,KEY_ADRESS,KEY_DATE_DEBUT,KEY_DATE_FIN,KEY_HORAIRE_DEBUT,KEY_HORAIRE_FIN},KEY_PRENOM+"=?",new String[]{String.valueOf(prenom)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Client client=new Client(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9));
        return client;
    }
    //Update Client

    public void updateClient(Client client){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_PRENOM,client.getPrenom());
        values.put(KEY_NOM,client.getNom());
        values.put(KEY_CIN,client.getCin());
        values.put(KEY_PHONE,client.getPhone());
        values.put(KEY_ADRESS,client.getAdress());
        values.put(KEY_DATE_DEBUT,client.getDate_debut());
        values.put(KEY_DATE_FIN,client.getDate_fin());
        values.put(KEY_HORAIRE_DEBUT,client.getHor_debut());
        values.put(KEY_HORAIRE_FIN,client.getHor_fin());
        db.update(TABLE_CLIENTS, values, KEY_ID + "=" + client.getID(), null);
        db.close();
    }


    //Nbr of Clients
    public int getClientCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String countQuery="SELECT * FROM "+TABLE_CLIENTS;
        Cursor cursor=db.rawQuery(countQuery,null);
        return cursor.getCount();
    }
    //Get All Clients in a List
    public ArrayList<Client> getClientsList(){
        ArrayList<Client> listClient=new ArrayList<Client>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_query="SELECT * FROM "+TABLE_CLIENTS;
        Cursor cursor= db.rawQuery(list_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            while(cursor.moveToNext()){
                Client client=new Client();
                client.setID(Integer.parseInt(cursor.getString(0)));
                client.setNom(cursor.getString(1));
                client.setPrenom(cursor.getString(2));
                client.setCin(cursor.getString(3));
                client.setPhone(cursor.getString(4));
                client.setAdress(cursor.getString(5));
                client.setDate_debut(cursor.getString(6));
                client.setDate_fin(cursor.getString(7));
                client.setHor_debut(cursor.getString(8));
                client.setHor_fin(cursor.getString(9));
                listClient.add(client);
            }
        }
        return listClient;
    }


    public boolean CheckInColumn(String TableName, String dbfield, String fieldValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    //Get a Table of ids
    public ArrayList<Integer> getClientsIds(){
        ArrayList<Integer> ids=new ArrayList<Integer>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_id_query="SELECT id FROM "+TABLE_CLIENTS;
        Cursor cursor=db.rawQuery(list_id_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            cursor.moveToFirst();
            while(cursor.moveToNext()) {
                ids.add(Integer.parseInt(cursor.getString(0)));
            }
        }
        return ids;
    }
    //Get a Table of Noms
    public List<String> getClientsNoms(){
        List<String> noms=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_noms_query="SELECT nom FROM "+TABLE_CLIENTS;
        Cursor cursor=db.rawQuery(list_noms_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                noms.add(cursor.getString(0));
            }
        }
        return noms;
    }
    //Get a Table of prenoms
    public ArrayList<String> getClientsPrenoms(){
        ArrayList<String> prenoms=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_prenoms_query="SELECT prenom FROM "+TABLE_CLIENTS;
        Cursor cursor=db.rawQuery(list_prenoms_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                prenoms.add(cursor.getString(0));
            }
        }
        return prenoms;
    }
    //Get a Table of Complete name
    public ArrayList<String> getClientsNomsComplets(){
        ArrayList<String> noms_complets=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_noms_complets_query="SELECT nom  || ' ' ||  prenom FROM "+TABLE_CLIENTS;
        Cursor cursor=db.rawQuery(list_noms_complets_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                noms_complets.add(cursor.getString(0));
            }
        }
        return noms_complets;
    }

    //------------------------------------TABLE_VOITURES METHODS------------------------------------------
    //ADD VOITURE
    public void add_Voiture(Voiture voiture){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues s=new ContentValues();
        s.put(KEY_MARQUE_V,voiture.getMarque());
        s.put(KEY_MODELE_V,voiture.getModele());
        s.put(KEY_MOTEUR_V,voiture.getMoteur());
        s.put(KEY_ENERGIE_V,voiture.getEnergie());
        s.put(KEY_VITESSE_MAX_V,voiture.getVitesse_max());
        s.put(KEY_BOITE_A_VITESSE_V,voiture.getBoite_a_vitesse());
        s.put(KEY_CONSOMMATION_V,voiture.getConsommation_sur_100km());
        s.put(KEY_PUISSANCE_MAX_V,voiture.getPuissance_max_ch());
        s.put(KEY_DISPONIBILITE_V,voiture.getDispo());
        s.put(KEY_PRIX_JOUR,voiture.getPrix_jour());
        s.put(KEY_CIN_RENTER,voiture.getCinRenter());

        db.insert(TABLE_CARS,null,s);
    }

    //Get Voiture par ID_V
    public Voiture getVoitureByIdV(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CARS, new String[]{KEY_ID_V,KEY_MARQUE_V,KEY_MODELE_V,KEY_MOTEUR_V,KEY_ENERGIE_V,KEY_VITESSE_MAX_V,KEY_BOITE_A_VITESSE_V,KEY_CONSOMMATION_V,KEY_PUISSANCE_MAX_V,KEY_DISPONIBILITE_V,KEY_PRIX_JOUR,KEY_CIN_RENTER}, KEY_ID_V + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Voiture voiture=new Voiture(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),Float.parseFloat(cursor.getString(5)),cursor.getString(6),Float.parseFloat(cursor.getString(7)),Float.parseFloat(cursor.getString(8)),Boolean.parseBoolean(cursor.getString(9)),Float.parseFloat(cursor.getString(10)),cursor.getString(11));
        return voiture;
    }

    //Get Voiture par Marque
    public Voiture getVoitureByMarque(String marque) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CARS, new String[]{KEY_ID_V,KEY_MARQUE_V,KEY_MODELE_V,KEY_MOTEUR_V,KEY_ENERGIE_V,KEY_VITESSE_MAX_V,KEY_BOITE_A_VITESSE_V,KEY_CONSOMMATION_V,KEY_PUISSANCE_MAX_V,KEY_DISPONIBILITE_V,KEY_PRIX_JOUR,KEY_CIN_RENTER}, KEY_MARQUE_V + "=?", new String[]{String.valueOf(marque)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Voiture voiture=new Voiture(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),Float.parseFloat(cursor.getString(5)),cursor.getString(6),Float.parseFloat(cursor.getString(7)),Float.parseFloat(cursor.getString(8)),Boolean.parseBoolean(cursor.getString(9)),Float.parseFloat(cursor.getString(10)),cursor.getString(11));
        return voiture;
    }

    //Delete Voiture
    public void del_Voiture(Voiture voiture){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CARS,KEY_ID_V+"=?",new String[]{String.valueOf(voiture.getId_V())});
        db.close();
    }
    //Update Voiture
    public void updateVoiture(Voiture voiture){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ID_V,voiture.getId_V());
        values.put(KEY_MARQUE_V,voiture.getMarque());
        values.put(KEY_MODELE_V,voiture.getModele());
        values.put(KEY_MOTEUR_V,voiture.getMoteur());
        values.put(KEY_ENERGIE_V,voiture.getEnergie());
        values.put(KEY_VITESSE_MAX_V,voiture.getVitesse_max());
        values.put(KEY_BOITE_A_VITESSE_V,voiture.getBoite_a_vitesse());
        values.put(KEY_CONSOMMATION_V,voiture.getConsommation_sur_100km());
        values.put(KEY_PUISSANCE_MAX_V,voiture.getPuissance_max_ch());
        values.put(KEY_DISPONIBILITE_V,voiture.getDispo());
        values.put(KEY_PRIX_JOUR,voiture.getPrix_jour());
        db.update(TABLE_CARS,values,KEY_ID_V+"=?",new String[]{String.valueOf(voiture.getId_V())});
    }
    //Nbr of Voitures
    public int getVoituresCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String countQuery="SELECT * FROM "+TABLE_CARS;
        Cursor cursor=db.rawQuery(countQuery,null);
        return cursor.getCount();
    }
    //Get All Voitures in a List
    public ArrayList<Voiture> getVoituresList(){
        ArrayList<Voiture> listVoiture=new ArrayList<Voiture>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_query="SELECT * FROM "+TABLE_CARS;
        Cursor cursor= db.rawQuery(list_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            while(cursor.moveToNext()){
                Voiture voiture=new Voiture();
                voiture.setId_V(Integer.parseInt(cursor.getString(0)));
                voiture.setMarque(cursor.getString(1));
                voiture.setModele(cursor.getString(2));
                voiture.setMoteur(cursor.getString(3));
                voiture.setEnergie(cursor.getString(4));
                voiture.setVitesse_max(Float.parseFloat(cursor.getString(5)));
                voiture.setBoite_a_vitesse(cursor.getString(6));
                voiture.setConsommation_sur_100km(Float.parseFloat(cursor.getString(7)));
                voiture.setPuissance_max_ch(Float.parseFloat(cursor.getString(8)));
                voiture.setDispo(Boolean.parseBoolean(cursor.getString(9)));
                voiture.setPrix_jour(Float.parseFloat(cursor.getString(10)));
                voiture.setCinRenter(cursor.getString(11));



                listVoiture.add(voiture);
            }
        }
        return listVoiture;
    }
    //Get a Table of id_v
    public List<Integer> getVoituresIds(){
        List<Integer> ids_v=new ArrayList<Integer>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_ids_v_query="SELECT id_voiture FROM "+TABLE_CARS;
        Cursor cursor=db.rawQuery(list_ids_v_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            do {
                cursor.moveToFirst();
                ids_v.add(Integer.parseInt(cursor.getString(0)));
            } while (cursor.moveToNext());
        }
        return ids_v;
    }
    //Get a Table of Marques Voitures
    public List<String> getVoituresMarques(){
        List<String> marques=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        String list_marques_v_query="SELECT marque FROM "+TABLE_CARS;
        Cursor cursor=db.rawQuery(list_marques_v_query,null);
        if(cursor!=null&&cursor.getCount()>0) {
            do {
                cursor.moveToFirst();
                marques.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return marques;
    }




}



