package Classes;

import java.util.Date;

/**
 * Created by Utilisateur on 02/05/2016.
 */
public abstract class LigneFrais {

    protected int id;
    protected Double montant;
    protected Etat etat;
    protected MaDate date;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public MaDate getDate() {
        return date;
    }

    public void setDate(MaDate date) {
        this.date = date;
    }

}
