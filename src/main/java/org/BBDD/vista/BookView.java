package org.BBDD.vista;

import org.BBDD.Book;
import org.BBDD.controller.IBookController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookView extends JFrame implements IBookView {
    private IBookController controller;

    private JLabel lblCover, lblTitle, lblAuthor, lblYear, lblISBN, lblID, lblAvailable;
    private JTextField txtTitle, txtAuthor, txtYear, txtISBN, txtID;
    private JCheckBox chkAvailable;
    private JButton btnPrevious, btnNext, btnUpdate, btnDelete;

    public BookView() {
        setTitle("Gestión de Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Panel de portada
        lblCover = new JLabel();
        lblCover.setHorizontalAlignment(SwingConstants.CENTER);
        lblCover.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(lblCover, BorderLayout.WEST);

        // Panel de información
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        lblTitle = new JLabel("Título:");
        txtTitle = new JTextField();
        lblAuthor = new JLabel("Autor:");
        txtAuthor = new JTextField();
        lblYear = new JLabel("Año:");
        txtYear = new JTextField();
        lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField();
        lblID = new JLabel("ID:");
        txtID = new JTextField();
        txtID.setEditable(false); // ID no se puede editar
        lblAvailable = new JLabel("Disponible:");
        chkAvailable = new JCheckBox();

        infoPanel.add(lblTitle);
        infoPanel.add(txtTitle);
        infoPanel.add(lblAuthor);
        infoPanel.add(txtAuthor);
        infoPanel.add(lblYear);
        infoPanel.add(txtYear);
        infoPanel.add(lblISBN);
        infoPanel.add(txtISBN);
        infoPanel.add(lblID);
        infoPanel.add(txtID);
        infoPanel.add(lblAvailable);
        infoPanel.add(chkAvailable);

        add(infoPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnPrevious = new JButton("Anterior");
        btnNext = new JButton("Siguiente");
        btnUpdate = new JButton("Actualizar");
        btnDelete = new JButton("Borrar");

        buttonPanel.add(btnPrevious);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        btnPrevious.addActionListener(e -> controller.getBook(controller.getPreviousId(Integer.parseInt(txtID.getText()))));
        btnNext.addActionListener(e -> controller.getBook(controller.getNextId(Integer.parseInt(txtID.getText()))));
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
    }

    public void setController(IBookController controller) {
        this.controller = controller;
        controller.getBook(controller.getFirstId());
    }

    @Override
    public void setBookTitle(String title) {
        txtTitle.setText(title);
    }

    @Override
    public void setAuthor(String author) {
        txtAuthor.setText(author);
    }

    @Override
    public void setYear(int year) {
        txtYear.setText(String.valueOf(year));
    }

    @Override
    public void setISBN(String isbn) {
        txtISBN.setText(isbn);
    }

    @Override
    public void setID(int id) {
        txtID.setText(String.valueOf(id));
    }

    @Override
    public void setAvailable(boolean available) {
        chkAvailable.setSelected(available);
    }

    @Override
    public void setCover(byte[] cover) {
        if (cover != null) {
            ImageIcon icon = new ImageIcon(cover);
            Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            lblCover.setIcon(new ImageIcon(img));
        } else {
            lblCover.setIcon(null);
        }
    }

    private void updateBook() {
        int id = Integer.parseInt(txtID.getText());
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String isbn = txtISBN.getText();
        int year = Integer.parseInt(txtYear.getText());
        boolean available = chkAvailable.isSelected();
        controller.updateBook(id, isbn, title, author, year, available, null);
    }

    private void deleteBook() {
        int id = Integer.parseInt(txtID.getText());
        controller.deleteBook(id);
        controller.getBook(controller.getFirstId());
    }
}
