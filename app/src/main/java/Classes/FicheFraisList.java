package Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Utilisateur on 10/05/2016.
 */
public class FicheFraisList extends ArrayList<FicheFrais> implements Parcelable{

    public FicheFraisList()
    {

    }

    public FicheFraisList(Parcel in)
    {
        this.getFromParcel(in);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public FicheFraisList createFromParcel(Parcel in)
        {
            return new FicheFraisList(in);
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
            FicheFrais ficheFrais = this.get(i); //On vient lire chaque objet FicheFraisList
            dest.writeInt(ficheFrais.getId());
            dest.writeParcelable(ficheFrais.getEtat(),flags);
            dest.writeDouble(ficheFrais.getMontantValide());
            dest.writeString(ficheFrais.getMois());
            dest.writeString(ficheFrais.getAnnee());
            dest.writeParcelable(ficheFrais.getDateModif(),flags);
            dest.writeTypedList(ficheFrais.getLesLignesFraisForfait());
            dest.writeTypedList(ficheFrais.getLesLigneFraisHorsForfait());
        }
    }

    public void getFromParcel(Parcel in) {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for (int i = 0; i < size; i++) {
            FicheFrais ficheFrais = new FicheFrais();
            ficheFrais.setId(in.readInt());
            Etat unEtat = Etat.CREATOR.createFromParcel(in);
            ficheFrais.setEtat(unEtat);
            ficheFrais.setMontantValide(in.readDouble());
            ficheFrais.setMois(in.readString());
            ficheFrais.setAnnee(in.readString());
            MaDate unDate = MaDate.CREATOR.createFromParcel(in);
            ficheFrais.setDateModif(unDate);

            ArrayList lesLignesFraisForfait = new ArrayList<>();
            in.readTypedList(lesLignesFraisForfait, LigneFraisForfaitList.CREATOR);
            ficheFrais.setLesLignesFraisForfait(lesLignesFraisForfait);

            ArrayList lesLigneFraisHorsForfait= new ArrayList<>();
            in.readTypedList(lesLigneFraisHorsForfait, LigneFraisHorsForfaitList.CREATOR);
            ficheFrais.setLesLigneFraisHorsForfait(lesLigneFraisHorsForfait);

            this.add(ficheFrais);
        }
    }
}
