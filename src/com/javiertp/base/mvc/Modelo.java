package com.javiertp.base.mvc;

import com.javiertp.base.*;
import com.javiertp.base.util.HibernateUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Modelo {

    public void conectar() {
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();
    }

    public void desconectar() {
        HibernateUtil.closeSessionFactory();
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Usuario").getResultList();
    }

    // Obtener todos los eventos
    public List<Evento> obtenerEventos() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Evento").getResultList();
    }

    // Obtener todos los organizadores
    public List<Organizador> obtenerOrganizadores() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Organizador").getResultList();
    }

    // Obtener todos las inscripciones
    public List<Inscripcion> obtenerInscripciones() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Inscripcion").getResultList();
    }

    // Obtener todas las valoraciones
    public List<Valoracion> obtenerValoraciones() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Valoracion").getResultList();
    }

    // Obtener inscripciones de un usuario
    public List<Inscripcion> obtenerInscripcionesPorUsuario(Usuario usuario) {
        return HibernateUtil.getCurrentSession()
                .createQuery("FROM Inscripcion WHERE usuario = :usuario")
                .setParameter("usuario", usuario)
                .getResultList();
    }

    // Obtener inscripciones de un evento
    public List<Inscripcion> obtenerInscripcionesPorEvento(Evento evento) {
        return HibernateUtil.getCurrentSession()
                .createQuery("FROM Inscripcion WHERE evento = :evento")
                .setParameter("evento", evento)
                .getResultList();
    }

    // Obtener los eventos de un organizador
    public List<Evento> obtenerEventosPorOrganizador(Organizador organizador) {
        return HibernateUtil.getCurrentSession()
                .createQuery("FROM Evento WHERE organizador = :organizador")
                .setParameter("organizador", organizador)
                .getResultList();
    }

    // Guardar o actualizar un usuario
    public void guardarUsuario(Usuario usuario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(usuario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Guardar o actualizar un evento
    public void guardarEvento(Evento evento) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(evento);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Guardar o actualizar un organizador
    public void guardarOrganizador(Organizador organizador) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(organizador);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Guardar o actualizar una inscripción
    public void guardarInscripcion(Inscripcion inscripcion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(inscripcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Guardar o actualizar una valoracion
    public void guardarValoracion(Valoracion valoracion) {

        if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
            HibernateUtil.getCurrentSession().getTransaction().commit();
        }

        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(valoracion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    //Modificar un usuario
    public void modificarUsuario(Usuario usuario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(usuario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    //Modificar un evento
    public void modificarEvento(Evento evento) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(evento);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    //Modificar un organizador
    public void modificarOrganizador(Organizador organizador) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(organizador);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    //Modificar una inscripcion
    public void modificarInscripcion(Inscripcion inscripcion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(inscripcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    //Modificar una valoracion
    public void modificarValoracion(Valoracion valoracion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(valoracion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Eliminar un usuario
    public void eliminarUsuario(Usuario usuario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(usuario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Eliminar un evento
    public void eliminarEvento(Evento evento) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(evento);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Eliminar un organizador
    public void eliminarOrganizador(Organizador organizador) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(organizador);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Eliminar una inscripción
    public void eliminarInscripcion(Inscripcion inscripcion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(inscripcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    // Eliminar una valoracion
    public void eliminarValoracion(Valoracion valoracion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(valoracion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public List<Usuario> obtenerUsuariosDisponiblesPorEvento(Evento evento) {
        // Obtener todos los usuarios registrados
        List<Usuario> usuarios = obtenerUsuarios();

        // Obtener los usuarios inscritos al evento desde la base de datos
        List<Usuario> usuariosInscritos = HibernateUtil.getCurrentSession()
                .createQuery("SELECT i.usuario FROM Inscripcion i WHERE i.evento = :evento", Usuario.class)
                .setParameter("evento", evento)
                .getResultList();

        // Filtrar los usuarios no inscritos
        usuarios.removeAll(usuariosInscritos);
        return usuarios;
    }

    public List<Evento> obtenerEventosDisponiblesPorOrganizador(Organizador organizador) {
        // Obtener todos los eventos disponibles para el organizador
        List<Evento> eventos = obtenerEventos();

        List<Evento> eventosOrganizador = HibernateUtil.getCurrentSession()
                .createQuery("SELECT e FROM Evento e WHERE e.organizador = :organizador", Evento.class)
                .setParameter("organizador", organizador)
                .getResultList();

        // Filtrar los eventos del organizador
        eventos.removeAll(eventosOrganizador);
        return eventos;
    }

    public void desvincularOrganizador(Evento eventoDesvincular) {
        HibernateUtil.getCurrentSession().beginTransaction();
        eventoDesvincular.setOrganizador(null);
        HibernateUtil.getCurrentSession().update(eventoDesvincular);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void asignarOrganizador(Evento eventoAsignar, Organizador organizadorAsignar) {
        HibernateUtil.getCurrentSession().beginTransaction();
        eventoAsignar.setOrganizador(organizadorAsignar);
        HibernateUtil.getCurrentSession().update(eventoAsignar);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void desapuntarUsuario(Evento eventoDesinscribir, Usuario usuarioDesinscribir) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().createQuery("DELETE FROM Inscripcion i WHERE i.evento = :evento AND i.usuario = :usuario")
                .setParameter("evento", eventoDesinscribir)
                .setParameter("usuario", usuarioDesinscribir)
                .executeUpdate();
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void inscribirUsuario(Evento eventoInscribir, Usuario usuarioInscribir, Date fechaInscripcion, String estado) {
        HibernateUtil.getCurrentSession().beginTransaction();
        Inscripcion inscripcion = new Inscripcion(fechaInscripcion, estado, eventoInscribir, usuarioInscribir);
        HibernateUtil.getCurrentSession().saveOrUpdate(inscripcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }
}
