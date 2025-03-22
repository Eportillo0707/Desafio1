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
}

class ArbolPacientes {
    NodoArbol raiz;

    public ArbolPacientes() {
        raiz = new NodoArbol("Pacientes");
        NodoArbol genero = new NodoArbol("Género");
        NodoArbol presion = new NodoArbol("Presión");
        NodoArbol sangre = new NodoArbol("Tipo de Sangre");

        raiz.agregarHijo(genero);
        raiz.agregarHijo(presion);
        raiz.agregarHijo(sangre);

        genero.agregarHijo(new NodoArbol("Hombre"));
        genero.agregarHijo(new NodoArbol("Mujer"));

        presion.agregarHijo(new NodoArbol("Alta"));
        presion.agregarHijo(new NodoArbol("Media"));
        presion.agregarHijo(new NodoArbol("Baja"));

        sangre.agregarHijo(new NodoArbol("A"));
        sangre.agregarHijo(new NodoArbol("B"));
        sangre.agregarHijo(new NodoArbol("AB"));
        sangre.agregarHijo(new NodoArbol("O"));
    }

    public void agregarPaciente(Paciente paciente) {
        NodoArbol genero = raiz.hijos.get(0);
        for (NodoArbol sub : genero.hijos) {
            if (sub.categoria.equalsIgnoreCase(paciente.genero)) {
                sub.agregarPaciente(paciente);
                break;
            }
        }

        NodoArbol presion = raiz.hijos.get(1);
        for (NodoArbol sub : presion.hijos) {
            if (sub.categoria.equalsIgnoreCase(paciente.presion)) {
                sub.agregarPaciente(paciente);
                break;
            }
        }

        NodoArbol sangre = raiz.hijos.get(2);
        for (NodoArbol sub : sangre.hijos) {
            if (sub.categoria.equalsIgnoreCase(paciente.tipoSanguineo)) {
                sub.agregarPaciente(paciente);
                break;
            }
        }

    }


}

