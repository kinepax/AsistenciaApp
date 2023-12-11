package com.tomtech.colegio2.adapter;

import android.content.res.Resources;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tomtech.colegio2.R;
import com.tomtech.colegio2.model.Registro_Asistencia;

import org.w3c.dom.Text;

public class Registro_AsistenciaViewHolder extends RecyclerView.ViewHolder{


    public TextView txtIndex;
    public TextView txtAsistencia;
    public TextView txtNombre;

    public TextView txtHora;


    public Registro_AsistenciaViewHolder(View itemView) {

        super(itemView);

        this.txtHora=(TextView)itemView.findViewById(R.id.txtFecha);
        this.txtAsistencia= (TextView) itemView.findViewById(R.id.txtAsistencia);
        this.txtNombre=(TextView) itemView.findViewById(R.id.txtNombreAlumno);
        this.txtIndex=(TextView) itemView.findViewById(R.id.txtIndex);

    }

    public void llenar(Registro_Asistencia registro,int index){


        if(registro.getAsistencia().equals("T")){

            txtAsistencia.setTextColor(Color.WHITE);

            txtAsistencia.setBackgroundColor(-500);


        }


        if(registro.getAsistencia().equals("F")){

            txtAsistencia.setTextColor(Color.WHITE);

            txtAsistencia.setBackgroundColor(Color.RED);


        }



        if(registro.getAsistencia().equals("P")){

            txtAsistencia.setTextColor(Color.WHITE);

            txtAsistencia.setBackgroundColor(Color.GREEN);


        }


        if(registro.getAsistencia().equals("FJ")){

            txtAsistencia.setTextColor(Color.WHITE);

            txtAsistencia.setBackgroundColor(Color.BLUE);


        }



        txtIndex.setText(String.valueOf(index));


        txtAsistencia.setText(registro.getAsistencia());

        txtHora.setText(registro.getHora());

        txtNombre.setText(""+registro.getAlumno().getNombres()+" "+registro.getAlumno().getApellido_paterno()+" " +registro.getAlumno().getApellido_materno());


    }


}
