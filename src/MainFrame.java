import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class MainFrame extends JFrame {
    ArbolPacientes arbol;
    DefaultMutableTreeNode rootNode;
    JTree tree;
    DefaultTreeModel treeModel;
    public MainFrame() {
        arbol = new ArbolPacientes();
        setTitle("Gestión de Pacientes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void actulizarArbol() {
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
