package Classes;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Utilisateur on 10/05/2016.
 */
public class MaDate implements Parcelable {

    private String jour;
    private String mois;
    private String annee;

    public MaDate(String jour, String mois, String annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public MaDate(String response){
        this.annee = response.substring(0,3);
        this.mois = response.substring(5,6);
        this.jour = response.substring(8,9);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getJour());
        dest.writeString(this.getMois());
        dest.writeString(this.getAnnee());
    }

    public static final Creator<MaDate> CREATOR = new Creator<MaDate>() {
        @Override
        public MaDate createFromParcel(Parcel in) {
            return new MaDate(in);
        }

        @Override
        public MaDate[] newArray(int size) {
            return new MaDate[size];
        }
    };

    protected MaDate(Parcel in) {
        this.jour = in.readString();
        this.mois = in.readString();
        this.annee = in.readString();
    }
    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
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
}
