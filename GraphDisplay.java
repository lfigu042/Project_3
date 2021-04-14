package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphDisplay extends JPanel {

    Graph inputGraph = new Graph();
    private int[][] coordinates = inputGraph.getCoordinateMatrix(); //coordinates
    private int vertices = inputGraph.getVerticesNumber(); //vertices
    private int[][] edges = inputGraph.getEdgeMatrix(); //edges matrix with weight
    private int[] shortestPath = inputGraph.getShortestPath(); //shortest hamiltonian cycle
    
    public void paint(Graphics g) {
        System.out.println("paint() ran");
        drawVertices(coordinates, vertices, g);

//      draw edge (1,2)
//        g.setColor(Color.BLACK);
//        g.drawLine(x1, y1+height/2, x2, y2+height/2);
    }
    public void drawVertices(int[][] m, int v, Graphics g){
        System.out.println("drawVertices() ran");
        int x,y;
        int width = 50;
        int height = 50;
        int labelX = 15;
        int labelY = 30;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        for (int i = 0; i < v; i++){
            x = m[i][0];
            y = m[i][1];
            g.setColor(Color.ORANGE);
            g.fillOval(x, y - labelY, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x, y - labelY, width, height);
            g.drawString(String.valueOf(i), x+labelX, y);
        }
    }
}
