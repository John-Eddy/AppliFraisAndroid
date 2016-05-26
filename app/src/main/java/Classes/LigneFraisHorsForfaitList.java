package Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 10/05/2016.
 */
class LigneFraisHorsForfaitList extends ArrayList<LigneFraisHorsForfait> implements Parcelable {
    public LigneFraisHorsForfaitList() {

    }

    public LigneFraisHorsForfaitList(Parcel in) {
        this.getFromParcel(in);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public LigneFraisHorsForfaitList createFromParcel(Parcel in) {
            return new LigneFraisHorsForfaitList(in);
        }

        @Override
        public Object[] newArray(int size) {
            return null;
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Taille de la liste
        int size = this.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            LigneFraisHorsForfait ligneFraisHorsForfait = this.get(i); //On vient lire chaque objet LigneFraisHorsForfait
            dest.writeInt(ligneFraisHorsForfait.getId());
            dest.writeDouble(ligneFraisHorsForfait.getMontant());
            dest.writeParcelable(ligneFraisHorsForfait.getEtat(), flags);
            dest.writeParcelable(ligneFraisHorsForfait.getDate(), flags);
            dest.writeString(ligneFraisHorsForfait.getLibelle());
            ;
        }
    }

    public void getFromParcel(Parcel in) {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for (int i = 0; i < size; i++) {
            LigneFraisHorsForfait ligneFraisHorsForfait = new LigneFraisHorsForfait();
            ligneFraisHorsForfait.setId(in.readInt());
            ligneFraisHorsForfait.setMontant(in.readDouble());
            Etat unEtat = in.readParcelable(Etat.class.getClassLoader());
            ligneFraisHorsForfait.setEtat(unEtat);
            MaDate uneDate = in.readParcelable(MaDate.class.getClassLoader());
            ligneFraisHorsForfait.setDate(uneDate);
            ligneFraisHorsForfait.setLibelle(in.readString());
            this.add(ligneFraisHorsForfait);
        }
    }
}