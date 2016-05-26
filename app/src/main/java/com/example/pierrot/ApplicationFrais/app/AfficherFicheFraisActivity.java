package com.example.pierrot.ApplicationFrais.app;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pierrot.ApplicationFrais.R;

import Classes.Visiteur;

public class AfficherFicheFraisActivity extends AppCompatActivity {

    private Visiteur visiteur ;

    private int idFiche;

    private static String TAG = AcceuilFicheFraisActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_fiche_frais);

        visiteur = this.getIntent().getExtras().getParcelable("visiteur");
        idFiche = this.getIntent().getExtras().getInt("id");


        TextView textViewMois = (TextView) findViewById(R.id.textViewMois);
        TextView textViewEtat = (TextView) findViewById(R.id.textViewEtat);
        TextView textViewMontant = (TextView) findViewById(R.id.textViewMontantValider);

        textViewMois.setText(visiteur.getLesFicheFrais().get(idFiche).getMois());
        textViewEtat.setText(visiteur.getLesFicheFrais().get(idFiche).getMois());
        textViewMontant.setText(visiteur.getLesFicheFrais().get(idFiche).getMois());







    }
}