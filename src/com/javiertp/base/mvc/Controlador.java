package com.javiertp.base.mvc;

import com.javiertp.base.*;
import com.javiertp.base.util.HibernateUtil;
import com.javiertp.base.util.Util;

import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The type Controlador.
 */
public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {

    private final Vista vista;
    private final Modelo modelo;

    /**
     * Instantiates a new Controlador.
     *
     * @param vista  the vista
     * @param modelo the modelo
     */
    public Controlador(Vista vista, Modelo modelo) {

        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);

        vista.listValoraciones.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = vista.listValoraciones.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object info = vista.listValoraciones.getModel().getElementAt(index);
                    vista.listValoraciones.setToolTipText(info.toString());
                }
            }
        });

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

    /**
     * Metodo para agregar los ActionListener
     * @param listener
     */
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
        vista.desinscribirUsuarioBtn.setActionCommand("DesinscribirUsuarioEvento");
        vista.desvincularEventoOrganizadorBtn.addActionListener(listener);
        vista.desvincularEventoOrganizadorBtn.setActionCommand("DesvincularOrganizador");
        vista.desapuntarUsuarioBtn.addActionListener(listener);
        vista.desapuntarUsuarioBtn.setActionCommand("DesapuntarUsuario");
        vista.inscribirUsuarioEventoBtn.addActionListener(listener);
        vista.inscribirUsuarioEventoBtn.setActionCommand("InscribirUsuario");
        vista.asignarEventoOrganizadorBtn.addActionListener(listener);
        vista.asignarEventoOrganizadorBtn.setActionCommand("AsignarOrganizador");

        vista.salirItem.addActionListener(listener);
        vista.conexionItem.addActionListener(listener);
    }

    /**
     * Metodo para agregar los ListSelectionListener
     * @param listener
     */
    private void addListSelectionListener(ListSelectionListener listener){
        vista.listEventos.addListSelectionListener(listener);
        vista.listUsuarios.addListSelectionListener(listener);
        vista.listOrganizadores.addListSelectionListener(listener);
        vista.listInscripciones.addListSelectionListener(listener);
        vista.listValoraciones.addListSelectionListener(listener);
    }

    /**
     * Metodo para agregar los WindowListener
     * @param listener
     */
    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    /**
     * Metodo para manejar los eventos
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();

        try {
            switch (comando) {
                case "NuevoEvento":
                    Evento eventoAInsertar = new Evento(vista.nombreEventoTxt.getText(), String.valueOf(vista.eventoComboBox.getSelectedItem()),
                            Date.valueOf(vista.eventoDPicker.getDate()), Float.parseFloat(vista.precioEventoTxt.getText()),
                            (Organizador) vista.organizadorComboBox.getSelectedItem());
                    modelo.guardarEvento(eventoAInsertar);
                    break;
                case "ModificarEvento":
                    Evento eventoAModificar = vista.listEventos.getSelectedValue();
                    eventoAModificar.setNombre(vista.nombreEventoTxt.getText());
                    eventoAModificar.setUbicacion(String.valueOf(vista.eventoComboBox.getSelectedItem()));
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
                    try {
                        modelo.guardarUsuario(usuarioAinsertar);
                    } catch (Exception e) {
                        Util.showErrorAlert("El email introducido ya existe");
                        return;
                    }
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
                    try {
                        modelo.guardarOrganizador(organizadorAInsertar);
                    } catch (Exception e) {
                        Util.showErrorAlert("El email introducido ya existe");
                        return;
                    }
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
                case "EliminarInscripcion":
                    Inscripcion inscripcionEliminar = vista.listInscripciones.getSelectedValue();
                    modelo.eliminarInscripcion(inscripcionEliminar);
                    break;
                case "NuevaValoracion":
                    int puntuacion = Integer.parseInt(vista.puntuacionValoracionTxt.getText());

                    if (puntuacion < 1 || puntuacion > 5) {
                        Util.showErrorAlert("La puntuacion debe estar entre 1 y 5");
                        return;
                    }
                    Valoracion valoracionAInsertar = new Valoracion(
                            (Usuario) vista.usuarioValoracionCombo.getSelectedItem(),
                            (Evento) vista.eventoValoracionCombo.getSelectedItem(),
                            (Organizador) vista.organaizadorValoracionCombo.getSelectedItem(),
                            Integer.parseInt(vista.puntuacionValoracionTxt.getText()),
                            vista.comentarioValoracionTxt.getText()
                    );
                    modelo.guardarValoracion(valoracionAInsertar);
                    break;
                case "ModificarValoracion":
                    int puntuacion2 = Integer.parseInt(vista.puntuacionValoracionTxt.getText());

                    if (puntuacion2 < 1 || puntuacion2 > 5) {
                        Util.showErrorAlert("La puntuacion debe estar entre 1 y 5");
                        return;
                    }
                    Valoracion valoracionAModificar = vista.listValoraciones.getSelectedValue();
                    valoracionAModificar.setUsuario((Usuario) vista.usuarioValoracionCombo.getSelectedItem());
                    valoracionAModificar.setEvento((Evento) vista.eventoValoracionCombo.getSelectedItem());
                    valoracionAModificar.setOrganizador((Organizador) vista.organaizadorValoracionCombo.getSelectedItem());
                    valoracionAModificar.setPuntuacion(Integer.parseInt(vista.puntuacionValoracionTxt.getText()));
                    valoracionAModificar.setComentario(vista.comentarioValoracionTxt.getText());
                    modelo.modificarValoracion(valoracionAModificar);
                    break;
                case "EliminarValoracion":
                    Valoracion valoracionAEliminar = vista.listValoraciones.getSelectedValue();
                    modelo.eliminarValoracion(valoracionAEliminar);
                    break;
                case "DesapuntarUsuario":
                    Usuario usuarioDesinscribir = vista.listUsuarios.getSelectedValue();
                    Evento eventoDesinscribir = vista.listInscripcionesUsuario.getSelectedValue();
                    modelo.desapuntarUsuario(eventoDesinscribir, usuarioDesinscribir);
                    break;
                case "InscribirUsuario":
                    Usuario usuarioInscribir = vista.listUsuariosDisponiblesPorEvento.getSelectedValue();
                    Evento eventoInscribir = vista.listEventos.getSelectedValue();
                    Date fechaInscripcion = Date.valueOf(LocalDate.now());
                    String estado = "Pendiente";
                    modelo.inscribirUsuario(eventoInscribir, usuarioInscribir, fechaInscripcion, estado);
                    break;
                case "DesinscribirUsuarioEvento":
                    Usuario usuarioDesinscribirEvento = vista.listUsuariosEvento.getSelectedValue();
                    Evento eventoDesinscribirEvento = vista.listEventos.getSelectedValue();
                    modelo.desapuntarUsuario(eventoDesinscribirEvento, usuarioDesinscribirEvento);
                    break;
                case "DesvincularOrganizador":
                    Evento eventoDesvincular = vista.listEventosOrganizador.getSelectedValue();
                    modelo.desvincularOrganizador(eventoDesvincular);
                    break;
                case "AsignarOrganizador":
                    Evento eventoAsignar = vista.listEventosDisponiblesOrganizador.getSelectedValue();
                    Organizador organizadorAsignar = vista.listOrganizadores.getSelectedValue();
                    modelo.asignarOrganizador(eventoAsignar, organizadorAsignar);
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

            // Refrescar las secciones después de la acción
            refrescarSeccionEventos();
            refrescarSeccionUsuarios();
            refrescarSeccionOrganizadores();
            refrescarSeccionInscripciones();
            refrescarSeccionValoraciones();

        } catch (Exception e) {
            HibernateUtil.getCurrentSession().getTransaction().rollback();
        }
    }

    /**
     * Lista los usuarios en la vista
     */
    private void listarUsuarios(){
        List<Usuario> usuarios = modelo.obtenerUsuarios();
        vista.dlmUsuarios.clear();
        for(Usuario usuario : usuarios){
            vista.dlmUsuarios.addElement(usuario);
        }
    }

    /**
     * Lista los eventos en la vista
     */
    private void listarEventos(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dlmEventos.clear();
        for(Evento evento : eventos){
            vista.dlmEventos.addElement(evento);
        }
    }

    /**
     * Lista los eventos por organizador en la vista
     *     */
    private void listarEventosPorOrganizador(Organizador organizador){
        List<Evento> eventos = modelo.obtenerEventosPorOrganizador(organizador);
        vista.dlmEventosOrganizador.clear();
        for(Evento evento : eventos){
            vista.dlmEventosOrganizador.addElement(evento);
        }
    }

    /** Lista las inscripciones en la vista
     *
     */
    private void listarInscripciones(){
        List<Inscripcion> inscripciones = modelo.obtenerInscripciones();
        vista.dlmInscripciones.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmInscripciones.addElement(inscripcion);
        }
    }

    /**
     * Lista las inscripciones por usuario
     * @param usuario
     */
    private void listarInscripcionesPorUsuario(Usuario usuario){
        List<Inscripcion> inscripciones = modelo.obtenerInscripcionesPorUsuario(usuario);
        vista.dlmEventosUsuario.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmEventosUsuario.addElement(inscripcion.getEvento());
        }
    }

    /**
     * Lista las inscripciones por evento
     * @param evento
     */
    private void listarInscripcionesPorEvento(Evento evento){
        List<Inscripcion> inscripciones = modelo.obtenerInscripcionesPorEvento(evento);
        vista.dlmUsuariosEvento.clear();
        for(Inscripcion inscripcion : inscripciones){
            vista.dlmUsuariosEvento.addElement(inscripcion.getUsuario());
        }
    }


    /**
     * Lista los usuarios disponibles para inscribirse en un evento.
     * @param evento el evento para el que se quieren obtener los usuarios disponibles.
     */
    private void listarUsuariosDisponiblesPorEvento(Evento evento){
        List<Usuario> usuarios = modelo.obtenerUsuariosDisponiblesPorEvento(evento);
        vista.dlmUsuariosDisponiblesPorEvento.clear();
        for(Usuario usuario : usuarios){
            vista.dlmUsuariosDisponiblesPorEvento.addElement(usuario);
        }
    }


    /**
     * Muestra en la vista todos los organizadores guardados en la base de datos.
     *     */
    private void listarOrganizadores(){
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dlmOrganizadores.clear();
        for(Organizador organizador : organizadores){
            vista.dlmOrganizadores.addElement(organizador);
        }
    }

    /**
     * lista las valoraciones
     */
    private void listarValoraciones(){
        List<Valoracion> valoraciones = modelo.obtenerValoraciones();
        vista.dlmValoraciones.clear();
        for(Valoracion valoracion : valoraciones){
            vista.dlmValoraciones.addElement(valoracion);
        }
    }

    /**
     * Muestra en la vista todos los eventos disponibles para un organizador.
     * Un evento est  disponible para un organizador si el organizador no
     * est  asociado a ese evento.
     * @param organizador el organizador para el que se quieren obtener los eventos disponibles.
     */
    private void listarEventosDisponiblesPorOrganizador(Organizador organizador){
        List<Evento> eventos = modelo.obtenerEventosDisponiblesPorOrganizador(organizador);
        vista.dlmEventosDisponiblesOrganizador.clear();
        for(Evento evento : eventos){
            vista.dlmEventosDisponiblesOrganizador.addElement(evento);
        }
    }

    /**
     * lista los eventos disponibles para inscribirse
     */
    private void listarEventosInscripcion(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dcbEventosInscripcion.removeAllElements();
        for(Evento evento : eventos){
            vista.dcbEventosInscripcion.addElement(evento);
        }
    }

    /**
     * lista los usuarios disponibles para inscribirse
     */
    private void listarUsuariosInscripcion(){
        List<Usuario> usuarios = modelo.obtenerUsuarios();
        vista.dcbUsuariosInscripcion.removeAllElements();
        for(Usuario usuario : usuarios){
            vista.dcbUsuariosInscripcion.addElement(usuario);
        }
    }

    /**
     * lista los usuarios disponibles para inscribirse
     */
    private void listarUsuariosValoraciones(){
        List<Usuario> usuarios = modelo.obtenerUsuarios();
        vista.dcbUsuarioValoracion.removeAllElements();
        for(Usuario usuario : usuarios){
            vista.dcbUsuarioValoracion.addElement(usuario);
        }
    }

    /**
     * lista los eventos disponibles para inscribirse
     */
    private void listarEventosValoraciones(){
        List<Evento> eventos = modelo.obtenerEventos();
        vista.dcbEventoValoracion.removeAllElements();
        for(Evento evento : eventos){
            vista.dcbEventoValoracion.addElement(evento);
        }
    }

    /**
     * lista los organizadores disponibles para inscribirse
     */
    private void listarOrganizadoresValoraciones(){
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dcbOrganizadorValoracion.removeAllElements();
        for(Organizador organizador : organizadores){
            vista.dcbOrganizadorValoracion.addElement(organizador);
        }
    }

    /**
     * lista los organizadores disponibles para inscribirse
     */
    private void listarOrganizadoresEvento() {
        List<Organizador> organizadores = modelo.obtenerOrganizadores();
        vista.dcbOrganizadorEvento.removeAllElements();
        for(Organizador organizador : organizadores){
            vista.dcbOrganizadorEvento.addElement(organizador);
        }
    }

    /**
     * refrescar la lista de eventos
     */
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

    /**
     * refrescar la lista de usuarios
     */
    private void refrescarSeccionUsuarios() {
        vista.nombreUsuarioTxt.setText("");
        vista.apellidosUsuarioTxt.setText("");
        vista.emailUsuarioTxt.setText("");
        vista.usuarioDPicker.setText("");
        listarUsuarios();
        clearDLMUsuario();
    }

    /**
     * limpia la lista de eventos de un usuario
     */
    private void clearDLMUsuario() {
        vista.dlmEventosUsuario.clear();
    }

    private void clearDLMEvento() {
        vista.dlmUsuariosEvento.clear();
        vista.dlmUsuariosDisponiblesPorEvento.clear();
    }

    private void clearDLMOrganizador() {
        vista.dlmEventosOrganizador.clear();
        vista.dlmEventosDisponiblesOrganizador.clear();
    }

    private void refrescarSeccionOrganizadores() {
        vista.nombreOrganizadorTxt.setText("");
        vista.apellidosOrganizadorTxt.setText("");
        vista.telefonoOrganizadorTxt.setText("");
        vista.emailOrganizadorTxt.setText("");
        listarOrganizadores();
        clearDLMOrganizador();
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


    /**
     * Refresca la sección de valoraciones en la interfaz de usuario con datos
     * actualizados del modelo.
     *
     * Borra cualquier valor seleccionado en los combobox correspondientes y
     * lista de nuevo las valoraciones, usuarios, eventos y organizadores
     * disponibles para la valoración.
     */
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

    /**
     * Handles the window closing event. Prompts the user with a confirmation dialog asking if they want to exit the application.
     * If the user confirms, disconnects the model and exits the application.
     *
     * @param windowEvent the window event that triggers this method
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int resp = Util.showConfirmDialog("¿Desea salir de la aplicación?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            modelo.desconectar();
            System.exit(0);
        }
    }

    /**
     * Handles the selection change events for various lists in the UI.
     * Updates the corresponding UI components based on the new selection.
     *
     * @param listSelectionEvent the event that characterizes the change in selection.
     *
     * If the source of the event is:
     * - `vista.listEventos`: Updates event-related fields and lists inscripciones and available users for the selected event.
     * - `vista.listUsuarios`: Updates user-related fields and lists inscripciones for the selected user.
     * - `vista.listOrganizadores`: Updates organizer-related fields and lists events and available events for the selected organizer.
     * - `vista.listInscripciones`: Updates inscripcion-related fields.
     * - `vista.listValoraciones`: Updates valoracion-related fields.
     */
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
        // Si la lista de valoraciones cambia de selección
        else if(listSelectionEvent.getSource() == vista.listValoraciones && vista.listValoraciones.getSelectedValue() != null){
            Valoracion valoracion = vista.listValoraciones.getSelectedValue();
            vista.usuarioValoracionCombo.setSelectedItem(valoracion.getUsuario());
            vista.eventoValoracionCombo.setSelectedItem(valoracion.getEvento());
            vista.organaizadorValoracionCombo.setSelectedItem(valoracion.getOrganizador());
            vista.puntuacionValoracionTxt.setText(String.valueOf(valoracion.getPuntuacion()));
            vista.comentarioValoracionTxt.setText(valoracion.getComentario());
        }
    }

    /**
     * Resetea los DefaultListModel de los JList de EventosUsuario, UsuariosEvento y
     * UsuariosDisponiblesEvento. Esto es necesario hacerlo cada vez que se cambia de
     * secci n en la interfaz gr fica para que no se sigan mostrando los datos de la
     * secci n anterior.
     */
    private void resetearDLMs() {
        vista.dlmUsuariosEvento.clear();
        vista.dlmEventosUsuario.clear();
        vista.dlmUsuariosDisponiblesPorEvento.clear();
    }
}
