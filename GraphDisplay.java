package com.company;
/**
 * Made by:
 *  Laura Figueroa 4918449
 *  Martin Alvarez 5856597
 *  Victoria Lariot 6124058
 *
 * Professor: Antonio Hernandez
 * Class: COP 4534
 * Section: UO1
 */
import javax.swing.*;
import java.awt.*;

public class GraphDisplay extends JPanel {

    int screen_W, screen_H,offSet;
    int width = 50; //width of nodes
    int height = 50; //height of nodes
    int labelX = 20;
    int labelY = 30;
    int labelsFontSize = 20;
    int weightsFontSize = 12;
    String weight;

    Graph inputGraph = new Graph();
    private final int[][] coordinates = inputGraph.getCoordinateMatrix(); //coordinates
    private final int numVertices = inputGraph.getVerticesNumber(); //vertices
    private final int[][] edges = inputGraph.getEdgeMatrix(); //edges matrix with weight
    private final int[] localSearchPath = inputGraph.getShortestPathLocal(); //shortest hamiltonian cycle
    private final int localSearchDistance = inputGraph.getShortestDistanceLocal();
    private final int[] exhaustiveSearchPath = inputGraph.getShortestPathExhaustive(); //shortest hamiltonian cycle
    private final int exhaustiveSearchDistance = inputGraph.getShortestDistanceExhaustive();

    public GraphDisplay(int W, int H){ //CONSTRUCTOR, get window measurements from FrameDisplay
        screen_W = W;
        screen_H = H;
        offSet = screen_W/2;
    }

    public void paint(Graphics g) {
        System.out.println("paint() ran");
        drawEdges(g);
        drawPathInfo(g);
        drawVertices(g);
    }
    public void drawShortestPath(int s, int e, Graphics g, int offset){
        int x1 = coordinates[s][0];
        int y1 = coordinates[s][1];
        int x2 = coordinates[e][0];
        int y2 = coordinates[e][1];

        g.setColor(Color.GREEN);

        g.drawLine(x1+labelX +offset, y1, x2+labelX +offset, y2 );
    }
    public void drawPathWeight(int s, int e, Graphics g ,int offset){
        int x,y;
        weight = String.valueOf(edges[s][e]); //weight value of an edge

//        //draw weight in the middle point of each edge
        x = offset+ labelX + ((coordinates[s][0] + coordinates[e][0]) / 2);
        y = 10 + ((coordinates[s][1] + coordinates[e][1]) / 2);

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("TimesRoman", Font.BOLD, weightsFontSize));
        g.drawString(weight, x, y);
    }
    public void drawPathInfo( Graphics g){
        int l1 = localSearchPath.length;
        int l2 = exhaustiveSearchPath.length;
        int indent = 25;
        int lines = screen_H-100;
        String outputLocalSearch = "";
        String outputExhaustiveSearch = "";

        g.setFont(new Font("TimesRoman", Font.PLAIN, labelsFontSize -5));

        for (int i = 0; i < l1 ; i++) {
            if(i == l1 - 1) {
                 outputLocalSearch += localSearchPath[i];
            }else {
                drawShortestPath(localSearchPath[i], localSearchPath[i+1],g,0);
                drawPathWeight(localSearchPath[i], localSearchPath[i+1],g, 0);
                outputLocalSearch += localSearchPath[i] + " -> ";
            }
        }
        for (int i = 0; i < l2 ; i++) {
            if(i == l2 - 1) {
                outputExhaustiveSearch += exhaustiveSearchPath[i];
            }else {
                drawShortestPath(exhaustiveSearchPath[i], exhaustiveSearchPath[i+1],g, offSet);
                drawPathWeight(exhaustiveSearchPath[i], exhaustiveSearchPath[i+1],g, offSet);
                outputExhaustiveSearch += exhaustiveSearchPath[i] + " -> ";
            }
        }
        g.setColor(Color.black);

        g.drawString("Shortest path with local search: ", indent, lines);
        g.drawString("Shortest path with exhaustive search: ", indent + offSet, lines);

        g.drawString(outputLocalSearch, indent, lines + 20);
        g.drawString("Total distance: " + localSearchDistance, indent, lines + 40); //local search

        g.drawString(outputExhaustiveSearch, indent+offSet, lines + 20);
        g.drawString("Total distance: " + exhaustiveSearchDistance, indent + offSet, lines + 40); //exhaustive search

    }
    public void drawEdges(Graphics g){
        int x1,x2,y1,y2;
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < numVertices-1; i++){
            x1 = coordinates[i][0];
            y1 = coordinates[i][1];
            for(int j = i+1; j < numVertices; j++){ //applying symmetry property
                System.out.print(i + " , " + j +" -> ");
                x2 = coordinates[j][0];
                y2 = coordinates[j][1];
                g.drawLine(x1+labelX , y1, x2+labelX , y2 ); //line for local search graph (left)
                g.drawLine(x1+offSet+ labelX , y1, x2+ offSet + labelX , y2 ); //line for exhaustive search graph (right)
            }
            System.out.println();
        }
    }
    public void drawVertices( Graphics g){
        int x,y;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, labelsFontSize));

        for (int i = 0; i < numVertices; i++){
            x = coordinates[i][0];
            y = coordinates[i][1];

            g.setColor(Color.ORANGE);
            g.fillOval(x, y - labelY, width, height); //ovals for local search graph (left)
            g.fillOval(x + offSet, y - labelY, width, height); //line for exhaustive search graph (right)

            g.setColor(Color.BLACK);

            g.drawOval(x, y - labelY, width, height); //ovals for local search graph (left)
            g.drawString(String.valueOf(i), x+labelX, y); //labels for local search graph (left)

            g.drawOval(x + offSet, y - labelY, width, height); //ovals for exhaustive search graph (right)
            g.drawString(String.valueOf(i), x+labelX + offSet, y); //label for exhaustive search graph (right)
        }
    }
}