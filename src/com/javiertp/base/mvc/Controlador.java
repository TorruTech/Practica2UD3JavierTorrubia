package com.javiertp.base.mvc;

import com.javiertp.base.*;
import com.javiertp.base.util.Util;

import java.awt.event.*;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {

    private final Vista vista;
    private final Modelo modelo;

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
            refrescarSeccionValoraciones();
        } catch (Exception e) {
            System.out.println("Inicializando el programa");
        }
    }

    // Método para agregar los ActionListener
    private void addActionListener(ActionListener listener){
        vista.nuevoEventoBtn.addActionListener(listener);
        vista.nuevoEventoBtn.setActionCommand("NuevoEvento");
        vista.modificarEventoBtn.addActionListener(listener);
        vista.modificarEventoBtn.setActionCommand("ModificarEvento");
        vista.eliminarEventoBtn.addActionListener(listener);
        vista.eliminarEventoBtn.setActionCommand("EliminarEvento");
        vista.nuevoUsuarioBtn.addActionListener(listener);
        vista.nuevoUsuarioBtn.setActionCommand("NuevoUsuario");
        vista.modificarUsuarioBtn.addActionListener(listener);
        vista.modificarUsuarioBtn.setActionCommand("ModificarUsuario");
        vista.eliminarUsuarioBtn.addActionListener(listener);
        vista.eliminarUsuarioBtn.setActionCommand("EliminarUsuario");
        vista.nuevoOrganizadorBtn.addActionListener(listener);
        vista.nuevoOrganizadorBtn.setActionCommand("NuevoOrganizador");
        vista.modificarOrganizadorBtn.addActionListener(listener);
        vista.modificarOrganizadorBtn.setActionCommand("ModificarOrganizador");
        vista.nuevaInscripcionBtn.addActionListener(listener);
        vista.nuevaInscripcionBtn.setActionCommand("NuevaInscripcion");
        vista.modificarInscripcionBtn.addActionListener(listener);
        vista.modificarInscripcionBtn.setActionCommand("ModificarInscripcion");
        vista.eliminarInscripcionBtn.addActionListener(listener);
        vista.eliminarInscripcionBtn.setActionCommand("EliminarInscripcion");
        vista.eliminarOrganizadorBtn.addActionListener(listener);
        vista.eliminarOrganizadorBtn.setActionCommand("EliminarOrganizador");
        vista.nuevoValoracionBtn.addActionListener(listener);
        vista.nuevoValoracionBtn.setActionCommand("NuevaValoracion");
        vista.modificarValoracionBtn.addActionListener(listener);
        vista.modificarValoracionBtn.setActionCommand("ModificarValoracion");
        vista.eliminarValoracionBtn.addActionListener(listener);
        vista.eliminarValoracionBtn.setActionCommand("EliminarValoracion");
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
        vista.listValoraciones.addListSelectionListener(listener);
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
            case "ModificarEvento":
                Evento eventoAModificar = vista.listEventos.getSelectedValue();
                eventoAModificar.setNombre(vista.nombreEventoTxt.getText());
                eventoAModificar.setOrganizador((Organizador) vista.organizadorComboBox.getSelectedItem());
                eventoAModificar.setPrecio(Float.parseFloat(vista.precioEventoTxt.getText()));
                modelo.modificarEvento(eventoAModificar);
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
            case "ModificarUsuario":
                Usuario usuarioAModificar = vista.listUsuarios.getSelectedValue();
                usuarioAModificar.setNombre(vista.nombreUsuarioTxt.getText());
                usuarioAModificar.setApellidos(vista.apellidosUsuarioTxt.getText());
                usuarioAModificar.setEmail(vista.emailUsuarioTxt.getText());
                modelo.modificarUsuario(usuarioAModificar);
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
            case "ModificarOrganizador":
                Organizador organizadorAModificar = vista.listOrganizadores.getSelectedValue();
                organizadorAModificar.setNombre(vista.nombreOrganizadorTxt.getText());
                organizadorAModificar.setApellidos(vista.apellidosOrganizadorTxt.getText());
                organizadorAModificar.setTelefono(vista.telefonoOrganizadorTxt.getText());
                organizadorAModificar.setEmail(vista.emailOrganizadorTxt.getText());
                modelo.modificarOrganizador(organizadorAModificar);
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
            case "ModificarInscripcion":
                Inscripcion inscripcionAModificar = vista.listInscripciones.getSelectedValue();
                inscripcionAModificar.setEstado(vista.estadoComboBox.getSelectedItem().toString());
                inscripcionAModificar.setEvento((Evento) vista.eventoInscripcionComboBox.getSelectedItem());
                inscripcionAModificar.setUsuario((Usuario) vista.usuarioInscripcionComboBox.getSelectedItem());
                modelo.modificarInscripcion(inscripcionAModificar);
                break;
            case "InscribirUsuario":
                break;
            case "EliminarInscripcion":
                Inscripcion inscripcionEliminar = vista.listInscripciones.getSelectedValue();
                modelo.eliminarInscripcion(inscripcionEliminar);
                break;
            case "NuevaValoracion":
                Valoracion valoracionAInsertar = new Valoracion(
                        (Usuario) vista.usuarioValoracionCombo.getSelectedItem(),
                        (Evento) vista.eventoValoracionCombo.getSelectedItem(),
                        (Organizador) vista.organaizadorValoracionCombo.getSelectedItem(),
                        Integer.parseInt(vista.puntuacionValoracionTxt.getText()),
                        vista.comentarioValoracionTxt.getText()
                );
                modelo.guardarValoracion(valoracionAInsertar);
                break;
            case "EliminarValoracion":
                Valoracion valoracionAEliminar = vista.listValoraciones.getSelectedValue();
                modelo.eliminarValoracion(valoracionAEliminar);
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
            refrescarSeccionValoraciones();
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

    private void listarUsuariosPorEvento(Evento evento){
        List<Inscripcion> inscripciones = evento.getInscripciones();
        vista.dlmUsuariosEvento.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmUsuariosEvento.addElement(inscripcion.getUsuario());
        }
    }

    private void listarEventosDisponiblePorUsuario(Usuario usuario){
        List<Evento> eventos = modelo.obtenerEventosDisponiblesPorUsuario(usuario);
        vista.dlmEventosUsuario.clear();
        for(Evento evento : eventos){
            vista.dlmEventosUsuario.addElement(evento);
        }
    }

    private void listarEventos(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dlmEventos.clear();
        for(Evento evento : eventos){
            vista.dlmEventos.addElement(evento);
        }
    }

    private void listarEventosPorOrganizador(Organizador organizador){
        List<Evento> eventos = organizador.getEventos();
        vista.dlmEventosOrganizador.clear();
        for(Evento evento : eventos){
            vista.dlmEventosOrganizador.addElement(evento);
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
        List<Inscripcion> inscripciones = modelo.obtenerInscripcionesPorUsuario(usuario);
        vista.dlmEventosUsuario.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmEventosUsuario.addElement(inscripcion.getEvento());
        }
    }

    private void listarInscripcionesPorEvento(Evento evento){
        List<Inscripcion> inscripciones = modelo.obtenerInscripcionesPorEvento(evento);
        vista.dlmUsuariosEvento.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmUsuariosEvento.addElement(inscripcion.getUsuario());
        }
    }

    private void listarUsuariosDisponiblesPorEvento(Evento evento){
        List<Usuario> usuarios = modelo.obtenerUsuariosDisponiblesPorEvento(evento);
        vista.dlmUsuariosDisponiblesPorEvento.clear();
        for(Usuario usuario : usuarios){
            vista.dlmUsuariosDisponiblesPorEvento.addElement(usuario);
        }
    }

    private void listarOrganizadores(){
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dlmOrganizadores.clear();
        for(Organizador organizador : organizadores){
            vista.dlmOrganizadores.addElement(organizador);
        }
    }

    private void listarValoraciones(){
        List<Valoracion> valoraciones = modelo.obtenerValoraciones();
        vista.dlmValoraciones.clear();
        for(Valoracion valoracion : valoraciones){
            vista.dlmValoraciones.addElement(valoracion);
        }
    }

    private void listarEventosDisponiblesPorOrganizador(Organizador organizador){
        List<Evento> eventos = modelo.obtenerEventosDisponiblesPorOrganizador(organizador);
        vista.dlmEventosDisponiblesOrganizador.clear();
        for(Evento evento : eventos){
            vista.dlmEventosDisponiblesOrganizador.addElement(evento);
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

    private void listarUsuariosValoraciones(){
        List<Usuario> usuarios = modelo.obtenerUsuarios();
        vista.dcbUsuarioValoracion.removeAllElements();
        for(Usuario usuario : usuarios){
            vista.dcbUsuarioValoracion.addElement(usuario);
        }
    }

    private void listarEventosValoraciones(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dcbEventoValoracion.removeAllElements();
        for(Evento evento : eventos){
            vista.dcbEventoValoracion.addElement(evento);
        }
    }

    private void listarOrganizadoresValoraciones(){
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dcbOrganizadorValoracion.removeAllElements();
        for(Organizador organizador : organizadores){
            vista.dcbOrganizadorValoracion.addElement(organizador);
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
        clearDLMEvento();
    }

    private void refrescarSeccionUsuarios() {
        vista.nombreUsuarioTxt.setText("");
        vista.apellidosUsuarioTxt.setText("");
        vista.emailUsuarioTxt.setText("");
        vista.usuarioDPicker.setText("");
        listarUsuarios();
        clearDLMUsuario();
    }

    private void clearDLMUsuario() {
        vista.dlmEventosUsuario.clear();
    }

    private void clearDLMEvento() {
        vista.dlmUsuariosEvento.clear();
        vista.dlmUsuariosDisponiblesPorEvento.clear();
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

        // resetear los dlm de EventosUsuario, UsuariosEvento, UsuariosDisponiblesEvento
        resetearDLMs();
    }

    private void refrescarSeccionValoraciones() {
        vista.usuarioValoracionCombo.setSelectedIndex(-1);
        vista.eventoValoracionCombo.setSelectedIndex(-1);
        vista.organaizadorValoracionCombo.setSelectedIndex(-1);
        vista.puntuacionValoracionTxt.setText("");
        vista.comentarioValoracionTxt.setText("");
        listarValoraciones();
        listarUsuariosValoraciones();
        listarEventosValoraciones();
        listarOrganizadoresValoraciones();
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
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        // Si la lista de eventos cambia de selección
        if(listSelectionEvent.getSource() == vista.listEventos && vista.listEventos.getSelectedValue() != null){
            Evento evento = vista.listEventos.getSelectedValue();
            vista.nombreEventoTxt.setText(evento.getNombre());
            vista.eventoComboBox.setSelectedItem(evento.getUbicacion());
            vista.organizadorComboBox.setSelectedItem(evento.getOrganizador());
            vista.eventoDPicker.setDate(evento.getFechaInicio().toLocalDate());
            vista.precioEventoTxt.setText(String.valueOf(evento.getPrecio()));
            listarInscripcionesPorEvento(evento);
            listarUsuariosDisponiblesPorEvento(evento);
        }
        // Si la lista de usuarios cambia de selección
        else if(listSelectionEvent.getSource() == vista.listUsuarios && vista.listUsuarios.getSelectedValue() != null){
            Usuario usuario = vista.listUsuarios.getSelectedValue();
            vista.nombreUsuarioTxt.setText(usuario.getNombre());
            vista.apellidosUsuarioTxt.setText(usuario.getApellidos());
            vista.emailUsuarioTxt.setText(usuario.getEmail());
            vista.usuarioDPicker.setDate(usuario.getFechaRegistro().toLocalDate());
            listarInscripcionesPorUsuario(usuario);
        }
        // Si la lista de organizadores cambia de selección
        else if(listSelectionEvent.getSource() == vista.listOrganizadores && vista.listOrganizadores.getSelectedValue() != null){
            Organizador organizador = vista.listOrganizadores.getSelectedValue();
            vista.nombreOrganizadorTxt.setText(organizador.getNombre());
            vista.apellidosOrganizadorTxt.setText(organizador.getApellidos());
            vista.telefonoOrganizadorTxt.setText(organizador.getTelefono());
            vista.emailOrganizadorTxt.setText(organizador.getEmail());
            listarEventosPorOrganizador(organizador);
            listarEventosDisponiblesPorOrganizador(organizador);
        }
        // Si la lista de inscripciones cambia de selección
        else if(listSelectionEvent.getSource() == vista.listInscripciones && vista.listInscripciones.getSelectedValue() != null){
            Inscripcion inscripcion = vista.listInscripciones.getSelectedValue();
            vista.eventoInscripcionComboBox.setSelectedItem(inscripcion.getEvento());
            vista.usuarioInscripcionComboBox.setSelectedItem(inscripcion.getUsuario());
            vista.inscripcionDPicker.setDate(inscripcion.getFechaInscripcion().toLocalDate());
            vista.estadoComboBox.setSelectedItem(inscripcion.getEstado());
        }
    }

    private void resetearDLMs() {
        vista.dlmUsuariosEvento.clear();
        vista.dlmEventosUsuario.clear();
        vista.dlmUsuariosDisponiblesPorEvento.clear();
    }

}
