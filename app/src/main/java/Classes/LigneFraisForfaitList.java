package Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 10/05/2016.
 */
class LigneFraisForfaitList extends ArrayList<LigneFraisForfait> implements Parcelable
{
    public LigneFraisForfaitList()
    {

    }

    public LigneFraisForfaitList(Parcel in)
    {
        this.getFromParcel(in);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public LigneFraisForfaitList createFromParcel(Parcel in)
        {
            return new LigneFraisForfaitList(in);
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
    public void writeToParcel(Parcel dest, int flags)
    {
        //Taille de la liste
        int size = this.size();
        dest.writeInt(size);
        for(int i=0; i < size; i++)
        {
            LigneFraisForfait ligneFraisForfait = this.get(i); //On vient lire chaque objet LigneFraisForfait
            dest.writeInt(ligneFraisForfait.getId());
            dest.writeDouble(ligneFraisForfait.getMontant());
            dest.writeParcelable(ligneFraisForfait.getEtat(), flags);
            dest.writeParcelable(ligneFraisForfait.getDate(),flags);
            dest.writeInt(ligneFraisForfait.getquantite());
            dest.writeString(ligneFraisForfait.getTypeFrais());
        }
    }

    public void getFromParcel(Parcel in) {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for (int i = 0; i < size; i++) {
            LigneFraisForfait ligneFraisForfait = new LigneFraisForfait();
            ligneFraisForfait.setId(in.readInt());
            ligneFraisForfait.setMontant(in.readDouble());
            Etat unEtat = Etat.CREATOR.createFromParcel(in);
            ligneFraisForfait.setEtat(unEtat);
            MaDate uneDate = MaDate.CREATOR.createFromParcel(in);
            ligneFraisForfait.setDate(uneDate);
            ligneFraisForfait.setquantite(in.readInt());
            ligneFraisForfait.setTypeFrais(in.readString());
            this.add(ligneFraisForfait);
        }
    }
}