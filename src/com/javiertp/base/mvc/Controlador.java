package com.javiertp.base.mvc;

import com.javiertp.base.Evento;
import com.javiertp.base.Usuario;
import com.javiertp.base.Organizador;
import com.javiertp.base.Inscripcion;
import java.awt.event.*;
import java.sql.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {

        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);

        modelo.conectar();

        refrescarSeccionEventos();
        refrescarSeccionUsuarios();
        refrescarSeccionOrganizadores();
        refrescarSeccionInscripciones();
    }

    // Método para agregar los ActionListener
    private void addActionListener(ActionListener listener){
        vista.nuevoEventoBtn.addActionListener(listener);
        vista.eliminarEventoBtn.addActionListener(listener);
        vista.nuevoUsuarioBtn.addActionListener(listener);
        vista.eliminarUsuarioBtn.addActionListener(listener);
        vista.nuevoOrganizadorBtn.addActionListener(listener);
        vista.nuevaInscripcionBtn.addActionListener(listener);
        vista.eliminarInscripcionBtn.addActionListener(listener);
        vista.eliminarOrganizadorBtn.addActionListener(listener);
        vista.desinscribirUsuarioBtn.addActionListener(listener);
        vista.desvincularEventoOrganizadorBtn.addActionListener(listener);
        vista.desinscribirseBtn.addActionListener(listener);
        vista.inscribirEnEventoBtn.addActionListener(listener);
        vista.inscribirUsuarioEventoBtn.addActionListener(listener);
        vista.asignarEventoOrganizadorBtn.addActionListener(listener);
    }

    // Método para agregar los ListSelectionListener
    private void addListSelectionListener(ListSelectionListener listener){
        vista.listEventos.addListSelectionListener(listener);
        vista.listUsuarios.addListSelectionListener(listener);
        vista.listOrganizadores.addListSelectionListener(listener);
        vista.listInscripciones.addListSelectionListener(listener);
    }

    // Método para agregar el WindowListener
    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();

        switch(comando) {
            // Sección de Eventos
            case "NuevoEvento":
                break;

            case "EliminarEvento":
                // Eliminar el evento seleccionado
                Evento eventoEliminar = vista.listEventos.getSelectedValue();
                modelo.eliminarEvento(eventoEliminar);
                break;

            // Sección de Usuarios
            case "NuevoUsuario":
                break;

            case "EliminarUsuario":
                // Eliminar el usuario seleccionado
                Usuario usuarioEliminar = vista.listUsuarios.getSelectedValue();
                modelo.eliminarUsuario(usuarioEliminar);
                break;

            // Sección de Organizadores
            case "NuevoOrganizador":
                break;

            case "EliminarOrganizador":
                // Eliminar el organizador seleccionado
                Organizador organizadorEliminar = vista.listOrganizadores.getSelectedValue();
                modelo.eliminarOrganizador(organizadorEliminar);
                break;

            // Sección de Inscripciones
            case "InscribirUsuario":
                break;

            case "DeshacerInscripcion":
                // Deshacer la inscripción de un usuario en un evento
                Inscripcion inscripcionEliminar = vista.listInscripciones.getSelectedValue();
                modelo.eliminarInscripcion(inscripcionEliminar);
                break;

            default:
                break;
        }
    }

    // Métodos para refrescar la vista con datos actualizados (según el modelo)
    private void refrescarSeccionEventos() {

    }

    private void refrescarSeccionUsuarios() {

    }

    private void refrescarSeccionOrganizadores() {

    }

    private void refrescarSeccionInscripciones() {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        // Guardar datos o realizar acciones antes de cerrar la ventana
        modelo.desconectar();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
