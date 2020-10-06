package com.example.user.histoire;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by AmineYahouni on 16/05/2018.
 */

public class HistoireAdapter extends ArrayAdapter<Histoire> {
    private List<Histoire> history;

    //histoire est la liste des models à afficher
    public HistoireAdapter(Context context, List<Histoire> histoire) {
        super(context, 0, histoire);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_histoire,parent, false);
        }


        HistoireViewHolder viewHolder = (HistoireViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new HistoireViewHolder();
            viewHolder.Titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.Auteur = (TextView) convertView.findViewById(R.id.auteur);
            viewHolder.NBRpage=(TextView)convertView.findViewById(R.id.nbrpage);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<histoire>
        Histoire histoire = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.Titre.setText(histoire.getTitre());
        viewHolder.Auteur.setText(histoire.getAuteur());
        viewHolder.NBRpage.setText(histoire.getNbrPages());
        viewHolder.avatar.setImageResource(histoire.getPhoto());

        return convertView;
    }

    private class HistoireViewHolder{
        public TextView Titre;
        public TextView Auteur;
        public TextView NBRpage;
        public ImageView avatar;


    }
}
