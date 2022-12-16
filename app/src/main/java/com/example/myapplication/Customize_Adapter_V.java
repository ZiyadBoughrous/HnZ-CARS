package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Customize_Adapter_V extends ArrayAdapter<Voiture> {
    public Customize_Adapter_V(@NonNull Context context, ArrayList<Voiture> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // if the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_customizer_v, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Voiture currentNumberPosition = getItem(position);



        // then according to the position of the view assign the desired TextView 1 for the same
        TextView V_marque = currentItemView.findViewById(R.id.marque);
        V_marque.setText(currentNumberPosition.getMarque());

        TextView V_model = currentItemView.findViewById(R.id.model);
        V_model.setText(currentNumberPosition.getModele());

        CheckBox V_dispo = currentItemView.findViewById(R.id.dispo);
        if(currentNumberPosition.getCinRenter().equals("")){
            V_dispo.setChecked(true);
        }
        else{
            V_dispo.setChecked(false);
        }



        TextView V_prix = currentItemView.findViewById(R.id.prix);
        V_prix.setText(Double.toString(currentNumberPosition.getPrix_jour()));



        // then return the recyclable view
        return currentItemView;
    }
}