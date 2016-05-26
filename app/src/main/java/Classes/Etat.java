package Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Utilisateur on 02/05/2016.
 */
public  class Etat implements Parcelable {

    protected int id;
    protected String libelle;

    public Etat(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    public Etat(){

    }
    public void setEtatLigneFrais(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.libelle = json.getString("libelleetatlignefrais");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }
    }

    public void setEtatFicheFrais(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.libelle = json.getString("libelle");

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
        dest.writeInt(id);
        dest.writeString(libelle);
    }

    public static final Creator<Etat> CREATOR = new Creator<Etat>() {
        @Override
        public Etat createFromParcel(Parcel in) {
            return new Etat(in);
        }

        @Override
        public Etat[] newArray(int size) {
            return new Etat[size];
        }
    };

    protected Etat(Parcel in) {
        this.id = in.readInt();
        this.libelle = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
