package com.example.giovanni_alexander.listview_demo04;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Giovanni_Alexander on 10/06/2017.
 */

public class AdaptadorLV extends ArrayAdapter<Persona> {
    public AdaptadorLV(Context context) {
        super(context, 0, new ArrayList<Persona>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }
        TextView tvId, tvNombreCompleto, tvDocumento, tvEdad;
        tvId = (TextView)convertView.findViewById(R.id.tvId);
        tvNombreCompleto = (TextView)convertView.findViewById(R.id.tvNombreCompleto);
        tvDocumento = (TextView)convertView.findViewById(R.id.tvDocumento);
        tvEdad = (TextView)convertView.findViewById(R.id.tvEdad);

        Persona persona = getItem(position);
        tvId.setText(persona.getId());
        tvNombreCompleto.setText(persona.getNombre() + " " + persona.getApellido());
        tvDocumento.setText(persona.getDocumento());
        tvEdad.setText(String.valueOf(persona.getEdad()));

        return convertView;
    }
}
