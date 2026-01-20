package ec.edu.espoch.singlelinkedlist.controller;

import ec.edu.espoch.singlelinkedlist.model.ListaSimple;
import ec.edu.espoch.singlelinkedlist.view.ListaCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;


public class ViewList {
    @FXML
    private TextField txtValue;

    @FXML
    private StackPane canvasHolder;

    private ListaCanvas canvas;

    private final ListaSimple lista = new ListaSimple();

    // ================================
    // INICIALIZACIÓN
    // ================================
    @FXML
    private void initialize() {
        canvas = new ListaCanvas();
        canvasHolder.getChildren().add(canvas);

        canvas.clearHighlight();
        refreshView();
    }

    // ================================
    // INSERTAR AL FINAL
    // ================================
    @FXML
    private void insertar() {
        Integer v = readInt();
        if (v == null) {
            return;
        }

        lista.insertar(v);
        canvas.clearHighlight();
        refreshView();
        focusInput();
    }

    // ================================
    // INSERTAR AL INICIO
    // ================================
    @FXML
    private void insertarInicio() {
        Integer v = readInt();
        if (v == null) {
            return;
        }

        lista.insertarInicio(v);
        canvas.clearHighlight();
        refreshView();
        focusInput();
    }

    // ================================
    // ELIMINAR
    // ================================
    @FXML
    private void eliminar() {
        Integer v = readInt();
        if (v == null) {
            return;
        }

        lista.eliminar(v);
        canvas.clearHighlight();
        refreshView();
        focusInput();
    }

    // ================================
    // BUSCAR
    // ================================
    @FXML
    private void buscar() {
        Integer v = readInt();
        if (v == null) {
            return;
        }

        int index = lista.indexOf(v); // ← explicado abajo
        if (index >= 0) {
            canvas.setHighlightIndex(index);
        } else {
            canvas.clearHighlight();
        }
        refreshView();
    }

    // ================================
    // MÉTODOS AUXILIARES
    // ================================
    private Integer readInt() {
        try {
            String s = (txtValue.getText() == null) ? "" : txtValue.getText().trim();
            if (s.isEmpty()) {
                return null;
            }
            return Integer.valueOf(s);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private void refreshView() {
        canvas.setValues(lista.toList());
        canvas.render();
    }

    private void focusInput() {
        txtValue.selectAll();
        txtValue.requestFocus();
    }

}
