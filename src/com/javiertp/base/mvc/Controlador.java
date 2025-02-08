package com.javiertp.base.mvc;

import com.javiertp.base.Evento;
import com.javiertp.base.Usuario;
import com.javiertp.base.Organizador;
import com.javiertp.base.Inscripcion;
import com.javiertp.base.util.Util;

import java.awt.event.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import javax.swing.*;
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

        try {
            refrescarSeccionEventos();
            refrescarSeccionUsuarios();
            refrescarSeccionOrganizadores();
            refrescarSeccionInscripciones();
        } catch (Exception e) {
            System.out.println("Inicializando el programa");
        }
    }

    // Método para agregar los ActionListener
    private void addActionListener(ActionListener listener){
        vista.nuevoEventoBtn.addActionListener(listener);
        vista.nuevoEventoBtn.setActionCommand("NuevoEvento");
        vista.eliminarEventoBtn.addActionListener(listener);
        vista.eliminarEventoBtn.setActionCommand("EliminarEvento");
        vista.nuevoUsuarioBtn.addActionListener(listener);
        vista.nuevoUsuarioBtn.setActionCommand("NuevoUsuario");
        vista.eliminarUsuarioBtn.addActionListener(listener);
        vista.eliminarUsuarioBtn.setActionCommand("EliminarUsuario");
        vista.nuevoOrganizadorBtn.addActionListener(listener);
        vista.nuevoOrganizadorBtn.setActionCommand("NuevoOrganizador");
        vista.nuevaInscripcionBtn.addActionListener(listener);
        vista.nuevaInscripcionBtn.setActionCommand("NuevaInscripcion");
        vista.eliminarInscripcionBtn.addActionListener(listener);
        vista.eliminarInscripcionBtn.setActionCommand("EliminarInscripcion");
        vista.eliminarOrganizadorBtn.addActionListener(listener);
        vista.eliminarOrganizadorBtn.setActionCommand("EliminarOrganizador");
        vista.desinscribirUsuarioBtn.addActionListener(listener);
        vista.desvincularEventoOrganizadorBtn.addActionListener(listener);
        vista.desinscribirseBtn.addActionListener(listener);
        vista.inscribirUsuarioEventoBtn.addActionListener(listener);
        vista.asignarEventoOrganizadorBtn.addActionListener(listener);

        vista.salirItem.addActionListener(listener);
        vista.conexionItem.addActionListener(listener);
    }

    // Método para agregar los ListSelectionListener
    private void addListSelectionListener(ListSelectionListener listener){
        vista.listEventos.addListSelectionListener(listener);
        vista.listUsuarios.addListSelectionListener(listener);
        vista.listOrganizadores.addListSelectionListener(listener);
        vista.listInscripciones.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();

        switch(comando) {
            case "NuevoEvento":
                Evento eventoAInsertar = new Evento(vista.nombreEventoTxt.getText(), String.valueOf(vista.eventoComboBox.getSelectedItem()),
                        Date.valueOf(vista.eventoDPicker.getDate()), Float.parseFloat(vista.precioEventoTxt.getText()),
                        (Organizador) vista.organizadorComboBox.getSelectedItem());
                modelo.guardarEvento(eventoAInsertar);
                break;
            case "EliminarEvento":
                Evento eventoEliminar = vista.listEventos.getSelectedValue();
                modelo.eliminarEvento(eventoEliminar);
                break;
            case "NuevoUsuario":
                Usuario usuarioAinsertar = new Usuario(vista.nombreUsuarioTxt.getText(), vista.apellidosUsuarioTxt.getText(),
                        vista.emailUsuarioTxt.getText(), Date.valueOf(vista.usuarioDPicker.getDate()));
                modelo.guardarUsuario(usuarioAinsertar);
                break;
            case "EliminarUsuario":
                Usuario usuarioEliminar = vista.listUsuarios.getSelectedValue();
                modelo.eliminarUsuario(usuarioEliminar);
                break;
            case "NuevoOrganizador":
                Organizador organizadorAInsertar = new Organizador(vista.nombreOrganizadorTxt.getText(), vista.apellidosOrganizadorTxt.getText(),
                        vista.telefonoOrganizadorTxt.getText(), vista.emailOrganizadorTxt.getText());
                modelo.guardarOrganizador(organizadorAInsertar);
                break;
            case "EliminarOrganizador":
                Organizador organizadorEliminar = vista.listOrganizadores.getSelectedValue();
                organizadorEliminar.desvincularEventos();
                modelo.eliminarOrganizador(organizadorEliminar);
                break;
            case "NuevaInscripcion":
                Inscripcion inscripcionAInsertar = new Inscripcion(Date.valueOf(vista.inscripcionDPicker.getDate()),
                        vista.estadoComboBox.getSelectedItem().toString(),
                        (Evento) vista.eventoInscripcionComboBox.getSelectedItem(),
                        (Usuario) vista.usuarioInscripcionComboBox.getSelectedItem());
                modelo.guardarInscripcion(inscripcionAInsertar);
                break;
            case "InscribirUsuario":
                break;
            case "DeshacerInscripcion":
                Inscripcion inscripcionEliminar = vista.listInscripciones.getSelectedValue();
                modelo.eliminarInscripcion(inscripcionEliminar);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                break;
            case "Salir":
                int resp = Util.showConfirmDialog("¿Desea salir de la aplicación?", "Salir");
                if (resp == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
                return;
            default:
                break;
        }

        try {
            refrescarSeccionEventos();
            refrescarSeccionUsuarios();
            refrescarSeccionOrganizadores();
            refrescarSeccionInscripciones();
        } catch (Exception e) {
            System.out.println("Cargando datos");
        }
    }

    private void listarUsuarios(){
        List<Usuario> usuarios = modelo.obtenerUsuarios();
        vista.dlmUsuarios.clear();
        for(Usuario usuario : usuarios){
            vista.dlmUsuarios.addElement(usuario);
        }
    }

    private void listarEventos(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dlmEventos.clear();
        for(Evento evento : eventos){
            vista.dlmEventos.addElement(evento);
        }
    }

    private void listarInscripciones(){
        List<Inscripcion> inscripciones = modelo.obtenerInscripciones();
        vista.dlmInscripciones.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmInscripciones.addElement(inscripcion);
        }
    }

    private void listarInscripcionesPorUsuario(Usuario usuario){
        List<Inscripcion> inscripciones = usuario.getInscripciones();
        vista.dlmEventosUsuario.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmEventosUsuario.addElement(inscripcion.getEvento());
        }
    }

    private void listarOrganizadores(){
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dlmOrganizadores.clear();
        for(Organizador organizador : organizadores){
            vista.dlmOrganizadores.addElement(organizador);
        }
    }

    private void listarEventosInscripcion(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dcbEventosInscripcion.removeAllElements();
        for(Evento evento : eventos){
            vista.dcbEventosInscripcion.addElement(evento);
        }
    }

    private void listarUsuariosInscripcion(){
        List<Usuario> usuarios = modelo.obtenerUsuarios();
        vista.dcbUsuariosInscripcion.removeAllElements();
        for(Usuario usuario : usuarios){
            vista.dcbUsuariosInscripcion.addElement(usuario);
        }
    }

    private void listarOrganizadoresEvento() {
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dcbOrganizadorEvento.removeAllElements();
        for(Organizador organizador : organizadores){
            vista.dcbOrganizadorEvento.addElement(organizador);
        }
    }

    // Métodos para refrescar la vista con datos actualizados (según el modelo)
    private void refrescarSeccionEventos() {
        vista.nombreEventoTxt.setText("");
        vista.eventoComboBox.setSelectedIndex(-1);
        vista.eventoDPicker.setText("");
        vista.precioEventoTxt.setText("");
        vista.organizadorComboBox.setSelectedIndex(-1);
        vista.listEventos.clearSelection();
        listarEventos();
        listarOrganizadoresEvento();
    }

    private void refrescarSeccionUsuarios() {
        vista.nombreUsuarioTxt.setText("");
        vista.apellidosUsuarioTxt.setText("");
        vista.emailUsuarioTxt.setText("");
        vista.usuarioDPicker.setText("");
        listarUsuarios();
    }

    private void refrescarSeccionOrganizadores() {
        vista.nombreOrganizadorTxt.setText("");
        vista.apellidosOrganizadorTxt.setText("");
        vista.telefonoOrganizadorTxt.setText("");
        vista.emailOrganizadorTxt.setText("");
        listarOrganizadores();
    }

    private void refrescarSeccionInscripciones() {
        vista.eventoInscripcionComboBox.setSelectedIndex(-1);
        vista.usuarioInscripcionComboBox.setSelectedIndex(-1);
        vista.inscripcionDPicker.setText("");
        vista.estadoComboBox.setSelectedIndex(-1);
        vista.listInscripciones.clearSelection();
        listarInscripciones();
        listarEventosInscripcion();
        listarUsuariosInscripcion();
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int resp = Util.showConfirmDialog("¿Desea salir de la aplicación?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            modelo.desconectar();
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
