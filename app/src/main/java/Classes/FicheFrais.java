package Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Utilisateur on 02/05/2016.
 */
public class FicheFrais {

    private int id;
    private String etat;
    private double montantValide;
    private String mois;
    private String annee;
    private MaDate dateModif;

    private List<LigneFraisForfait> lesLignesFraisForfait;
    private List<LigneFraisHorsForfait> lesLigneFraisHorsForfait;

    public FicheFrais() {
    }

    public FicheFrais(int id, String etat, double montantValide, String mois, String annee,MaDate dateModif, List<LigneFraisForfait> lesLignesFraisForfait,
                      List<LigneFraisHorsForfait> lesLigneFraisHorsForfait) {
        this.id = id;
        this.etat = etat;
        this.montantValide = montantValide;
        this.mois = mois;
        this.annee = annee;
        this.dateModif = dateModif;
        this.lesLignesFraisForfait = lesLignesFraisForfait;
        this.lesLigneFraisHorsForfait = lesLigneFraisHorsForfait;

    }
    public FicheFrais(JSONObject response){
        try {
            this.id = response.getInt("id");
            this.etat = response.getJSONObject("idetatfichefrais").getString("libelle");
            this.montantValide = response.getDouble("montantvalide");
            this.mois = response.getString("mois");
            this.annee = response.getString("annee");
            this.dateModif = new MaDate(response.getString("datemodif"));

            this.lesLignesFraisForfait = new ArrayList<LigneFraisForfait>();


            for (int i = 0 ; i< response.getJSONArray("lignes_frais_forfaits").length();i++ ){
                this.lesLignesFraisForfait.add(new LigneFraisForfait( response.getJSONArray("lignes_frais_forfaits").getJSONObject(i) ));
            }
            this.lesLigneFraisHorsForfait = new ArrayList<LigneFraisHorsForfait>();

            for (int i = 0 ; i< response.getJSONArray("lignes_frais_hors_forfaits").length();i++ ){
                this.lesLigneFraisHorsForfait.add(new LigneFraisHorsForfait( response.getJSONArray("lignes_frais_hors_forfaits").getJSONObject(i) ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantValide() {
        return montantValide;
    }

    public void setMontantValide(double montantValide) {
        this.montantValide = montantValide;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public MaDate getDateModif() {
        return dateModif;
    }

    public void setDateModif(MaDate dateModif) {
        this.dateModif = dateModif;
    }


    public List<LigneFraisForfait> getLesLignesFraisForfait() {
        return lesLignesFraisForfait;
    }

    public void setLesLignesFraisForfait(List<LigneFraisForfait> lesLignesFraisForfait) {
        this.lesLignesFraisForfait = lesLignesFraisForfait;
    }

    public List<LigneFraisHorsForfait> getLesLigneFraisHorsForfait() {
        return lesLigneFraisHorsForfait;
    }

    public void setLesLigneFraisHorsForfait(List<LigneFraisHorsForfait> lesLigneFraisHorsForfait) {
        this.lesLigneFraisHorsForfait = lesLigneFraisHorsForfait;
    }

    public String getMoisAnnee() {
        return this.mois+"/"+this.annee;
    }
}
