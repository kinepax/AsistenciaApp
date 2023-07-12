package com.tomtech.colegio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tomtech.colegio2.model.Registro_Asistencia;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiRegistrarAsistencia;
import com.tomtech.colegio2.network.ApiRegistro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registo_qr extends AppCompatActivity {

    public static List<String> textos;
    public static TextView eTextos;
    public Button btnScanner;
    private boolean isButtonEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_qr);

        eTextos = findViewById(R.id.txtEscaneado);
        btnScanner = findViewById(R.id.btnEscanear);
        btnScanner.setEnabled(isButtonEnabled);
    }

    public static void llenar(String nombre_alumno) {

        eTextos.setText(nombre_alumno);


            Call<List<String>> call = ApiClient.getClient().create(ApiRegistrarAsistencia.class).setAsistencia(Integer.parseInt(nombre_alumno));

            call.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if (response.isSuccessful()) {








                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    // Manejar el error de la petición
                }
            });



    }


    public void MostrarQr(View view) {
        if (isButtonEnabled) {
            isButtonEnabled = false;
            btnScanner.setEnabled(isButtonEnabled);
            Intent intent = new Intent(this, Lector_QR.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        isButtonEnabled=true;
        btnScanner.setEnabled(isButtonEnabled);

    }

}
