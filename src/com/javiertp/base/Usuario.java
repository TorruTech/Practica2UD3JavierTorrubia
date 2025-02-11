package com.javiertp.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios", schema = "eventoshibernate", catalog = "")
public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private Date fechaRegistro;
    private List<Inscripcion> inscripciones;
    private List<Valoracion> valoraciones;

    public Usuario() {

    }

    public Usuario(String nombre, String apellidos, String email, Date fechaRegistro) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
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
    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "fecha_registro")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id &&
                Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(apellidos, usuario.apellidos) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(fechaRegistro, usuario.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, email, fechaRegistro);
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " " + email + " " + fechaRegistro;
    }

    public void desinscribirse(Evento evento) {
        Iterator<Inscripcion> iterator = inscripciones.iterator();

        while (iterator.hasNext()) {
            Inscripcion inscripcion = iterator.next();
            if (inscripcion.getEvento().equals(evento)) {
                iterator.remove();  // Elimina de manera segura el elemento de la lista
                break;  // No es necesario seguir buscando una vez que se desinscribe el usuario
            }
        }
    }

}
