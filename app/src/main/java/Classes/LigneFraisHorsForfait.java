package Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Utilisateur on 02/05/2016.
 */
public class LigneFraisHorsForfait extends LigneFrais  {
    private String libelle;

    public LigneFraisHorsForfait(int id, Double montant, String etat, MaDate date,String libelle) {
        this.id = id;
        this.montant = montant;
        this.etat = etat;
        this.date = date;
        this.libelle = libelle;
    }
    public LigneFraisHorsForfait(){}

    public LigneFraisHorsForfait(JSONObject response) {
        try {
            this.id = response.getInt("id");
            this.etat = response.getJSONObject("idetatlignefrais").getString("libelleetatlignefrais");
            this.montant = response.getDouble("montant");
            this.date = new MaDate(response.getString("date"));
            this.libelle = response.getString("libellelignehorsforfait");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
