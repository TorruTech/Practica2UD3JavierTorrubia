package com.javiertp.base;

import javax.persistence.*;

@Entity
@Table(name = "valoraciones")
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_organizador")
    private Organizador organizador;

    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;

    @Column(name = "comentario")
    private String comentario;

    // Constructor vacío
    public Valoracion() {}

    // Constructor con parámetros
    public Valoracion(Usuario usuario, Evento evento, Organizador organizador, int puntuacion, String comentario) {
        this.usuario = usuario;
        this.evento = evento;
        this.organizador = organizador;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Valoracion{" +
                "id=" + id +
                ", usuario=" + usuario.getNombre() +
                ", evento=" + (evento != null ? evento.getNombre() : "N/A") +
                ", organizador=" + (organizador != null ? organizador.getNombre() : "N/A") +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
