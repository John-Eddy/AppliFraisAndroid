package com.example.pierrot.ApplicationFrais.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pierrot.ApplicationFrais.R;

import org.json.JSONException;
import org.json.JSONObject;

import Classes.Visiteur;

/**
 * Created by Utilisateur on 27/04/2016.
 */
public class ConnexionActivity extends Activity {

    private static String TAG = ConnexionActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        Button boutonConnexion = (Button) findViewById(R.id.boutonConnexion);
        final EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Connexion...");
        pDialog.setCancelable(false);

        boutonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String username = editTextUsername.getText().toString();
                    String password = editTextPassword.getText().toString();
                    Visiteur visiteurConnecter = new Visiteur(username, password);
                    makeJsonVisiteurRequest(visiteurConnecter);

                } catch (Exception e) {
                    Log.e("Erreur", e.toString());
                    Toast.makeText(getApplicationContext(),
                            e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void makeJsonVisiteurRequest(final Visiteur visiteur) {

        showpDialog();
        // json object response url
        String urlJsonVisiteur = "http://192.168.56.1:81/projetcastor/web/api/connexion/";

        String JSoNVisiteur = visiteur.toJSon();

        urlJsonVisiteur += JSoNVisiteur;

        Log.e("adresse", urlJsonVisiteur);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonVisiteur, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    if (response.getInt("id")!=0)
                    {
                        Visiteur visiteurConnecter = new Visiteur(response);
                        visiteurConnecter.setPassword(visiteur.getPassword());

                        String jsonResponse = "";
                        jsonResponse += "username: " + visiteurConnecter.getUsername() + "\n\n";
                        jsonResponse += "nom: " + visiteurConnecter.getNom() + "\n\n";
                        jsonResponse += "prenom: " + visiteurConnecter.getPrenom() + "\n\n";

                        Log.e("Visiteur ", jsonResponse);

                        hidepDialog();


                        Intent acceuilActivite = new Intent(ConnexionActivity.this, AcceuilFicheFraisActivity.class);
                        // objet qui vas nous permettre de passe des variables ici la variable passInfo
                        acceuilActivite.putExtra("visiteur",visiteurConnecter);
                        // on appelle notre activité
                        startActivity(acceuilActivite);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Nom d'utilisateur ou mot de passe inccorecte", Toast.LENGTH_SHORT).show();
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
