package ec.edu.espoch.singlelinkedlist.model;

import java.util.ArrayList;
import java.util.List;

public class ListaSimple {
        private Node cabeza; // primer nodo de la lista

        // Constructor
        public ListaSimple() {
            this.cabeza = null;
        }

        // ================================
        // INSERTAR AL FINAL
        // ================================
        public void insertar(int dato) {
            Node nuevo = new Node(dato);

            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Node actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
        }

        // ================================
        // INSERTAR AL INICIO
        // ================================
        public void insertarInicio(int dato) {
            Node nuevo = new Node(dato);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }

        // ================================
        // BUSCAR UN DATO
        // ================================
        public boolean buscar(int dato) {
            Node actual = cabeza;

            while (actual != null) {
                if (actual.dato == dato) {
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        // ================================
        // ELIMINAR UN DATO
        // ================================
        public void eliminar(int dato) {
            if (cabeza == null) {
                System.out.println("La lista está vacía");
                return;
            }

            // Si el dato está en la cabeza
            if (cabeza.dato == dato) {
                cabeza = cabeza.siguiente;
                return;
            }

            Node actual = cabeza;
            Node anterior = null;

            while (actual != null && actual.dato != dato) {
                anterior = actual;
                actual = actual.siguiente;
            }

            if (actual == null) {
                System.out.println("Dato no encontrado");
            } else {
                anterior.siguiente = actual.siguiente;
            }
        }

        // ================================
        // RECORRER / MOSTRAR LA LISTA
        // ================================
        public void recorrer() {
            if (cabeza == null) {
                System.out.println("La lista está vacía");
                return;
            }

            Node actual = cabeza;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

    public int indexOf(int dato) {
        Node actual = cabeza;
        int index = 0;

        while (actual != null) {
            if (actual.dato == dato) {
                return index;
            }
            actual = actual.siguiente;
            index++;
        }
        return -1;
    }

    public List<Integer> toList() {
        List<Integer> result = new ArrayList<>();
        Node actual = cabeza;

        while (actual != null) {
            result.add(actual.dato);
            actual = actual.siguiente;
        }

        return result;
    }



}
