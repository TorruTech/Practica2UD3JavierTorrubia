package com.javiertp.base;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "inscripciones", schema = "eventoshibernate", catalog = "")
public class Inscripcion {
    private int id;
    private Date fechaInscripcion;
    private String estado;
    private Evento evento;
    private Usuario usuario;

    public Inscripcion() {}

    public Inscripcion(Date fechaInscripcion, String estado, Evento evento, Usuario usuario) {
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
        this.evento = evento;
        this.usuario = usuario;
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
    @Column(name = "fecha_inscripcion")

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscripcion that = (Inscripcion) o;
        return id == that.id &&
                Objects.equals(fechaInscripcion, that.fechaInscripcion) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInscripcion, estado);
    }

    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "id", nullable = false)
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return evento.getNombre() + " " + usuario.getNombre() + " " + fechaInscripcion + " " + estado;
    }
}
