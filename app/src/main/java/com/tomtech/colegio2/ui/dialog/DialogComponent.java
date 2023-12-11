package com.tomtech.colegio2.ui.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

public class DialogComponent  {

    public static DialogListener listener;

    public  static AlertDialog createDialog(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Grabar Registro")
                .setMessage("¿Esta seguro de grabar?")
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(view.getContext(),"Diste al ok",Toast.LENGTH_SHORT).show();
                                listener.onConfirm();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(view.getContext(),"Diste al cancelar",Toast.LENGTH_SHORT).show();
                                listener.onNegative();

                            }
                        });



            return builder.create();



    }

    public   void setDialogListener(DialogListener listener){
        this.listener=listener;
    }


}
