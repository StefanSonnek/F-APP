package com.example.fapp_navi_drawer.Activities.workout;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.bll.Uebung;

import java.util.List;

/**
 * Created by pupil on 4/2/19.
 */

public class UebungAdapter extends ArrayAdapter<Uebung> {

    public UebungAdapter(Context context, List<Uebung> object){
        super(context,0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.workout_adapter,parent,false);
        }

        TextView tvBezeichnung =  convertView.findViewById(R.id.tvBezeichnung);
        TextView tvSaetze=convertView.findViewById(R.id.tvSaetze);
        TextView tvWdh=convertView.findViewById(R.id.tvWdh);
        TextView tvGwt=convertView.findViewById(R.id.tvGewicht);



        Uebung u = getItem(position);

        tvBezeichnung.setText(u.getBezeichnung());



        return convertView;
    }

}


