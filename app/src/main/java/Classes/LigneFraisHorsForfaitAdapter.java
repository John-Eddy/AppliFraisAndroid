package Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pierrot.ApplicationFrais.R;

import java.util.List;

public class LigneFraisHorsForfaitAdapter extends ArrayAdapter<LigneFraisHorsForfait> {

    public LigneFraisHorsForfaitAdapter(Context context, List<LigneFraisHorsForfait> ligneFraisHorsForfait) {
        super(context, 0, ligneFraisHorsForfait);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_ligne_frais_forfait,parent, false);
        }

        LigneFraisHorsForfaitViewHolder viewHolder = (LigneFraisHorsForfaitViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new LigneFraisHorsForfaitViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.textViewDate);
            viewHolder.etat = (TextView) convertView.findViewById(R.id.textViewEtat);
            viewHolder.libelle = (TextView) convertView.findViewById(R.id.textViewType);
            viewHolder.montant = (TextView) convertView.findViewById(R.id.textViewMontant);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        LigneFraisHorsForfait ligneFraisHorsForfait = getItem(position);
        String date =ligneFraisHorsForfait.getDate().toString();
        String etat = ligneFraisHorsForfait.getEtat();
        String libelle = ligneFraisHorsForfait.getLibelle();
        String montant = Double.toString(ligneFraisHorsForfait.getMontant());
        viewHolder.date.setText(date);
        viewHolder.etat.setText(etat);
        viewHolder.libelle.setText(libelle);
        viewHolder.montant.setText(montant+" €");

        return convertView;
    }

    private class LigneFraisHorsForfaitViewHolder{
        public TextView date;
        public TextView etat;
        public TextView libelle;
        public TextView montant;

    }
}