import javax.swing.*;
import java.awt.*;

public class AddPatientFrame extends JFrame {
    public AddPatientFrame(ArbolPacientes arbol, MainFrame mainFrame) {
        super("Registro de Nuevo Paciente");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con borde y espacio interno
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de formulario con GridBagLayout para mayor control
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes del formulario
        JLabel nameLabel = new JLabel("Nombre completo:");
        JTextField nameField = new JTextField(20);
        nameField.setToolTipText("Ingrese el nombre completo del paciente");

        JLabel genderLabel = new JLabel("Género:");
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Seleccione...", "Masculino", "Femenino", "Otro"});
        genderBox.setSelectedIndex(0); // Selección por defecto

        JLabel bloodLabel = new JLabel("Tipo sanguíneo:");
        JComboBox<String> bloodBox = new JComboBox<>(new String[]{"Seleccione...", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        bloodBox.setSelectedIndex(0);

        JLabel pressureLabel = new JLabel("Presión arterial:");
        JComboBox<String> pressureBox = new JComboBox<>(new String[]{"Seleccione...", "Normal (120/80)", "Elevada", "Hipertensión Grado 1", "Hipertensión Grado 2", "Crisis hipertensiva"});
        pressureBox.setSelectedIndex(0);

        // Añadir componentes al formulario con GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(genderLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(genderBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(bloodLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(bloodBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(pressureLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(pressureBox, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Guardar Paciente");
        addButton.setBackground(new Color(76, 175, 80)); // Verde
        addButton.setForeground(Color.WHITE);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(addButton);

        // Ensamblar la interfaz
        mainPanel.add(new JLabel("Complete los datos del paciente:"), BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // ActionListener para el botón de guardar
        addButton.addActionListener(e -> {
            if (validarFormulario(nameField, genderBox, bloodBox, pressureBox)) {
                String nombre = nameField.getText().trim();
                String genero = (String) genderBox.getSelectedItem();
                String tipoSanguineo = (String) bloodBox.getSelectedItem();
                String presion = (String) pressureBox.getSelectedItem();

                if (!arbol.pacienteExiste(nombre)) {
                    arbol.agregarPaciente(new Paciente(nombre, genero, tipoSanguineo, presion));
                    mainFrame.actualizarArbol();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "El paciente ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private boolean validarFormulario(JTextField nameField, JComboBox<String> genderBox,
                                      JComboBox<String> bloodBox, JComboBox<String> pressureBox) {

        if (nameField.getText().trim().isEmpty() || !nameField.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s']+$") || nameField.getText().length() <= 5) {

            String mensaje = "";
            if (nameField.getText().trim().isEmpty()) {
                mensaje = "El nombre no puede estar vacío";
            } else if (!nameField.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s']+$")) {
                mensaje = "Solo se permiten letras y espacios";
            } else {
                mensaje = "El nombre debe tener más de 5 caracteres";
            }

            JOptionPane.showMessageDialog(this, mensaje,
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (genderBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un género", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (bloodBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione tipo sanguíneo", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (pressureBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione presión arterial", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

}
