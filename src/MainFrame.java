import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class MainFrame extends JFrame {
    ArbolPacientes arbol = new ArbolPacientes();
    DefaultMutableTreeNode rootNode;
    JTree tree;
    DefaultTreeModel treeModel;
    public MainFrame() {
        super("Gestión de Pacientes");
        setSize(600, 400); // Tamaño recomendado para formularios
        setLocationRelativeTo(null); // Centra respecto a la pantalla
        setResizable(false);//Evitar que el usuario redimensione
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra solo esta ventana
        setLayout(new BorderLayout());

        JButton addPatientButton = new JButton("Agregar Paciente");
        addPatientButton.addActionListener(e -> {
            new AddPatientFrame(arbol, this);
        });

        JPanel topPanel = new JPanel();
        topPanel.add(addPatientButton);
        add(topPanel, BorderLayout.NORTH);

        rootNode = new DefaultMutableTreeNode("Pacientes");
        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);
        add(new JScrollPane(tree), BorderLayout.CENTER);

        setVisible(true);

    }

    public void actualizarArbol() {
        rootNode.removeAllChildren();
        agregarNodos(arbol.raiz, rootNode);
        treeModel.reload();

    }

    private void agregarNodos(NodoArbol nodo, DefaultMutableTreeNode treeNode){
        if (nodo == null) return;


        for (NodoArbol hijo : nodo.hijos) {


           DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(hijo.categoria);
           treeNode.add(nuevoNodo);
           agregarNodos(hijo, nuevoNodo);

        }
        for (Paciente paciente: nodo.pacientes){
            treeNode.add(new DefaultMutableTreeNode(paciente.toString()));
        }
    }

}
