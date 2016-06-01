package Classes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import com.example.pierrot.ApplicationFrais.R;

import java.util.List;

public class LigneFraisForfaitAdapter extends ArrayAdapter<LigneFraisForfait> {

    public LigneFraisForfaitAdapter(Context context, List<LigneFraisForfait> ligneFraisForfait) {
        super(context, 0, ligneFraisForfait);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_ligne_frais_forfait,parent, false);
        }

        LigneFraisForfaitViewHolder viewHolder = (LigneFraisForfaitViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new LigneFraisForfaitViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.textViewDate);
            viewHolder.etat = (TextView) convertView.findViewById(R.id.textViewEtat);
            viewHolder.type = (TextView) convertView.findViewById(R.id.textViewType);
            viewHolder.montant = (TextView) convertView.findViewById(R.id.textViewMontant);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        LigneFraisForfait ligneFraisForfait = getItem(position);
        String date =ligneFraisForfait.getDate().toString();
        String etat = ligneFraisForfait.getEtat();
        String type = ligneFraisForfait.getTypeFrais();
        String montant = Double.toString(ligneFraisForfait.getMontant());
        viewHolder.date.setText(date);
        viewHolder.etat.setText(etat);
        viewHolder.type.setText(type);
        viewHolder.montant.setText(montant+" €");

        return convertView;
    }

    private class LigneFraisForfaitViewHolder{
        public TextView date;
        public TextView etat;
        public TextView type;
        public TextView montant;

    }
}