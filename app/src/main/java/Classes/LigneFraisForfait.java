package Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Utilisateur on 02/05/2016.
 */
public class LigneFraisForfait extends LigneFrais implements Parcelable{

    private int quantite;
    private String typeFrais;

    public LigneFraisForfait(int id, Double montant, Etat etat, MaDate date,int quantite, String typeFrais) {
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
            this.etat= new Etat ();
            this.etat.setEtatLigneFrais(response.getJSONObject("idetatlignefrais"));
            this.montant= response.getDouble("montant");
            this.date = new MaDate(response.getString("date"));
            this.quantite = response.getInt("quantite");
            this.typeFrais=response.getJSONObject("idfraisforfait").getString("libellefraisforfait");



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
        dest.writeInt(this.getquantite());
        dest.writeString(this.getTypeFrais());

    }

    public static final Parcelable.Creator<LigneFraisForfait> CREATOR = new Parcelable.Creator<LigneFraisForfait>() {
        @Override
        public LigneFraisForfait createFromParcel(Parcel in) {
            return new LigneFraisForfait(in);
        }

        @Override
        public LigneFraisForfait[] newArray(int size) {
            return new LigneFraisForfait[size];
        }
    };

    protected LigneFraisForfait(Parcel in) {
        this.id = in.readInt();
        this.montant = in.readDouble();
        this.etat = in.readParcelable(Etat.class.getClassLoader());
        this.date = in.readParcelable(MaDate.class.getClassLoader());
        this.quantite  = in.readInt();
        this.typeFrais= in.readString();

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
