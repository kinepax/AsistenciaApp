package com.tomtech.colegio2.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.tomtech.colegio2.R;
import com.tomtech.colegio2.model.Registro_Asistencia;

import java.util.List;

public class Registro_AsistenciaAdapter extends RecyclerView.Adapter<Registro_AsistenciaViewHolder> {
     List<Registro_Asistencia> registrosAsistencia;




    public  Registro_AsistenciaAdapter(List<Registro_Asistencia> data){

         this.registrosAsistencia=data;


    }


    @NonNull
    @Override
    public Registro_AsistenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new Registro_AsistenciaViewHolder(layoutInflater.inflate(R.layout.item_registro_asistencia,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull Registro_AsistenciaViewHolder holder, int position) {

        Registro_Asistencia dataRegistros= registrosAsistencia.get(position);
        holder.llenar(dataRegistros);


    }

    @Override
    public int getItemCount() {
        return registrosAsistencia.size();
    }
}
