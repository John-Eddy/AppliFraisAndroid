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
public class LigneFraisHorsForfait extends LigneFrais implements Parcelable {
    private String libelle;

    public LigneFraisHorsForfait(int id, Double montant, Etat etat, MaDate date,String libelle) {
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
            this.etat = new Etat();
            this.etat.setEtatLigneFrais(response.getJSONObject("idetatlignefrais"));
            this.montant = response.getDouble("montant");
            this.date = new MaDate(response.getString("date"));
            this.libelle = response.getString("libellelignehorsforfait");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeDouble(this.getMontant());
        dest.writeParcelable(this.getEtat(), flags);
        dest.writeParcelable(this.getDate(),flags);
        dest.writeString(this.getLibelle());

    }

    public static final Parcelable.Creator<LigneFraisHorsForfait> CREATOR = new Parcelable.Creator<LigneFraisHorsForfait>() {
        @Override
        public LigneFraisHorsForfait createFromParcel(Parcel in) {
            return new LigneFraisHorsForfait(in);
        }

        @Override
        public LigneFraisHorsForfait[] newArray(int size) {
            return new LigneFraisHorsForfait[size];
        }
    };

    protected LigneFraisHorsForfait(Parcel in) {
        this.id = in.readInt();
        this.montant = in.readDouble();
        this.etat = in.readParcelable(Etat.class.getClassLoader());
        this.date = in.readParcelable(MaDate.class.getClassLoader());
        this.libelle = in.readString();

    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
