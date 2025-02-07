package com.javiertp.base.mvc;

import com.elenajif.colegio.base.Alumno;
import com.elenajif.colegio.base.Asignatura;
import com.elenajif.colegio.base.Profesor;
import com.elenajif.colegio.util.HibernateUtil;

import java.util.List;

public class Modelo {
    public void conectar(){
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();
    }

    public void desconectar(){
        HibernateUtil.closeSessionFactory();
    }

    public List<Alumno> obtenerAlumnos(){
        return HibernateUtil.getCurrentSession().createQuery("FROM Alumno").getResultList();
    }

    public List<Asignatura> obtenerAsignaturas() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Asignatura").getResultList();
    }

    public List<Profesor> obtenerProfesores() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Profesor").getResultList();
    }

    public List<Alumno> obtenerAlumnosDisponiblesParaAsignatura(Asignatura asignatura) {
        //Elimino a los alumnos que ya tiene matriculados esa asignatura
        List<Alumno> lista = obtenerAlumnos();
        lista.removeAll(asignatura.getAlumnos());
        return lista;
    }

    public List<Asignatura> obtenerAsignaturasDisponiblesParaAlumno(Alumno alumno) {
        //Elimino las asignaturas en las que ya esta matriculado ese alumno
        List<Asignatura> lista = obtenerAsignaturas();
        lista.removeAll(alumno.getAsignaturas());
        return lista;
    }

    public List<Asignatura> obtenerAsignaturasDisponiblesParaProfesor(Profesor profesor) {
        List<Asignatura> lista = obtenerAsignaturas();
        lista.removeAll(profesor.getAsignaturas());
        return lista;
    }

    public void guardarAlumno(Alumno alumno) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(alumno);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void guardarAsignatura(Asignatura asignatura) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(asignatura);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void guardarProfesor(Profesor profesor){
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(profesor);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(asignatura);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarAlumno(Alumno alumno) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(alumno);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarProfesor(Profesor profesor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(profesor);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }
}
