package com.javiertp.base.mvc;

import com.javiertp.base.*;
import com.javiertp.base.util.HibernateUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Modelo {


    /**
     * Establishes a connection to the database by building the session factory
     * and opening a new session using Hibernate utilities.
     */
    public void conectar() {
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();
    }

    /**
     * Closes the current session and destroys the session factory.
     */
    public void desconectar() {
        HibernateUtil.closeSessionFactory();
    }


    /**
     * Returns a list of all users in the database.
     * @return list of all users in the database
     */
    public List<Usuario> obtenerUsuarios() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Usuario").getResultList();
    }


    /**
     * Retrieves a list of all events from the database.
     *
     * @return a list of Evento objects representing all events in the database
     */
    public List<Evento> obtenerEventos() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Evento").getResultList();
    }


    /**
     * Retrieves a list of all Organizador objects from the database.
     *
     * @return a list of Organizador objects representing all organizers in the database
     */
    public List<Organizador> obtenerOrganizadores() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Organizador").getResultList();
    }

    /**
     * Retrieves a list of all {@link Inscripcion} objects from the database.
     *
     * @return a list of {@link Inscripcion} objects representing all inscriptions in the database
     */
    public List<Inscripcion> obtenerInscripciones() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Inscripcion").getResultList();
    }

    /**
     * Retrieves a list of all {@link Valoracion} objects from the database.
     *
     * @return a list of {@link Valoracion} objects representing all ratings in the database
     */
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

    /**
     * Retrieves a list of users who are not registered for a given event.
     *
     * @param evento the event for which to find available users
     * @return a list of Usuario objects representing users not registered for the specified event
     */
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

    /**
     * Retrieves a list of events that are not already assigned to the given organizer.
     *
     * @param organizador the organizer for which to find available events
     * @return a list of Evento objects representing events not already assigned to the specified organizer
     */
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

    /**
     * Removes the association between the given event and its associated organizer.
     *
     * @param eventoDesvincular the event to be disassociated from its current organizer
     */
    public void desvincularOrganizador(Evento eventoDesvincular) {
        HibernateUtil.getCurrentSession().beginTransaction();
        eventoDesvincular.setOrganizador(null);
        HibernateUtil.getCurrentSession().update(eventoDesvincular);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }


    /**
     * Assigns the specified organizer to the given event.
     *
     * @param eventoAsignar the event to which the organizer will be assigned
     * @param organizadorAsignar the organizer to be assigned to the event
     */
    public void asignarOrganizador(Evento eventoAsignar, Organizador organizadorAsignar) {
        HibernateUtil.getCurrentSession().beginTransaction();
        eventoAsignar.setOrganizador(organizadorAsignar);
        HibernateUtil.getCurrentSession().update(eventoAsignar);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    /**
     * Deletes the association between the given event and user.
     *
     * @param eventoDesinscribir the event from which the user will be disassociated
     * @param usuarioDesinscribir the user to be disassociated from the event
     */
    public void desapuntarUsuario(Evento eventoDesinscribir, Usuario usuarioDesinscribir) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().createQuery("DELETE FROM Inscripcion i WHERE i.evento = :evento AND i.usuario = :usuario")
                .setParameter("evento", eventoDesinscribir)
                .setParameter("usuario", usuarioDesinscribir)
                .executeUpdate();
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    /**
     * Registers a user to an event. The user is associated with the event as an {@link Inscripcion}, which is persisted to the database.
     *
     * @param eventoInscribir the event to which the user is being registered
     * @param usuarioInscribir the user being registered to the event
     * @param fechaInscripcion the date when the user registered to the event
     * @param estado the state of the registration (e.g. "Pendiente", "Aceptado", etc.)
     */
    public void inscribirUsuario(Evento eventoInscribir, Usuario usuarioInscribir, Date fechaInscripcion, String estado) {
        HibernateUtil.getCurrentSession().beginTransaction();
        Inscripcion inscripcion = new Inscripcion(fechaInscripcion, estado, eventoInscribir, usuarioInscribir);
        HibernateUtil.getCurrentSession().saveOrUpdate(inscripcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }
}
