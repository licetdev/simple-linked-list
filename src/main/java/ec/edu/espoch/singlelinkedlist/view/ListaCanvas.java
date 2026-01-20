package ec.edu.espoch.singlelinkedlist.view;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Collections;

public class ListaCanvas extends Canvas {

    /** Snapshot de los datos entregados por el controlador */
    private List<Integer> values = Collections.emptyList();

    /** Índice del nodo a resaltar */
    private int highlightIndex = -1;

    // Medidas de dibujo
    private final double margin = 24;
    private final double nodeW = 100;
    private final double nodeH = 48;
    private final double spacing = 36;
    private final double baseY = 120;

    public ListaCanvas() {
        setWidth(1200);
        setHeight(280);
    }

    // ================================
    // API para el Controlador
    // ================================
    public void setValues(List<Integer> values) {
        this.values = (values == null) ? Collections.emptyList() : values;
    }

    public void setHighlightIndex(int idx) {
        this.highlightIndex = idx;
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
    }

    // ================================
    // RENDER
    // ================================
    public void render() {
        int n = values.size();

        double neededW = (n == 0)
                ? 900
                : margin * 2 + n * nodeW + (n - 1) * spacing + 180;

        setWidth(Math.max(900, neededW));
        setHeight(280);

        GraphicsContext g = getGraphicsContext2D();

        // Fondo
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Título
        g.setFill(Color.rgb(20, 20, 20));
        g.setFont(Font.font(16));
        g.fillText("Lista Simplemente Enlazada", margin, 28);

        g.setFill(Color.rgb(90, 90, 90));
        g.setFont(Font.font(12));
        g.fillText("Estructura: head → [dato | next] → ... → null", margin, 48);

        if (n == 0) {
            g.setFill(Color.rgb(120, 120, 120));
            g.setFont(Font.font(14));
            g.fillText("La lista está vacía.", margin, baseY);
            return;
        }

        drawHead(g);

        double x = margin;
        for (int i = 0; i < n; i++) {
            boolean hl = (i == highlightIndex);

            drawNode(g, x, baseY, String.valueOf(values.get(i)), hl);

            double midY = baseY + nodeH / 2;
            double nextX = x + nodeW + spacing;

            g.setStroke(Color.rgb(60, 60, 60));
            g.setLineWidth(2);

            // Flecha desde el campo "next"
            double fromX = x + nodeW * 0.75;
            g.strokeLine(fromX, midY, nextX, midY);
            arrowHead(g, fromX, midY, nextX, midY);

            if (i == n - 1) {
                g.setFill(Color.rgb(120, 120, 120));
                g.setFont(Font.font(12));
                g.fillText("null", nextX + 10, midY + 4);
            }

            x += nodeW + spacing;
        }
    }

    // ================================
    // DIBUJOS AUXILIARES
    // ================================
    private void drawHead(GraphicsContext g) {
        double headX = margin;
        double headY = baseY - 50;

        g.setFill(Color.rgb(50, 50, 50));
        g.setFont(Font.font(12));
        g.fillText("head", headX, headY);

        g.setStroke(Color.rgb(60, 60, 60));
        g.setLineWidth(2);

        g.strokeLine(headX + 14, headY + 6, headX + 14, baseY - 8);
        arrowHead(g, headX + 14, baseY - 8, headX + 14, baseY);
    }

    private void drawNode(GraphicsContext g, double x, double y, String text, boolean highlighted) {
        double splitX = x + nodeW * 0.65;

        g.setLineWidth(highlighted ? 3 : 2);
        g.setStroke(highlighted ? Color.rgb(25, 90, 200) : Color.rgb(40, 40, 40));
        g.strokeRoundRect(x, y, nodeW, nodeH, 16, 16);

        g.strokeLine(splitX, y, splitX, y + nodeH);

        g.setFill(Color.rgb(20, 20, 20));
        g.setFont(Font.font(14));
        g.fillText(text, x + 22, y + 30);

        g.setFill(Color.rgb(90, 90, 90));
        g.setFont(Font.font(11));
        g.fillText("next", splitX + 6, y + 28);

        if (highlighted) {
            g.setFill(Color.rgb(25, 90, 200));
            g.setFont(Font.font(11));
            g.fillText("FOUND", x + 6, y - 6);
        }
    }

    private void arrowHead(GraphicsContext g, double x1, double y1, double x2, double y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double len = 10;

        double a1 = angle - Math.PI / 8;
        double a2 = angle + Math.PI / 8;

        double ax1 = x2 - len * Math.cos(a1);
        double ay1 = y2 - len * Math.sin(a1);
        double ax2 = x2 - len * Math.cos(a2);
        double ay2 = y2 - len * Math.sin(a2);

        g.strokeLine(x2, y2, ax1, ay1);
        g.strokeLine(x2, y2, ax2, ay2);
    }
}
