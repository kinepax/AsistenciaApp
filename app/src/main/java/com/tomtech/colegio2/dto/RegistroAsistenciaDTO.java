package com.tomtech.colegio2.dto;

public class RegistroAsistenciaDTO {

    private int idAlumno;
    private String asistencia;
    private int idMatricula;
    private String strfecha;
    private String estado;

    public RegistroAsistenciaDTO(int idAlumno, String asistencia, int idMatricula, String strfecha, String estado) {
        this.idAlumno = idAlumno;
        this.asistencia = asistencia;
        this.idMatricula = idMatricula;
        this.strfecha = strfecha;
        this.estado = estado;
    }

    public RegistroAsistenciaDTO() {
    }


    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getStrfecha() {
        return strfecha;
    }

    public void setStrfecha(String strfecha) {
        this.strfecha = strfecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {this.estado = estado;}
}