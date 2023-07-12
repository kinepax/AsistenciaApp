package com.tomtech.colegio2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tomtech.colegio2.R;
import com.tomtech.colegio2.model.Registro_Asistencia;

public class Registro_AsistenciaViewHolder extends RecyclerView.ViewHolder{

    public TextView txtFecha;
    public TextView txtAsistencia;
    public TextView txtGrado;
    public TextView txtNombre;

    public TextView txtHora;


    public Registro_AsistenciaViewHolder(View itemView) {

        super(itemView);

        this.txtHora=(TextView)itemView.findViewById(R.id.txtFecha);
        this.txtAsistencia= (TextView) itemView.findViewById(R.id.txtAsistencia);
        this.txtGrado=(TextView) itemView.findViewById(R.id.txtGrado);
        this.txtNombre=(TextView) itemView.findViewById(R.id.txtNombreAlumno);
    }

    public void llenar(Registro_Asistencia registro){

        txtHora.setText(registro.getHora());
        txtAsistencia.setText(registro.getAsistencia());
        txtGrado.setText(registro.getMatricula().getGrado().getGrado());
        txtNombre.setText(""+registro.getAlumno().getNombres()+" "+registro.getAlumno().getApellido_paterno()+" " +registro.getAlumno().getApellido_materno());


    }


}
