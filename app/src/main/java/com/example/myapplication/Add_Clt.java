package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Add_Clt extends android.app.Activity implements AdapterView.OnItemSelectedListener{
    public static String[] menu={"Options :","Menu Principal","Se déconnecter"};
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
    public ImageButton previous;
    public ImageButton profile;
    public Spinner spin;
    public static final int PICK_IMAGE = 1;
    public DatabaseHandler db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_clt);
        this.nom = findViewById(R.id.text2);
        this.prenom = findViewById(R.id.text1);
        this.cin = findViewById(R.id.text3);
        this.phone = findViewById(R.id.text4);
        this.adress = findViewById(R.id.text5);
        this.d_pick1 = findViewById(R.id.datePicker);
        this.d_pick2 = findViewById(R.id.datePicker1);
        this.t_pick1 = findViewById(R.id.timePicker);
        this.t_pick2 = findViewById(R.id.timePicker1);
        this.spin = findViewById(R.id.spin);
        this.profile=findViewById(R.id.photo);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(Add_Clt.this, android.R.layout.simple_spinner_item, menu);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        addListenerOnButton();
        addListenerOnPrevious();
        addOnClickListenerOnProfile();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        db=new DatabaseHandler(this);
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
        String s=t_pick1.getCurrentHour()+":"+t_pick1.getCurrentMinute();
        return s;
    }
    public String getSelectionnedTime2(){
        String s=t_pick2.getCurrentHour()+":"+t_pick2.getCurrentMinute();
        return s;
    }
    private Client collectInfo(){
        Client client=new Client();
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
    public void addListenerOnButton(){
        add_btn=findViewById(R.id.button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*Add_Clt client=new Add_Clt();
                db.add_Client(client.collectInfo());*/
                db.add_Client(collectInfo());
                Toast.makeText(getApplicationContext(),"Ajouté", Toast.LENGTH_SHORT).show();

                nom.setText("");
                prenom.setText("");
                cin.setText("");
                phone.setText("");
                adress.setText("");
                d_pick1.init(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH),null);
                d_pick2.init(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH),null);
                t_pick1.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
                t_pick1.setHour(Calendar.getInstance().get(Calendar.HOUR));
                t_pick2.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
                t_pick2.setHour(Calendar.getInstance().get(Calendar.HOUR));

            }
        });
    }
    public void addListenerOnPrevious(){
        previous=findViewById(R.id.previous_ajout);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Add_Clt.this,Gestion_Clt.class);
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
                Intent intent = new Intent(Add_Clt.this, My_Dashboard.class);
                startActivity(intent);
                break;
            case 2:
                Intent t= new Intent(Add_Clt.this, MainActivity.class);
                startActivity(t);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Auto-generated method stub
    }

    public void addOnClickListenerOnProfile(){
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }
    byte[] image_clt;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE && resultCode==RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            byte[] bytesImage = byteArrayOutputStream.toByteArray();
            image_clt=bytesImage;
        }
    }
}
