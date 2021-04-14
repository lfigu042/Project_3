package com.company;

import java.util.Arrays;
/**
 * * Implements a Graph. Uses an adjacency matrix to represent the graph.
 *
 *  @author Prof. Antonio Hernandez
 *  */

public class Graph implements GraphInterface{

    readInput myText = new readInput();
    private int verticesNumber = myText.getVertices(); //vertices
    private int[][] coordinateMatrix = myText.getMatrix(); //coordinates
    private int[][] edgeMatrix = new int[verticesNumber][verticesNumber]; //adjacency matrix

//  GETTERS
    public int[][]getCoordinateMatrix(){   return this.coordinateMatrix;   }
    public int[][] getEdgeMatrix()     {   setEdgeMatrix();   return this.edgeMatrix;    }
    public int getVerticesNumber()     {   return this.verticesNumber;     }

    public void addEdge(int i, int j, int d)    {
        edgeMatrix[i][j] = d;
        edgeMatrix[j][i] = d;
    }
    public void removeEdge(int i, int j)    {
        edgeMatrix[i][j] = 0;
        edgeMatrix[j][i] = 0;
    }
    /**
     * Calculates the distance b/w 2 points
     *
     * @param x1 x coordinate of first point
     * @param y1 y coordnate of first point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     * @return the distance between the two points as calculated with pythagorean
     *         theorem.
     */
    public static double calculatePointDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
    }
    /**
     * method to create an adjacency matrix
     */
    public void setEdgeMatrix() {
        int d;
        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                if (i == j) addEdge( i,  j, 0); // distance from a point to itself is always 0
                else { // the points are not equal to themselves and have not been calculated
                    d = (int)calculatePointDistance(coordinateMatrix[i][0], coordinateMatrix[i][1], coordinateMatrix[j][0], coordinateMatrix[j][1]);
                    addEdge( i,  j, d);
                }
            }
        }
        System.out.println("Edge matrix created");
    }
    public void printMatrix(int[][] m){ //print a matrix
        System.out.println("\nPrinting matrix:");
        for (int[] x : m) {
            for (int y : x)
                System.out.printf("%4d |",(y));
            System.out.println();
        }
    }
    /**
     * * Finds vertices adjacent to a given vertex.
     * @param v given vertex
     ** @return list of vertices adjacent to v stored in an array;
     * size of array = number of adjacent vertices
     * */
    public int[] findAdjacencyVertices(int v)    {
        int[] vert = new int[verticesNumber];
        int total = 0;
        for (int i=0; i<verticesNumber; i++)        {
            if (edgeMatrix[v][i] != 0)            {
                vert[total] = i;
                total++;
            }
        }
        return Arrays.copyOf(vert, total);
    }
    public String toString()    {
        String s = "";
        for (int i=0; i<verticesNumber; i++)        {
            for (int j=0; j<verticesNumber; j++)            {
                s += edgeMatrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}