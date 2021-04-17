package com.company;

import javax.swing.*;
import java.awt.*;

public class GraphDisplay extends JPanel {

    int screen_W, screen_H;
    int width = 50; //width of nodes
    int height = 50; //height of nodes
    int labelX = 20;
    int labelY = 30;
    int fontSize = 20;

    public GraphDisplay(int W, int H){ //CONSTRUCTOR, get window measurements from FrameDisplay
        screen_W = W;
        screen_H = H;
        System.out.println("screen dimensions: "+ screen_W+ " x "+ screen_H);
    }

    Graph inputGraph = new Graph();
    private final int[][] coordinates = inputGraph.getCoordinateMatrix(); //coordinates
    private final int numVertices = inputGraph.getVerticesNumber(); //vertices
    private final int[][] edges = inputGraph.getEdgeMatrix(); //edges matrix with weight
    private final int[] path = inputGraph.getShortestPath(); //shortest hamiltonian cycle
    private final int distance = inputGraph.getShortestDistance();

    public void paint(Graphics g) {
        System.out.println("paint() ran");
        drawEdges(g);
        drawPathInfo(g);
        drawVertices(g);
    }
    public void drawShortestPath(int s, int e, Graphics g){
        System.out.println("drawShortestPath() ran");
        int x1 = coordinates[s][0];
        int y1 = coordinates[s][1];
        int x2 = coordinates[e][0];
        int y2 = coordinates[e][1];

        g.setColor(Color.GREEN);
        g.drawLine(x1+labelX , y1, x2+labelX , y2 );
    }
    public void drawPathInfo( Graphics g){
        int l = path.length;
        int indent = 25;
        int lines = screen_H-100;
        String output = "";

        System.out.println("drawPathInfo() ran");
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize-5));

        for (int i = 0; i < l ; i++) {
            if(i == l - 1) {
                 output += path[i];
            }else {
                drawShortestPath(path[i],path[i+1],g);
                output += path[i] + " -> ";
            }
        }
        g.setColor(Color.black);
        g.drawString("Shortest path: ", indent, lines);
        g.drawString(output, indent, lines += 20);
        g.drawString("Total distance: " + distance , indent, lines += 20);

    }
    public void drawEdges(Graphics g){
        System.out.println("drawEdges() ran");
        int x1,x2,y1,y2;
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < numVertices-1; i++){
            x1 = coordinates[i][0];
            y1 = coordinates[i][1];
            for(int j = i+1; j < numVertices; j++){ //applying symmetry property
                System.out.print(i + " , " + j +" -> ");
                x2 = coordinates[j][0];
                y2 = coordinates[j][1];
                g.drawLine(x1+labelX , y1, x2+labelX , y2 );
            }
            System.out.println();
        }
    }
    public void drawVertices( Graphics g){
        System.out.println("drawVertices() ran");
        int x,y;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));

        for (int i = 0; i < numVertices; i++){
            x = coordinates[i][0];
            y = coordinates[i][1];
            g.setColor(Color.ORANGE);
            g.fillOval(x, y - labelY, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x, y - labelY, width, height);
            g.drawString(String.valueOf(i), x+labelX, y);
        }
    }
}