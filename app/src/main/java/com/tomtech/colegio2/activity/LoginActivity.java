package com.tomtech.colegio2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.tomtech.colegio2.activity.Asistencia.AsistenciasActivity;
import com.tomtech.colegio2.R;
import com.tomtech.colegio2.model.Usuario;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiUsuario;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText eTextUser, eTextPassContra;
    private List<Usuario> listaUsuarios;
    private boolean isLoginEnabled = true;
    private ProgressBar barraDeProgreso ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eTextUser = findViewById(R.id.editTextUsuario);
        eTextPassContra = findViewById(R.id.editTextPWContra);

        barraDeProgreso= findViewById(R.id.barraDeProgreso);

        eTextUser.requestFocus();




        SharedPreferences sharedPreferences= getSharedPreferences("FrancisCollinsLog",Context.MODE_PRIVATE);
        boolean sesionActiva = sharedPreferences.getBoolean("sesionActiva",false);

        if(sesionActiva){

            iniciarSesion();
        }






    }

    public void InicioSesion(View view) {


        String usuario = eTextUser.getText().toString();
        String contraseña = eTextPassContra.getText().toString();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            if (isLoginEnabled) {
                onLoginDisableBtnEtex(view,false);



                Call<List<Usuario>> call = ApiClient.getClient().create(ApiUsuario.class).getUsuario(usuario, contraseña);
                barraDeProgreso.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if (response.isSuccessful()) {
                            listaUsuarios = response.body();

                            if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
                                for (Usuario usuario : listaUsuarios) {
                                    Toast.makeText(LoginActivity.this, "Bienvenido " + usuario.getUsuario(), Toast.LENGTH_SHORT).show();

                                    SharedPreferences sharedPreferences = getSharedPreferences("FrancisCollinsLog", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putBoolean("sesionActiva",true);
                                    editor.apply();




                                }
                                barraDeProgreso.setVisibility(View.INVISIBLE);
                                iniciarSesion();

                            } else {


                                mostrarSnack("Verifique el usuario y contraseña");
                                barraDeProgreso.setVisibility(View.INVISIBLE);
                                onLoginDisableBtnEtex(view,true);

                            }
                        } else {
                            // La solicitud no fue exitosa, muestra el mensaje de error proporcionado por la API
                            String error = "";
                            try {
                                error = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            barraDeProgreso.setVisibility(View.INVISIBLE);

                            mostrarSnack("Error: " + error);

                            // Habilitar el botón de inicio de sesión nuevamente
                            onLoginDisableBtnEtex(view,true);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        mostrarSnack("Error de Conexion "+t.getMessage());
                        barraDeProgreso.setVisibility(View.INVISIBLE);

                        // Habilitar el botón de inicio de sesión nuevamente
                        onLoginDisableBtnEtex(view,true);
                    }
                });
            }
        } else {
            // Al menos uno de los campos está vacío, muestra un mensaje de error o realiza alguna otra acción
            Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


    public void onForgotPassword(View view){
        mostrarSnack("Contacte al administrador del sistema para solicitar un cambio de contraseña");

    }


    public void mostrarSnack(String mensaje){

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),mensaje,Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.RED);
        snackbar.show();


    }


    public void onLoginDisableBtnEtex(View view,Boolean activado){
        isLoginEnabled = activado;
        // Deshabilitar el botón de inicio de sesión para evitar múltiples clics
        view.setEnabled(isLoginEnabled);
        eTextUser.setEnabled(activado);
        eTextPassContra.setEnabled(activado);


    }


    public void iniciarSesion (){

        Intent intent = new Intent(LoginActivity.this, AsistenciasActivity.class);
        startActivity(intent);
        finish(); // Terminar la actividad actual para que no se pueda volver atrás
    }

}
