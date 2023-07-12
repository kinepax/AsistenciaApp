package com.tomtech.colegio2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.tomtech.colegio2.model.Usuario;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiUsuario;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText eTextUser, eTextPassContra;
    private List<Usuario> usuarios;
    private boolean isLoginEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTextUser = findViewById(R.id.editTextUsuario);
        eTextPassContra = findViewById(R.id.editTextPWContra);

    }

    public void InicioSesion(View view) {
        String usuario = eTextUser.getText().toString();
        String contraseña = eTextPassContra.getText().toString();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            if (isLoginEnabled) {
                isLoginEnabled = false;
                // Deshabilitar el botón de inicio de sesión para evitar múltiples clics
                view.setEnabled(isLoginEnabled);

                Call<List<Usuario>> call = ApiClient.getClient().create(ApiUsuario.class).getUsuario(usuario, contraseña);

                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if (response.isSuccessful()) {
                            usuarios = response.body();

                            if (usuarios != null && !usuarios.isEmpty()) {
                                for (Usuario usuario : usuarios) {
                                    Toast.makeText(MainActivity.this, "Bienvenido " + usuario.getUsuario(), Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(MainActivity.this, Asistencias.class);
                                startActivity(intent);
                                finish(); // Terminar la actividad actual para que no se pueda volver atrás
                            } else {
                                Toast.makeText(MainActivity.this, "No se encontró ese usuario", Toast.LENGTH_SHORT).show();
                                // Habilitar el botón de inicio de sesión nuevamente
                                isLoginEnabled = true;
                                view.setEnabled(isLoginEnabled);
                            }
                        } else {
                            // La solicitud no fue exitosa, muestra el mensaje de error proporcionado por la API
                            String error = "";
                            try {
                                error = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                            // Habilitar el botón de inicio de sesión nuevamente
                            isLoginEnabled = true;
                            view.setEnabled(isLoginEnabled);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                        // Habilitar el botón de inicio de sesión nuevamente
                        isLoginEnabled = true;
                        view.setEnabled(isLoginEnabled);
                    }
                });
            }
        } else {
            // Al menos uno de los campos está vacío, muestra un mensaje de error o realiza alguna otra acción
            Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
