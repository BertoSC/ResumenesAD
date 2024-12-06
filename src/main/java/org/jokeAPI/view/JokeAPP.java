package org.jokeAPI.view;

import org.jokeAPI.networkAndData.BromaDAO;
import org.jokeAPI.model.Broma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class JokeAPP {
        private JFrame frame;
        private JTextArea jokeArea;
        private JTextField langField;
        private JTextField categoryField;
        private BromaDAO daoJoke;

        public JokeAPP() {
            daoJoke = new BromaDAO();

            // Configuración de la ventana principal
            frame = new JFrame("Joke Finder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 400);
            frame.setLayout(new BorderLayout());

            // Área de texto para mostrar los chistes
            jokeArea = new JTextArea(10, 40);
            Font font = new Font("Arial", Font.PLAIN, 18);
            jokeArea.setFont(font);
            jokeArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(jokeArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Panel de botones
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

            JButton randomJokeButton = new JButton("Get Random Joke");
            JButton jokeByLangButton = new JButton("Get Joke by Language");
            JButton jokeByCategoryButton = new JButton("Get Joke by Category");

            buttonPanel.add(randomJokeButton);
            buttonPanel.add(jokeByLangButton);
            buttonPanel.add(jokeByCategoryButton);

            frame.add(buttonPanel, BorderLayout.EAST);

            // Panel inferior para entrada de datos
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(2, 2, 5, 5));
            inputPanel.add(new JLabel("Language:"));
            langField = new JTextField();
            inputPanel.add(langField);
            inputPanel.add(new JLabel("Category:"));
            categoryField = new JTextField();
            inputPanel.add(categoryField);

            frame.add(inputPanel, BorderLayout.SOUTH);

            // Agregar eventos a los botones
            randomJokeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fetchRandomJoke();
                }
            });

            jokeByLangButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fetchJokeByLanguage();
                }
            });

            jokeByCategoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fetchJokeByCategory();
                }
            });

            // Hacer visible la ventana
            frame.setVisible(true);
        }

        private void fetchRandomJoke() {
            try {
                Broma randomJoke = daoJoke.getChiste();
                jokeArea.setText(randomJoke.toString());
            } catch (Exception ex) {
                jokeArea.setText("Error fetching random joke: " + ex.getMessage());
            }
        }

        private void fetchJokeByLanguage() {
            String lang = langField.getText().trim();
            if (lang.isEmpty()) {
                jokeArea.setText("Please enter a language code.");
                return;
            }
            try {
                Broma jokeByLang = daoJoke.getChisteByLang(lang);
                jokeArea.setText(jokeByLang.toString());
            } catch (Exception ex) {
                jokeArea.setText("Error fetching joke by language: " + ex.getMessage());
            }
        }

        private void fetchJokeByCategory() {
            String category = categoryField.getText().trim();
            if (category.isEmpty()) {
                jokeArea.setText("Please enter a category.");
                return;
            }
            try {
                Broma jokeByCategory = daoJoke.getChisteByCategory(category);
                jokeArea.setText(jokeByCategory.toString());
            } catch (Exception ex) {
                jokeArea.setText("Error fetching joke by category: " + ex.getMessage());
            }
        }

    public static void main(String[] args) {
        new JokeAPP();
    }


}
