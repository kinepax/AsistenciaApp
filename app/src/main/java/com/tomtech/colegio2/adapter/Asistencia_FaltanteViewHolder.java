package com.tomtech.colegio2.adapter;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tomtech.colegio2.R;
import com.tomtech.colegio2.dto.AsistenciaFaltanteDTO;

public class Asistencia_FaltanteViewHolder extends RecyclerView.ViewHolder{

    public TextView txtNombreAlumno;
    public Spinner spAsistencia;

    public final String[] estados_asistencia = {"P","T","TJ","F","FJ"};

    private ListenerItemAsistenciaFaltante listener;

    public Button btnQuitar;








    public Asistencia_FaltanteViewHolder(View itemView) {

        super(itemView);

        this.txtNombreAlumno=(TextView)itemView.findViewById(R.id.txtNombreAlumno);
        this.spAsistencia=itemView.findViewById(R.id.spAsistencia);
        this.btnQuitar=itemView.findViewById(R.id.btnQuitar);




    }
/*
    public void llenar(List<AsistenciaFaltante> asistenciaFaltanteList){


        for(AsistenciaFaltante asistenciaFaltante : asistenciaFaltanteList){

            txtNombreAlumno.setText(asistenciaFaltante.getNombres()+" "+asistenciaFaltante.getApellidoPaterno()+" "+asistenciaFaltante.getApellidoMaterno());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item,estados_asistencia);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spAsistencia.setAdapter(adapter);

            asistenciaFaltante.setSpAsistencias(spAsistencia);

        }



    }

    */


    public void llenar(AsistenciaFaltanteDTO asistenciaFaltante){




            txtNombreAlumno.setText(asistenciaFaltante.getNombreAlumno());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item,estados_asistencia);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spAsistencia.setAdapter(adapter);

            asistenciaFaltante.setSpAsistencias(spAsistencia);
        initListener();












    }
    public void initListener(){

        btnQuitar.setOnClickListener(view -> {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION && listener!=null){
                listener.onEliminarClick(position);
            }
        });






    }

    public void setListener(ListenerItemAsistenciaFaltante listener){

        this.listener=listener;
    }




}
