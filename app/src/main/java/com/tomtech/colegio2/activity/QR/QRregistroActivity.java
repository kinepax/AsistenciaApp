package com.tomtech.colegio2.activity.QR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tomtech.colegio2.R;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiRegistrarAsistenciaDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRregistroActivity extends AppCompatActivity {

    public static TextView eTextos;
    public Button btnScanner;
    private boolean isButtonEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_registro);

        eTextos = findViewById(R.id.txtEscaneado);
        btnScanner = findViewById(R.id.btnEscanear);
        btnScanner.setEnabled(isButtonEnabled);
    }

    public static void llenar(String nombre_alumno) {

        eTextos.setText(nombre_alumno);


            Call<List<String>> call = ApiClient.getClient().create(ApiRegistrarAsistenciaDTO.class).setAsistencia(Integer.parseInt(nombre_alumno));

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
            Intent intent = new Intent(this, QRLectorActivity.class);
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
