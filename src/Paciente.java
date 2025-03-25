import java.util.ArrayList;
import java.util.List;

public class Paciente {
    String nombre;
    String genero;
    String tipoSanguineo;
    String presion;

    public Paciente(String nombre, String genero, String tipoSanguineo, String presion) {
        this.nombre = nombre;
        this.genero = genero;
        this.tipoSanguineo = tipoSanguineo;
        this.presion = presion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class NodoArbol {
    String categoria;
    List<NodoArbol> hijos;
    List<Paciente> pacientes;

    public NodoArbol(String categoria) {
        this.categoria = categoria;
        this.hijos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public void agregarHijo(NodoArbol hijo) {
        hijos.add(hijo);
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Método para verificar si un paciente existe en este nodo
    public boolean contienePaciente(String nombre) {
        return pacientes.stream()
                .anyMatch(p -> p.nombre.equalsIgnoreCase(nombre.trim()));
    }

    // Método para buscar en todo el árbol
    public boolean existePacienteEnArbol(String nombre) {
        // Buscar en este nodo
        if (contienePaciente(nombre)) {
            return true;
        }

        // Buscar recursivamente en los hijos
        for (NodoArbol hijo : hijos) {
            if (hijo.existePacienteEnArbol(nombre)) {
                return true;
            }
        }

        return false;
    }
}

class ArbolPacientes {
    NodoArbol raiz;

    public boolean pacienteExiste(String nombre) {
        if (raiz == null) return false;
        return raiz.existePacienteEnArbol(nombre);
    }

    public ArbolPacientes() {
        raiz = new NodoArbol("Pacientes");
        NodoArbol genero = new NodoArbol("Género");
        NodoArbol sangre = new NodoArbol("Tipo Sanguíneo");
        NodoArbol presion = new NodoArbol("Presión Arterial");

        raiz.agregarHijo(genero);
        raiz.agregarHijo(sangre);
        raiz.agregarHijo(presion);

        genero.agregarHijo(new NodoArbol("Masculino"));
        genero.agregarHijo(new NodoArbol("Femenino"));
        genero.agregarHijo(new NodoArbol("Otro"));

        sangre.agregarHijo(new NodoArbol("A+"));
        sangre.agregarHijo(new NodoArbol("A-"));
        sangre.agregarHijo(new NodoArbol("B+"));
        sangre.agregarHijo(new NodoArbol("B-"));
        sangre.agregarHijo(new NodoArbol("AB+"));
        sangre.agregarHijo(new NodoArbol("AB-"));
        sangre.agregarHijo(new NodoArbol("O+"));
        sangre.agregarHijo(new NodoArbol("O-"));

        presion.agregarHijo(new NodoArbol("Normal (120/80)"));
        presion.agregarHijo(new NodoArbol("Elevada"));
        presion.agregarHijo(new NodoArbol("Hipertensión Grado 1"));
        presion.agregarHijo(new NodoArbol("Hipertensión Grado 2"));
        presion.agregarHijo(new NodoArbol("Crisis hipertensiva"));
    }

    public void agregarPaciente(Paciente paciente) {

        NodoArbol genero = raiz.hijos.get(0);
        for (NodoArbol sub : genero.hijos) {
            if (sub.categoria.equalsIgnoreCase(paciente.genero)) {
                sub.agregarPaciente(paciente);
                break;
            }
        }

        NodoArbol sangre = raiz.hijos.get(1);
        for (NodoArbol sub : sangre.hijos) {
            if (sub.categoria.equalsIgnoreCase(paciente.tipoSanguineo)) {
                sub.agregarPaciente(paciente);
                break;
            }
        }

        NodoArbol presion = raiz.hijos.get(2);
        for (NodoArbol sub : presion.hijos) {
            if (sub.categoria.equalsIgnoreCase(paciente.presion)) {
                sub.agregarPaciente(paciente);
                break;
            }
        }

    }


}

