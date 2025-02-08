package com.javiertp.base.mvc;

import com.javiertp.base.Evento;
import com.javiertp.base.Usuario;
import com.javiertp.base.Organizador;
import com.javiertp.base.Inscripcion;
import com.javiertp.base.util.HibernateUtil;

import java.util.List;

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

}
