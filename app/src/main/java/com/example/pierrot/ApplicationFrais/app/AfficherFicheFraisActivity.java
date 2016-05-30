package com.example.pierrot.ApplicationFrais.app;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pierrot.ApplicationFrais.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import Classes.FicheFrais;
import Classes.FicheFraisAdapter;
import Classes.LigneFraisForfait;
import Classes.LigneFraisForfaitAdapter;
import Classes.Visiteur;
import Classes.WebService;

public class AfficherFicheFraisActivity extends AppCompatActivity {

    // Progress dialog
    private ProgressDialog pDialog;
    private TextView textViewMoisAnnee;
    private TextView textViewEtat ;
    private TextView textViewMontant ;
    private TextView textViewDateModif;
    private ListView listViewFraisForfait;
    private ListView getListViewFraisHotsForfait;

    private Visiteur visiteur ;

    private int idFiche;

    public FicheFrais uneFicheFrais;

    private static String TAG = AcceuilFicheFraisActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_fiche_frais);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Chargement...");
        pDialog.setCancelable(false);

        visiteur = this.getIntent().getExtras().getParcelable("visiteur");
        idFiche = this.getIntent().getExtras().getInt("idFiche");

        makeJsonFicheFraisRequest(visiteur);


        textViewMoisAnnee = (TextView) findViewById(R.id.textViewMoisAnnee);
        textViewEtat = (TextView) findViewById(R.id.textViewEtat);
        textViewMontant = (TextView) findViewById(R.id.textViewMontantValider);
        textViewDateModif = (TextView) findViewById(R.id.textViewDateModif);
        listViewFraisForfait = (ListView) findViewById(R.id.listViewFraisForfait);
        getListViewFraisHotsForfait = (ListView) findViewById(R.id.listViewFraisHorsForfait);

    }



    private void makeJsonFicheFraisRequest(final Visiteur visiteur) {

        showpDialog();
        //Création de la requete qui va être envoyer
        String urlJsonVisiteur = new WebService().getUrl()+"/projetcastor/web/api/fichefrais/";

        //ajout des informations du visiteur requete qui va être envoyer
        String JSoNVisiteur = visiteur.toJSon(idFiche);

        urlJsonVisiteur += JSoNVisiteur;

        Log.e("adresse", urlJsonVisiteur);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonVisiteur, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("fiche frais", response.toString());

                try {
                    if (response.getInt("id")!=0)
                    {

                        setFicheFrais(response);

                        hidepDialog();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Erreur fiche frais", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        this.uneFicheFrais= uneFicheFrais;
    }


    private void setFicheFrais(JSONObject reponse)
    {
        this.uneFicheFrais = new FicheFrais(reponse);

        textViewMoisAnnee.setText(uneFicheFrais.getMoisAnnee());
        textViewEtat.setText(uneFicheFrais.getEtat());
        textViewMontant.setText(Double.toString(uneFicheFrais.getMontantValide())+" €");
        textViewDateModif.setText(uneFicheFrais.getDateModif().toString());

        afficherListeLigneFraisForfait(uneFicheFrais.getLesLignesFraisForfait());
    }

    private void afficherListeLigneFraisForfait(List<LigneFraisForfait> lesLignesFraisForfait){

        LigneFraisForfaitAdapter adapter = new LigneFraisForfaitAdapter(AfficherFicheFraisActivity.this, lesLignesFraisForfait);
        listViewFraisForfait.setAdapter(adapter);
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}