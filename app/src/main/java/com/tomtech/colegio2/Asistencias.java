package com.tomtech.colegio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tomtech.colegio2.adapter.Registro_AsistenciaAdapter;
import com.tomtech.colegio2.model.Grado;
import com.tomtech.colegio2.model.Registro_Asistencia;
import com.tomtech.colegio2.network.ApiClient;
import com.tomtech.colegio2.network.ApiGrado;
import com.tomtech.colegio2.network.ApiRegistro;
import com.tomtech.colegio2.ui.dialog.DatePickerFragment;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Asistencias extends AppCompatActivity {

    private Button btnRegistroQr;
    private List<Registro_Asistencia> registro_asistencias;

    public List<Grado>grados=null;
    private boolean isButtonEnabled = true;
    EditText editDate;


    public Spinner spGrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencias);

        btnRegistroQr = findViewById(R.id.btnQR);
        btnRegistroQr.setEnabled(isButtonEnabled);
        spGrados = findViewById(R.id.spGrados);
        editDate= (EditText) findViewById(R.id.editTextDate);


        initData();
        initListener();

    }

    public void initRecyclerView(List<Registro_Asistencia> registroAsistencias) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        if(registroAsistencias!=null){

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Registro_AsistenciaAdapter(registroAsistencias));

        }

        else{

            recyclerView.setAdapter(null);
        }


    }




    public void initData(){



        llenarSelect();
        llenarFecha();

        llenarDatos();


    }


    public void initListener(){


        editDate.addTextChangedListener(onChangeText());
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

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final String selectedDate = year+"-"+ (month+1)+ "-"+day;
        editDate.setText(selectedDate);

    }



    public void showDialogDatePicker(){

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                final String selectedDate = year+"-"+ (month+1)+ "-"+day;
                editDate.setText(selectedDate);

            }
        });
        newFragment.show(this.getSupportFragmentManager(),"datePicker");
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
                    grados = response.body();

                    if (grados != null && !grados.isEmpty()) {

                        ArrayAdapter<Grado> adapter = new ArrayAdapter<>(Asistencias.this, android.R.layout.simple_spinner_dropdown_item,grados);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spGrados.setAdapter(adapter);








                    }
                }
            }

            @Override
            public void onFailure(Call<List<Grado>> call, Throwable t) {

            }
        });







    }

    public void llenarDatos() {
        Call<List<Registro_Asistencia>> call = ApiClient.getClient().create(ApiRegistro.class).getAsistencia(editDate.getText().toString(),String.valueOf(spGrados.getSelectedItemId()+1));

        call.enqueue(new Callback<List<Registro_Asistencia>>() {
            @Override
            public void onResponse(Call<List<Registro_Asistencia>> call, Response<List<Registro_Asistencia>> response) {
                if (response.isSuccessful()) {
                    registro_asistencias = response.body();

                    if (registro_asistencias != null && !registro_asistencias.isEmpty()) {
                        initRecyclerView(registro_asistencias);

                        for (Registro_Asistencia registro_asistencia : registro_asistencias) {
                            Toast.makeText(Asistencias.this, "Alumno "
                                            + registro_asistencia.getAlumno().getNombres()
                                            + " llegó a las " + registro_asistencia.getHora() + " eso es " + registro_asistencia.getAsistencia(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{

                        Toast.makeText(Asistencias.this, "No hay ningun registro",
                                Toast.LENGTH_SHORT).show();
                        initRecyclerView(null);

                    }


                }
            }

            @Override
            public void onFailure(Call<List<Registro_Asistencia>> call, Throwable t) {
                // Manejar el error de la petición
            }
        });



    }



    public void MostrarQr(View view) {
        if (isButtonEnabled) {
            isButtonEnabled = false;
            btnRegistroQr.setEnabled(isButtonEnabled);
            Intent intent = new Intent(this, registo_qr.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnRegistroQr.setEnabled(true);
    }




}
