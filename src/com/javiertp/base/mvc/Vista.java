package com.javiertp.base.mvc;

import com.github.lgooddatepicker.components.DatePicker;
import com.javiertp.base.Evento;
import com.javiertp.base.Usuario;
import com.javiertp.base.Organizador;
import com.javiertp.base.Inscripcion;
import com.javiertp.base.enums.Estados;
import com.javiertp.base.enums.Ubicaciones;

import javax.swing.*;
import java.awt.*;

public class Vista {

    //Inscripción
    JFrame frame;
    JTabbedPane tabbedPane1;
    JPanel panel1;

    //Usuario
    JTextField nombreUsuarioTxt;
    JTextField apellidosUsuarioTxt;
    JTextField emailUsuarioTxt;
    DatePicker usuarioDPicker;
    JButton nuevoUsuarioBtn;
    JButton modificarUsuarioBtn;
    JButton eliminarUsuarioBtn;
    JButton desinscribirseBtn;

    //Evento
    JTextField nombreEventoTxt;
    DatePicker eventoDPicker;
    JComboBox eventoComboBox;
    JComboBox organizadorComboBox;
    JTextField precioEventoTxt;
    JButton nuevoEventoBtn;
    JButton modificarEventoBtn;
    JButton eliminarEventoBtn;
    JButton desinscribirUsuarioBtn;
    JButton inscribirUsuarioEventoBtn;

    //Organizador
    JTextField nombreOrganizadorTxt;
    JTextField apellidosOrganizadorTxt;
    JTextField emailOrganizadorTxt;
    JTextField telefonoOrganizadorTxt;
    JButton nuevoOrganizadorBtn;
    JButton modificarOrganizadorBtn;
    JButton eliminarOrganizadorBtn;
    JButton desvincularEventoOrganizadorBtn;
    JButton asignarEventoOrganizadorBtn;

    //Inscripcion
    JComboBox eventoInscripcionComboBox;
    JComboBox usuarioInscripcionComboBox;
    DatePicker inscripcionDPicker;
    JComboBox estadoComboBox;
    JButton nuevaInscripcionBtn;
    JButton modificarInscripcionBtn;
    JButton eliminarInscripcionBtn;

    JList<Usuario> listUsuarios;
    JList<Evento> listInscripcionesUsuario;
    JList<Evento> listEventos;
    JList<Usuario> listUsuariosEvento;
    JList<Evento> listUsuariosDisponibles;
    JList<Organizador> listOrganizadores;
    JList<Evento> listEventosOrganizador;
    JList<Organizador> listEventosDisponiblesOrganizador;
    JList<Inscripcion> listInscripciones;

    DefaultListModel<Usuario> dlmUsuarios;
    DefaultListModel<Evento> dlmEventosUsuario;
    DefaultListModel<Evento> dlmEventos;
    DefaultListModel<Usuario> dlmUsuariosEvento;
    DefaultListModel<Evento> dlmUsuariosDisponibles;
    DefaultListModel<Organizador> dlmOrganizadores;
    DefaultListModel<Evento> dlmEventosOrganizador;
    DefaultListModel<Organizador> dlmEventosDisponiblesOrganizador;
    DefaultListModel<Inscripcion> dlmInscripciones;

    DefaultComboBoxModel<Organizador> dcbOrganizadorEvento;
    DefaultComboBoxModel<Evento> dcbEventosInscripcion;
    DefaultComboBoxModel<Usuario> dcbUsuariosInscripcion;

    JMenuItem conexionItem;
    JMenuItem salirItem;

    public Vista() {
        frame = new JFrame("App de gestión de eventos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(600,600));
        crearMenu();
        iniciarListas();
        setEnumComboBox();
    }

    private void iniciarListas() {
        dlmUsuarios = new DefaultListModel<>();
        listUsuarios.setModel(dlmUsuarios);

        dlmEventosUsuario = new DefaultListModel<Evento>();
        listInscripcionesUsuario.setModel(dlmEventosUsuario);

        dlmEventos = new DefaultListModel<>();
        listEventos.setModel(dlmEventos);

        dlmUsuariosEvento = new DefaultListModel<>();
        listUsuariosEvento.setModel(dlmUsuariosEvento);

        dlmUsuariosDisponibles = new DefaultListModel<>();
        listUsuariosDisponibles.setModel(dlmUsuariosDisponibles);

        dlmOrganizadores = new DefaultListModel<>();
        listOrganizadores.setModel(dlmOrganizadores);

        dlmEventosOrganizador = new DefaultListModel<>();
        listEventosOrganizador.setModel(dlmEventosOrganizador);

        dlmEventosDisponiblesOrganizador = new DefaultListModel<>();
        listEventosDisponiblesOrganizador.setModel(dlmEventosDisponiblesOrganizador);

        dlmInscripciones = new DefaultListModel<>();
        listInscripciones.setModel(dlmInscripciones);

        dcbOrganizadorEvento = new DefaultComboBoxModel<>();
        organizadorComboBox.setModel(dcbOrganizadorEvento);

        dcbEventosInscripcion = new DefaultComboBoxModel<>();
        eventoInscripcionComboBox.setModel(dcbEventosInscripcion);

        dcbUsuariosInscripcion = new DefaultComboBoxModel<>();
        usuarioInscripcionComboBox.setModel(dcbUsuariosInscripcion);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void setEnumComboBox() {

        for (Ubicaciones ubicacion: Ubicaciones.values()) {
            eventoComboBox.addItem(ubicacion.getValue());
        }

        eventoComboBox.setSelectedIndex(-1);

        for (Estados estado: Estados.values()) {
            estadoComboBox.addItem(estado.getValue());
        }

        estadoComboBox.setSelectedIndex(-1);
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
