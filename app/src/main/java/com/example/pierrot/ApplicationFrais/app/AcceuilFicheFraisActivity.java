package com.example.pierrot.ApplicationFrais.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.pierrot.ApplicationFrais.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Classes.FicheFrais;
import Classes.FicheFraisAdapter;
import Classes.Visiteur;

/**
 * Created by Utilisateur on 02/05/2016.
 */
public class AcceuilFicheFraisActivity extends Activity {

    // Progress dialog
    private ProgressDialog pDialog;

    private Visiteur visiteur ;


    private static String TAG = AcceuilFicheFraisActivity.class.getSimpleName();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueilfichesfrais);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Chargement...");
        pDialog.setCancelable(false);

        visiteur = this.getIntent().getExtras().getParcelable("visiteur");



        listView = (ListView) findViewById(R.id.listView);

        makeJsonFichesFraisRequest();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent AfficherFicheActivity = new Intent(AcceuilFicheFraisActivity.this, AfficherFicheFraisActivity.class);
                // objet qui vas nous permettre de passe des variables ici la variable passInfo
                AfficherFicheActivity.putExtra("visiteur", visiteur);
                AfficherFicheActivity.putExtra("idFiche", id);
                Log.d("Visiteur",visiteur.toJSon());
                // on appelle notre activité
                startActivity(AfficherFicheActivity);
            }
        });
    }

    private void makeJsonFichesFraisRequest() {

        showpDialog();

        String urlJsonFiche = "http://192.168.56.1:81/projetcastor/web/api/lesfichesfrais/";
        urlJsonFiche += this.visiteur.toJSon();

        Log.e("url:",urlJsonFiche+visiteur.describeContents());

        JsonArrayRequest req = new JsonArrayRequest(urlJsonFiche,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            // Parsing json array response
                            // loop through each json object
                            String jsonResponse = "";


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response.get(i);

                                FicheFrais uneFicheFrais = new FicheFrais(person);

                                visiteur.ajouterFiche(uneFicheFrais);

                                jsonResponse += "Mois: " + uneFicheFrais.getMoisAnnee() + "\n\n";
                                jsonResponse += "Montant valide: " + Double.toString(uneFicheFrais.getMontantValide()) + "\n\n";
                                jsonResponse += "Etat: " + uneFicheFrais.getEtat().getLibelle() + "\n\n";
                                Log.e("Fiche:",jsonResponse);

                            }
                            afficherListeFiches(visiteur.getLesFicheFrais());

                        } catch (JSONException e) {
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
                hidepDialog();
            }
        });

        // Affing request to quest queue
        AppController.getInstance().addToRequestQueue(req);
    }

    private void afficherListeFiches(List<FicheFrais> lesFichesFrais){

        FicheFraisAdapter adapter = new FicheFraisAdapter(AcceuilFicheFraisActivity.this, lesFichesFrais);
        listView.setAdapter(adapter);
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
