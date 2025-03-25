import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame(){
        setTitle("Sistema de Login");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de formulario con GridBagLayout para máximo control
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Márgenes uniformes
        gbc.anchor = GridBagConstraints.WEST;

        // Componentes
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField(15);

        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(15);

        JButton loginButton = new JButton("Ingresar");
        loginButton.setPreferredSize(new Dimension(100, 30));

        JLabel statusLabel = new JLabel(" ", JLabel.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        // Configurar el mismo tamaño mínimo para los labels
        Dimension labelSize = new Dimension(80, 20); // Ancho fijo para ambos labels
        userLabel.setPreferredSize(labelSize);
        passLabel.setPreferredSize(labelSize);

        // Organización de componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        // Panel para el botón (centrado)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        // Agregar componentes al panel principal
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ActionListener
        loginButton.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                statusLabel.setText("Ambos campos son requeridos");
                return;
            }

            if (user.equals("admin") && pass.equals("1234")) {
                new MainFrame().setVisible(true);
                dispose();
            } else {
                statusLabel.setText("Credenciales incorrectas");
                passField.setText("");
            }
        });

        // Configurar Enter para iniciar sesión
        getRootPane().setDefaultButton(loginButton);

        add(mainPanel);
        setVisible(true);
    }
}

