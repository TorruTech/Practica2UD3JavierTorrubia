package com.javiertp.base.mvc;

import com.javiertp.base.*;
import com.javiertp.base.util.HibernateUtil;

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

    public List<Evento> obtenerEventosDisponiblesPorUsuario(Usuario usuario) {
        // Obtener todos los eventos disponibles para el usuario
        List<Evento> eventos = new ArrayList<>(obtenerEventos());

        List<Evento> eventosInscritos = usuario.getInscripciones().stream()
                .map(Inscripcion::getEvento)
                .collect(Collectors.toList());

        eventos.removeAll(eventosInscritos);
        return eventos;
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
        List<Evento> eventos = new ArrayList<>(obtenerEventos());

        List<Evento> eventosOrganizador = organizador.getEventos();

        eventos.removeAll(eventosOrganizador);
        return eventos;
    }


}
