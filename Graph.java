package com.company;

import java.util.*;
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
    private int[] shortestPath = new int[verticesNumber];
    
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
     * Given an array, generates random permutation of values in [0, n-1], where n
     * is size of given array; random permutation will be stored in the array. Uses
     * Fisher-Yates shuffle algorithm.
     *
     * @param a output array
     */
    public void randomPermutation(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        Random rnd = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            // generates a random index in [0,i]
            int randomLocation = rnd.nextInt(i + 1);

            if (randomLocation != i) {
                // swap a[i] and a[randomLocation]
                int temp = a[i];
                a[i] = a[randomLocation];
                a[randomLocation] = temp;
            }
        }
    }
    
    /**
     * Calculates distance of given route.
     *
     * @param a route
     *
     * @return distance
     *
     */
    int totalDistance(int[] a) {
        int n = a.length;
        // add weights of all edges in
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            int weight = edgeMatrix[a[i]][a[(i + 1) % n]];
            totalWeight += weight;
        }
        return totalWeight;
    }
    
    /**
     * Finds a shortest route that visits every vertex exactly once and returns to
     * the starting point. Uses local search, so optimal solution is not obtained,
     * in general.
     *
     * @param shortestRoute array with thea shortest path(return value)
     *
     * @return shortest distance
     */
    public int TSP_localSearch(int[] shortestRoute) {
        int bestDistance;
        // generate initial solution as a random permutation
        int[] a = new int[verticesNumber];
        randomPermutation(a);

        // shortestRoute = initial solution
        System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
        bestDistance = totalDistance(shortestRoute);

        boolean betterSolutionFound;
        /*
         * Loop will continue as long as there is a neighbor that improves best distance
         * obtained so far
         */
        do {
            betterSolutionFound = false;
            PermutationNeighborhood pn = PermutationNeighborhood(shortestRoute);

            while (pn.hasNext()) {
                a = pn.next();
                int currentDistance = totalDistance(a);
                if (currentDistance < bestDistance) {
                    // shortestRoute = current Solution
                    System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
                    bestDistance = currentDistance;
                    betterSolutionFound = true;
                }
            }
        } while (betterSolutionFound);
        System.arraycopy(shortestRoute, 0, shortestPath, 0, verticesNumber);
        return bestDistance;
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
