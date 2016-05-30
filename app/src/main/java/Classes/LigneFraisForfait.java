package Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Utilisateur on 02/05/2016.
 */
public class LigneFraisForfait extends LigneFrais{

    private int quantite;
    private String typeFrais;

    public LigneFraisForfait(int id, Double montant, String etat, MaDate date,int quantite, String typeFrais) {
        this.id = id;
        this.montant = montant;
        this.etat = etat;
        this.date = date;
        this.quantite = quantite;
        this.typeFrais=typeFrais;
    }
    public LigneFraisForfait(){}

    public LigneFraisForfait(JSONObject response){
        try {
            this.id = response.getInt("id");
            this.etat = response.getJSONObject("idetatlignefrais").getString("libelleetatlignefrais");
            this.montant= response.getDouble("montant");
            this.date = new MaDate(response.getString("date"));
            this.quantite = response.getInt("quantite");
            this.typeFrais=response.getJSONObject("idfraisforfait").getString("libellefraisforfait");



        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }
    }



    public int getquantite() {
        return quantite;
    }

    public void setquantite(int quantite) {
        quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getTypeFrais() {
        return typeFrais;
    }

    public void setTypeFrais(String typeFrais) {
        this.typeFrais = typeFrais;
    }
}
