package com.tomtech.colegio2.activity.Asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tomtech.colegio2.R;
import com.tomtech.colegio2.adapter.Asistencia_FaltanteAdapter;
import com.tomtech.colegio2.adapter.ListenerItemAsistenciaFaltante;
import com.tomtech.colegio2.dto.AsistenciaFaltanteDTO;
import com.tomtech.colegio2.dto.RegistroAsistenciaDTO;
import com.tomtech.colegio2.network.ApiAsistenciaFaltanteDTO;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiRegistrarAsistenciaDTO;
import com.tomtech.colegio2.ui.dialog.DialogComponent;
import com.tomtech.colegio2.ui.dialog.DialogListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsistenciaActivity extends AppCompatActivity implements ListenerItemAsistenciaFaltante, DialogListener {
    TextView txtFecha, txtGrado;

    LocalDate localDate;
    Button btnGuardar, btnCancelar;
    List<AsistenciaFaltanteDTO> listaAsistenciaFaltante;
    RecyclerView recyclerAlumnos;
    List<RegistroAsistenciaDTO> listaRegistroAsistenciaDTO = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        recyclerAlumnos = findViewById(R.id.recyclerAlumnos);

        txtFecha = findViewById(R.id.txtFecha);
        txtGrado = findViewById(R.id.txtGrado);


        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);


        //Recibe los siguientes parametros de AsistenciasActivity
        Bundle bundle = getIntent().getExtras();


        txtFecha.setText(bundle.getString("fecha"));
        localDate = (LocalDate) bundle.get("localDate");
        txtGrado.setText(bundle.getString("grado"));

        initData();

    }


    public void initData() {


        llenarDatos();


    }

    public void initRecyclerView(List<AsistenciaFaltanteDTO> asistenciaFaltantes) {


        if (asistenciaFaltantes != null) {

            recyclerAlumnos.setLayoutManager(new LinearLayoutManager(this));
            recyclerAlumnos.setAdapter(new Asistencia_FaltanteAdapter(asistenciaFaltantes, this));
            recyclerAlumnos.getAdapter().notifyDataSetChanged();

        } else {
            recyclerAlumnos.setAdapter(null);
        }


    }

    public void llenarDatos() {
        btnGuardar.setEnabled(false);
        //Llamada a a la Asistencia Faltante de la base  de datos
        Call<List<AsistenciaFaltanteDTO>> llamadaAsistenciaFaltante =
                ApiClient.getClient().create(ApiAsistenciaFaltanteDTO.class)
                        .getAsistenciaFaltante
                                (localDate,
                                        txtGrado.getText().toString()
                                );

        llamadaAsistenciaFaltante.enqueue(new Callback<List<AsistenciaFaltanteDTO>>() {
                                              @Override
                                              public void onResponse(Call<List<AsistenciaFaltanteDTO>> llamada, Response<List<AsistenciaFaltanteDTO>> respuesta) {
                                                  //Si hay una respuesta no importa si es vacio
                                                  if (respuesta.isSuccessful()) {
                                                      listaAsistenciaFaltante = respuesta.body();

                                                      //Comprobar la Asistencia si es difente de nula
                                                      if (listaAsistenciaFaltante != null && !listaAsistenciaFaltante.isEmpty()) {
                                                          btnGuardar.setEnabled(true);
                                                          initRecyclerView(listaAsistenciaFaltante);
                                                      } else {

                                                          Toast.makeText(
                                                                  AsistenciaActivity.this,
                                                                  "No hay ningun registro",
                                                                  Toast.LENGTH_SHORT
                                                          ).show();

                                                          btnGuardar.setEnabled(false);

                                                          //El reciclerView no tendra nada
                                                          initRecyclerView(null);

                                                      }


                                                  }
                                              }

                                              @Override
                                              public void onFailure(Call<List<AsistenciaFaltanteDTO>> call, Throwable t) {
                                                  // En caso de error que hacer
                                              }
                                          }

        );


    }


    public void guardarAsistencia(View view) {
        DialogComponent.listener = this;
        DialogComponent.createDialog(view).show();


    }


    public void cerrarAsistencia(View view) {

        finish();
    }


    @Override
    public void onEliminarClick(int position) {

        if (position >= 0 && position < listaAsistenciaFaltante.size()) {
            listaAsistenciaFaltante.remove(position);
            recyclerAlumnos.getAdapter().notifyItemRemoved(position);

            // Verifica si el tamaño de la lista es 0 después de la eliminación
            if (recyclerAlumnos.getAdapter().getItemCount() == 0) {
                Toast.makeText(AsistenciaActivity.this,
                        "El tamaño de la lista ahora es 0", Toast.LENGTH_SHORT).show();
            }
        }
    }




        @Override
        public void onConfirm() {
            if (listaAsistenciaFaltante.size() != 0) {
                String fechaFormateada = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                btnGuardar.setEnabled(false);

                // Inicializar i con 1
                for (AsistenciaFaltanteDTO asistencia : listaAsistenciaFaltante) {
                    listaRegistroAsistenciaDTO.add(
                            new RegistroAsistenciaDTO(
                                    // Usar el valor de i como id
                                    asistencia.getIdAlumno(),
                                    asistencia.getSpAsistencias().getSelectedItem().toString(),
                                    asistencia.getIdMatricula(),
                                    fechaFormateada,
                                    "E"
                            )
                    );
                 //   asistencia.setEstado("E");
                    // Incrementar i para la próxima iteración
                }


                Call<Void> call = ApiClient.getClient().create(ApiRegistrarAsistenciaDTO.class).
                        setAsistenciaFaltante2(listaRegistroAsistenciaDTO);


                //Pruebas despues hay que quitar
                String jsonAEnviar = new Gson().toJson(listaRegistroAsistenciaDTO);
                Log.d("JSONN A ENVIAR ", jsonAEnviar);


                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // La respuesta exitosa solo tiene código 200 OK, no necesitas verificar el contenido
                        if (response.isSuccessful()) {
                            // La respuesta exitosa solo tiene código 200 OK, no necesitas verificar el contenido
                            Toast.makeText(AsistenciaActivity.this, "GRABADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AsistenciaActivity.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AsistenciaActivity.this, "Error en la comunicación con el servidor", Toast.LENGTH_SHORT).show();
                        System.out.println("-------------EL ERROR ES " + t);
                    }


                });


            } else {
                Toast.makeText(AsistenciaActivity.this, "No hay ningun registro para grabar", Toast.LENGTH_SHORT).show();
                btnGuardar.setEnabled(false);

            }
        }

    @Override
    public void onNegative() {
        btnGuardar.setEnabled(true);
    }
}