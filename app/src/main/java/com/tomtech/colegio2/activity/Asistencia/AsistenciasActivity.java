package com.tomtech.colegio2.activity.Asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.tomtech.colegio2.R;
import com.tomtech.colegio2.activity.QR.QRregistroActivity;
import com.tomtech.colegio2.adapter.Registro_AsistenciaAdapter;
import com.tomtech.colegio2.model.Grado;
import com.tomtech.colegio2.model.Registro_Asistencia;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiGrado;
import com.tomtech.colegio2.network.ApiRegistro;
import com.tomtech.colegio2.ui.dialog.MaterialPickerFragment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class AsistenciasActivity extends AppCompatActivity {

    private Button btnRegistroQr;
    private List<Registro_Asistencia> listaRegistroAsistencia;
    public List<Grado> listaGrados =null;
    private boolean isButtonEnabled = true;
    EditText eFecha;

    LocalDate fechaLocal;
    public Spinner spGrados;


    public Calendar min_date;
    public Calendar max_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencias);

        btnRegistroQr = findViewById(R.id.btnQR);
        btnRegistroQr.setEnabled(isButtonEnabled);
        spGrados = findViewById(R.id.spGrados);
        eFecha = (EditText) findViewById(R.id.editTextDate);


        initData();
        initListener();

    }

    public void initRecyclerView(List<Registro_Asistencia> registroAsistencias) {
        RecyclerView recyclerAsistencias = findViewById(R.id.recyclerAsistencias);

        if(registroAsistencias!=null){

            recyclerAsistencias.setLayoutManager(new LinearLayoutManager(this));
            recyclerAsistencias.setAdapter(new Registro_AsistenciaAdapter(registroAsistencias));

        }

        else{

            recyclerAsistencias.setAdapter(null);
        }


    }




    public void initData(){


        llenarFecha();
        llenarSelect();






    }




    public void initListener(){


        eFecha.addTextChangedListener(onChangeText());
        spGrados.setOnItemSelectedListener(onItemSelectedListener());

    }


    public AdapterView.OnItemSelectedListener onItemSelectedListener(){
        AdapterView.OnItemSelectedListener listener =

        new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                llenarDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        return listener;
    }


    public TextWatcher onChangeText() {

        TextWatcher textWatcher;
        textWatcher= new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                llenarDatos();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };





        return textWatcher;
    };





    public void llenarFecha(){




        fechaLocal=LocalDate.now();


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());

// Formatea la fecha y obtén la cadena formateada
        String selectedDate = fechaLocal.format(dateFormatter);

// Establece la cadena de fecha formateada en el campo eFecha
        eFecha.setText(selectedDate);
    }



    public void showDialogDatePicker() {
        // Obtén la zona horaria actual
        ZoneId zonaHoraria = ZoneId.systemDefault();

        // Crea un MaterialDatePicker con la zona horaria configurada
        MaterialDatePicker<Long> customDatePicker = MaterialPickerFragment.createCustomDatePicker(fechaLocal, zonaHoraria);
        customDatePicker.show(getSupportFragmentManager(), "customDatePicker");

        customDatePicker.addOnPositiveButtonClickListener(selection -> {
            // Obtén la fecha seleccionada como un objeto Date
            Date selectedDate = new Date(selection);



            // Convierte la fecha seleccionada a un objeto LocalDateTime en la zona horaria UTC
            LocalDateTime utcLocalDateTime = selectedDate.toInstant().atZone(ZoneId.ofOffset("UTC", ZoneOffset.UTC)).toLocalDateTime();

            // Convierte la fecha UTC a la zona horaria de la aplicación
            LocalDateTime localDateTime = utcLocalDateTime.atZone(ZoneId.systemDefault()).toLocalDateTime();

            fechaLocal=localDateTime.toLocalDate();


            // Formatea la fecha en el formato local de la aplicación
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = localDateTime.format(dateFormatter);

            // Establece la fecha formateada en el campo eFecha
            eFecha.setText(formattedDate);
        });
    }

    public void onClickDate(View view){

        showDialogDatePicker();

    }


    public void llenarSelect(){




        Call<List<Grado>> call = ApiClient.getClient().create(ApiGrado.class).getGrados();

        call.enqueue(new Callback<List<Grado>>() {
            @Override
            public void onResponse(Call<List<Grado>> call, Response<List<Grado>> response) {
                if (response.isSuccessful()) {
                    listaGrados = response.body();

                    if (listaGrados != null && !listaGrados.isEmpty()) {

                        ArrayAdapter<Grado> adapter = new ArrayAdapter<>(AsistenciasActivity.this, android.R.layout.simple_spinner_dropdown_item, listaGrados);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spGrados.setAdapter(adapter);



                        llenarDatos();





                    }
                }
            }

            @Override
            public void onFailure(Call<List<Grado>> call, Throwable t) {

            }
        });







    }

    public void llenarDatos() {



        Call<List<Registro_Asistencia>> llamadaRegistroAsistencia =
          ApiClient.getClient().create(ApiRegistro.class).
                  getAsistencia(fechaLocal,
                          String.valueOf(spGrados.getSelectedItem().toString())

                  );





        Log.d("SolicitudHTTP", "URL: " + llamadaRegistroAsistencia.request().url());
        Log.d("SolicitudHTTP", "Método: " + llamadaRegistroAsistencia.request().method());

        if (llamadaRegistroAsistencia.request().body() != null) {
            try {
                Buffer buffer = new Buffer();
                llamadaRegistroAsistencia.request().body().writeTo(buffer);
                String requestBody = buffer.readUtf8();
                Log.d("SolicitudHTTP", "Cuerpo de la solicitud: " + requestBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }






        llamadaRegistroAsistencia.enqueue(new Callback<List<Registro_Asistencia>>() {
            @Override
            public void onResponse(Call<List<Registro_Asistencia>> llamada, Response<List<Registro_Asistencia>> respuesta) {
                if (respuesta.isSuccessful()) {
                    listaRegistroAsistencia = respuesta.body();

                    Log.d("RespuestaHTTP", "Cuerpo codigo: " + respuesta.code());
                    Log.d("RespuestaHTTP", "Cuerpo de la respuesta: " + respuesta.body());



                    if (listaRegistroAsistencia != null && !listaRegistroAsistencia.isEmpty()) {
                        initRecyclerView(listaRegistroAsistencia);

                       }
                    else{

                        Toast.makeText(AsistenciasActivity.this, "No hay ningun registro",
                                Toast.LENGTH_SHORT).show();
                        initRecyclerView(null);

                    }


                }
            }

            @Override
            public void onFailure(Call<List<Registro_Asistencia>> call, Throwable t) {
                Log.d("RespuestaHTTP", "Cuerpo del error: " + t.getMessage());
            }
        });



    }



    public void MostrarQr(View view) {
        if (isButtonEnabled) {
            isButtonEnabled = false;
            btnRegistroQr.setEnabled(isButtonEnabled);
            Intent intent = new Intent(this, QRregistroActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isButtonEnabled=true;
        btnRegistroQr.setEnabled(true);
        llenarSelect();
    }


    public void nuevaAsistencia(View view){
        Intent intent = new Intent(this, AsistenciaActivity.class);
        intent.putExtra("localDate", fechaLocal);

        intent.putExtra("fecha", eFecha.getText().toString());
        intent.putExtra("grado",spGrados.getSelectedItem().toString());
        startActivity(intent);

    }





}
