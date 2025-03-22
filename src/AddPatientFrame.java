import javax.swing.*;
import java.awt.*;

public class AddPatientFrame extends JFrame {
    public AddPatientFrame(ArbolPacientes arbol, MainFrame mainFrame) {
        setTitle("Agregar Paciente");
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Hombre", "Mujer"});
        JComboBox<String> bloodBox = new JComboBox<>(new String[]{"A", "B", "AB", "O"});
        JComboBox<String> pressureBox = new JComboBox<>(new String[]{"alta", "media", "baja"});
        JButton addButton = new JButton("Agregar");

        addButton.addActionListener(e -> {
            String nombre = nameField.getText();
            String genero = (String) genderBox.getSelectedItem();
            String tipoSanguineo = (String) bloodBox.getSelectedItem();
            String presion = (String) pressureBox.getSelectedItem();
            arbol.agregarPaciente(new Paciente(nombre, genero, tipoSanguineo, presion));
            mainFrame.actulizarArbol();
            dispose();
        });

        add(new JLabel("Nombre:"));
        add(nameField);
        add(new JLabel("Género:"));
        add(genderBox);
        add(new JLabel("Tipo de sangre:"));
        add(bloodBox);
        add(new JLabel("Presión:"));
        add(pressureBox);
        add(addButton);

        setVisible(true);
    }
}
