package com.company;

import javax.swing.*;
import java.awt.*;

public class GraphDisplay extends JPanel {
    int width = 50; //width of nodes
    int height = 50; //height of nodes
    int labelX = 20;
    int labelY = 30;
    int fontSize = 20;

    Graph inputGraph = new Graph();
    private int[][] coordinates = inputGraph.getCoordinateMatrix(); //coordinates
    private int vertices = inputGraph.getVerticesNumber(); //vertices
    private int[][] edges = inputGraph.getEdgeMatrix(); //edges matrix with weight
    private int[] path = inputGraph.getShortestPath(); //shortest hamiltonian cycle
    private int distance = inputGraph.getShortestDistance();
    public void paint(Graphics g) {
        System.out.println("paint() ran");
        drawEdges(coordinates, vertices, g);
        drawVertices(coordinates, vertices, g);
        drawPathInfo(path,distance, g);
    }
    public void drawPathInfo(int[] p, int d, Graphics g){

    }
    public void drawEdges(int[][] m, int v, Graphics g){
        System.out.println("drawEdges() ran");
        int x1,x2,y1,y2;
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < v-1; i++){
            x1 = coordinates[i][0];
            y1 = coordinates[i][1];
            for(int j = i+1; j < v; j++){ //applying symmetry property
                System.out.print(i + " , " + j +" -> ");
                x2 = coordinates[j][0];
                y2 = coordinates[j][1];
                g.drawLine(x1+labelX , y1, x2+labelX , y2 );
            }
            System.out.println();
        }
    }
    public void drawVertices(int[][] m, int v, Graphics g){
        System.out.println("drawVertices() ran");
        int x,y;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));

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
