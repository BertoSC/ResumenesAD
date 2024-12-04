package org.BBDDfilosofos.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.BBDDfilosofos.controlador.FilosofoController;
import java.sql.Connection;
import java.sql.SQLException;

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
        frame.getContentPane().setLayout(new GridLayout(6, 2));

        // Campos para mostrar los datos
        JLabel lblId = new JLabel("ID:");
        idField = new JTextField();
        idField.setEditable(false);
        frame.getContentPane().add(lblId);
        frame.getContentPane().add(idField);

        JLabel lblNome = new JLabel("Nome:");
        nomeField = new JTextField();
        nomeField.setEditable(false);
        frame.getContentPane().add(lblNome);
        frame.getContentPane().add(nomeField);

        JLabel lblApelidos = new JLabel("Apelidos:");
        apelidosField = new JTextField();
        apelidosField.setEditable(false);
        frame.getContentPane().add(lblApelidos);
        frame.getContentPane().add(apelidosField);

        JLabel lblIdade = new JLabel("Idade:");
        idadeField = new JTextField();
        idadeField.setEditable(false);
        frame.getContentPane().add(lblIdade);
        frame.getContentPane().add(idadeField);

        JLabel lblDataNacemento = new JLabel("Data Nacemento:");
        dataNacementoField = new JTextField();
        dataNacementoField.setEditable(false);
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

        // Inicializar con los primeros datos
        try {
            controller.setDatosActuales();
            updateFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFields() throws SQLException {
        // Actualiza los campos de la interfaz con los datos del ResultSet
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
