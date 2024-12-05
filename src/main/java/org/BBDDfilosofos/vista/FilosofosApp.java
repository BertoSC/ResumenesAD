package org.BBDDfilosofos.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.BBDDfilosofos.controlador.FilosofoController;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FilosofosApp {
    private JFrame frame;
    private JTextField idField;
    private JTextField nomeField;
    private JTextField apelidosField;
    private JTextField idadeField;
    private JTextField dataNacementoField;
    private FilosofoController controller;

    public FilosofosApp(Connection con) throws SQLException {
        controller = new FilosofoController(con);
        initialize();
    }

    private void initialize() {
        // Frame principal
        frame = new JFrame("Filosofos");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(8, 2));

        // Campos para mostrar los datos
        JLabel lblId = new JLabel("ID:");
        idField = new JTextField();
        idField.setEditable(false);
        frame.getContentPane().add(lblId);
        frame.getContentPane().add(idField);

        JLabel lblNome = new JLabel("Nome:");
        nomeField = new JTextField();
        nomeField.setEditable(true);
        frame.getContentPane().add(lblNome);
        frame.getContentPane().add(nomeField);

        JLabel lblApelidos = new JLabel("Apelidos:");
        apelidosField = new JTextField();
        apelidosField.setEditable(true);
        frame.getContentPane().add(lblApelidos);
        frame.getContentPane().add(apelidosField);

        JLabel lblIdade = new JLabel("Idade:");
        idadeField = new JTextField();
        idadeField.setEditable(true);
        frame.getContentPane().add(lblIdade);
        frame.getContentPane().add(idadeField);

        JLabel lblDataNacemento = new JLabel("Data Nacemento:");
        dataNacementoField = new JTextField();
        dataNacementoField.setEditable(true);
        frame.getContentPane().add(lblDataNacemento);
        frame.getContentPane().add(dataNacementoField);

        // Botones de navegación
        JButton btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (controller.previo()) {
                        controller.setDatosActuales();  // Actualizar los datos en los campos
                        updateFields();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnAnterior);

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (controller.siguiente()) {
                        controller.setDatosActuales();  // Actualizar los datos en los campos
                        updateFields();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnSiguiente);

        // Nuevos botones
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome= nomeField.getText();
                String apell=apelidosField.getText();
                int ed= Integer.parseInt(idadeField.getText());
                String fecha= dataNacementoField.getText();
                Boolean select=true;
                try {
                    controller.actualizarDatos(nome, apell,ed,fecha,select);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println("Botón Actualizar presionado");
            }
        });
        frame.getContentPane().add(btnActualizar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para borrar el registro
                System.out.println("Botón Borrar presionado");
            }
        });
        frame.getContentPane().add(btnBorrar);

        JButton btnAñadir = new JButton("Añadir");
        btnAñadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome= nomeField.getText();
                String apell=apelidosField.getText();
                int ed= Integer.parseInt(idadeField.getText());
                String fecha= dataNacementoField.getText();
                Boolean select=false;
                try {
                    controller.actualizarDatos(nome, apell,ed,fecha,select);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println("Botón Añadir presionado");
            }
        });
        frame.getContentPane().add(btnAñadir);

        // Inicializar con los primeros datos
        try {
            controller.setDatosActuales();
            updateFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFields() throws SQLException {

        idField.setText(String.valueOf(controller.getId()));
        nomeField.setText(controller.getNome());
        apelidosField.setText(controller.getApelidos());
        idadeField.setText(String.valueOf(controller.getIdade()));
        dataNacementoField.setText(controller.getDataNacemento().toString());
    }

    public void show() {
        frame.setVisible(true);
    }
}