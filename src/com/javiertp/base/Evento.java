package com.javiertp.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "eventos", schema = "eventoshibernate", catalog = "")
public class Evento {
    private int id;
    private String nombre;
    private String ubicacion;
    private Date fechaInicio;
    private float precio;
    private Organizador organizador;
    private List<Inscripcion> inscripciones;

    public Evento() {

    }

    public Evento(String nombre, String ubicacion, Date fechaInicio, float precio, Organizador organizador) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.precio = precio;
        this.organizador = organizador;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ubicacion")
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Basic
    @Column(name = "fecha_inicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "precio")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id == evento.id &&
                Float.compare(evento.precio, precio) == 0 &&
                Objects.equals(nombre, evento.nombre) &&
                Objects.equals(ubicacion, evento.ubicacion) &&
                Objects.equals(fechaInicio, evento.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, ubicacion, fechaInicio, precio);
    }

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @ManyToOne
    @JoinColumn(name = "id_organizador", referencedColumnName = "id", nullable = true)
    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    @Override
    public String toString() {
        return nombre + " " + ubicacion + " " + fechaInicio + " " + precio + " " + (organizador == null ? "" : organizador.getNombre());
    }

    public Inscripcion getInscripcion(Usuario usuarioDesinscribir) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getUsuario().equals(usuarioDesinscribir)) {
                return inscripcion;
            }
        }
        return null;
    }
}
