package com.tomtech.colegio2.dto;

import android.widget.Spinner;

public class AsistenciaFaltanteDTO {

    private int idAlumno;
    private int idMatricula;
    private String nombreAlumno;



    private Spinner spAsistencias;


    public AsistenciaFaltanteDTO(int idAlumno, int idMatricula, String nombreAlumno) {
        this.idAlumno = idAlumno;
        this.idMatricula = idMatricula;
        this.nombreAlumno = nombreAlumno;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Spinner getSpAsistencias() {
        return spAsistencias;
    }

    public void setSpAsistencias(Spinner spAsistencias) {
        this.spAsistencias = spAsistencias;
    }
}
