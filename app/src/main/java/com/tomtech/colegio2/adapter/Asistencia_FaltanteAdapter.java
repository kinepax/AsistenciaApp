package com.tomtech.colegio2.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomtech.colegio2.R;
import com.tomtech.colegio2.dto.AsistenciaFaltanteDTO;
import com.tomtech.colegio2.model.AsistenciaFaltante;

import java.util.List;

public class Asistencia_FaltanteAdapter extends RecyclerView.Adapter<Asistencia_FaltanteViewHolder> {
     List<AsistenciaFaltanteDTO> asistenciaFaltantes;

    private ListenerItemAsistenciaFaltante listener;



    public Asistencia_FaltanteAdapter(List<AsistenciaFaltanteDTO> asistenciaFaltantes, ListenerItemAsistenciaFaltante listener ){

         this.asistenciaFaltantes =asistenciaFaltantes;
         this.listener=listener;

    }


    @NonNull
    @Override
    public Asistencia_FaltanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflado = LayoutInflater.from(parent.getContext());
        return new Asistencia_FaltanteViewHolder(layoutInflado.inflate(R.layout.item_asistencia,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull Asistencia_FaltanteViewHolder holder, int position) {

       AsistenciaFaltanteDTO dataRegistros= asistenciaFaltantes.get(position);
       // holder.llenar(dataRegistros);

        holder.llenar(dataRegistros);
        holder.setListener(new ListenerItemAsistenciaFaltante() {
            @Override
            public void onEliminarClick(int position) {

                eliminarItem(position);
            }
        });



    }


    private void eliminarItem(int position){

        if(position>=0 && position<asistenciaFaltantes.size()){

            asistenciaFaltantes.remove(position);
            notifyItemRemoved(position);


        }

    }


    @Override
    public int getItemCount() {
        return asistenciaFaltantes.size();
    }


}
