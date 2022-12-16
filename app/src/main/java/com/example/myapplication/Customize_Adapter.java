package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Customize_Adapter extends ArrayAdapter<Client> {
    public Customize_Adapter(@NonNull Context context, ArrayList<Client> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // if the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_customizer, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Client currentNumberPosition = getItem(position);



        // then according to the position of the view assign the desired TextView 1 for the same
        TextView client_prenom = currentItemView.findViewById(R.id.prenom);
        client_prenom.setText(currentNumberPosition.getPrenom());

        TextView client_nom = currentItemView.findViewById(R.id.nom);
        client_nom.setText(currentNumberPosition.getNom());

        TextView client_Ddebut = currentItemView.findViewById(R.id.Ddebut);
        client_Ddebut.setText(currentNumberPosition.getDate_debut());

        TextView client_Dfin = currentItemView.findViewById(R.id.Dfin);
        client_Dfin.setText(currentNumberPosition.getDate_fin());



        // then return the recyclable view
        return currentItemView;
    }
}