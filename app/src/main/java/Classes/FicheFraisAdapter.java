package Classes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import com.example.pierrot.ApplicationFrais.R;

import java.util.List;

public class FicheFraisAdapter extends ArrayAdapter<FicheFrais> {

    public FicheFraisAdapter(Context context, List<FicheFrais> ficheFrais) {
        super(context, 0, ficheFrais);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_fiche,parent, false);
        }

        FicheFraisViewHolder viewHolder = (FicheFraisViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FicheFraisViewHolder();
            viewHolder.moisAnne = (TextView) convertView.findViewById(R.id.moisAnnee);
            viewHolder.montantValider = (TextView) convertView.findViewById(R.id.montantValide);
            viewHolder.libelleEtat = (TextView) convertView.findViewById(R.id.libelleEtat);
            viewHolder.id = (TextView) convertView.findViewById(R.id.id);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        FicheFrais ficheFrais = getItem(position);
        String moisAnnee =ficheFrais.getMoisAnnee();
        String montantValide="Montant validé : "+Double.toString(ficheFrais.getMontantValide());
        viewHolder.moisAnne.setText(moisAnnee);
        viewHolder.montantValider.setText(montantValide);
        viewHolder.libelleEtat.setText(ficheFrais.getEtat().getLibelle());

        viewHolder.id.setText(Integer.toString(ficheFrais.getId()));


        return convertView;
    }

    private class FicheFraisViewHolder{
        public TextView moisAnne;
        public TextView montantValider;
        public TextView libelleEtat;
        public TextView id;

    }
}