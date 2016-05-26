package Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Utilisateur on 26/04/2016.
 */
public class Visiteur implements Parcelable {

    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String token;

    private List<FicheFrais> lesFicheFrais;

    public Visiteur(String nom, String prenom, String username, String password, String token, List<FicheFrais> lesFicheFrais) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.token = token;
        this.lesFicheFrais = lesFicheFrais;
    }

    public Visiteur(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Visiteur(JSONObject response) {

        try {
            this.nom = response.getString("nom") ;
            this.prenom = response.getString("prenom");
            this.username = response.getString("username");
            this.password =null;
            this.token = null;
            this.lesFicheFrais=new ArrayList<FicheFrais>();


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: " , e.getMessage());
        }
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getNom());
        dest.writeString(this.getPrenom());
        dest.writeString(this.getUsername());
        dest.writeString(this.getPassword());
        dest.writeString(this.token);
        dest.writeTypedList(this.getLesFicheFrais());

    }

    public static final Parcelable.Creator<Visiteur> CREATOR = new Parcelable.Creator<Visiteur>()
    {
        @Override
        public Visiteur createFromParcel(Parcel source)
        {
            return new Visiteur(source);
        }

        @Override
        public Visiteur[] newArray(int size)
        {
            return new Visiteur[size];
        }
    };

    public Visiteur(Parcel in) {
        this.nom = in.readString();
        this.prenom = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.token = in.readString();
        this.lesFicheFrais = new ArrayList<>();
            in.readTypedList(lesFicheFrais,FicheFraisList.CREATOR);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toJSon() {

            String visiteurJSon = "{\"username\":\""+this.username+"\",";
            visiteurJSon += "\"password\":\""+this.password+"\"}";

        return visiteurJSon;
    }

    public void fromJson(JSONObject response) {
        try {
            this.nom = response.getString("nom") ;
            this.prenom = response.getString("prenom");
            this.username = response.getString("username");
            this.password =null;


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error: " , e.getMessage());
        }
    }

    public List<FicheFrais> getLesFicheFrais() {
        return lesFicheFrais;
    }

    public void setLesFicheFrais(List<FicheFrais> lesFicheFrais) {
        this.lesFicheFrais = lesFicheFrais;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void ajouterFiche(FicheFrais uneFicheFrais) {
        this.lesFicheFrais.add(uneFicheFrais);
    }
}

