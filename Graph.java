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
    private int[][] edgeMatrix = setEdgeMatrix();  //adjacency matrix
    private int[] shortestPath = TSP_localSearch();
    private int shortestDistance = totalDistance(shortestPath);
    //  GETTERS
    public int[][]getCoordinateMatrix(){   return this.coordinateMatrix;   }
    public int[][] getEdgeMatrix()     {   return this.edgeMatrix;    }
    public int getVerticesNumber()     {   return this.verticesNumber;     }
    public int[] getShortestPath()     {   return this.shortestPath;       }
    public int getShortestDistance()   {   return this.shortestDistance;       }

    /**
     * method to remove an edge from edgeMatrix
     * will remove specified edge as well as reflective edge
     * it returns the updated matrix
     */
    public int[][] removeEdge(int i, int j){
        edgeMatrix[i][j] = 0;
        edgeMatrix[j][i] = 0;
        return edgeMatrix; //return updated matrix
    }
    public void addEdge(int i, int j, int d){
        edgeMatrix[i][j] = d;
        edgeMatrix[j][i] = d;
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
    private static int calculatePointDistance(int x1, int y1, int x2, int y2) {
        return (int)Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
    }
    /**
     * method to create an adjacency matrix
     */
    public int[][] setEdgeMatrix() {
        int d;
        int[][] m = new int[verticesNumber][verticesNumber];
        for (int i = 0; i < verticesNumber; i++) {
            for (int j = i+1; j < verticesNumber ; j++) { //only traversing half of symmetric matrix
                if (i == j){
                    m[i][j] = 0;
                    m[j][i] = 0;
                } // distance from a point to itself is always 0
                else { // the points are not equal to themselves and have not been calculated
                    d = calculatePointDistance(coordinateMatrix[i][0], coordinateMatrix[i][1], coordinateMatrix[j][0], coordinateMatrix[j][1]);
                    m[i][j] = d;
                    m[j][i] = d;
                }
            }
        }
        System.out.println("Edge matrix created");
        return m;
    }
    public void printMatrix(int[][] m){ //print a matrix
        for (int[] x : m) {
            for (int y : x)
                System.out.printf("%4d |",(y));
            System.out.println();
        }
    }
    public void printArr(int[] m){ //print array
        for (int i = 0; i < m.length ; i++)
            System.out.print(m[i] + "-");
    }
    /**
     * Given an array, generates random permutation of values in [0, n-1], where n
     * is size of given array; random permutation will be stored in the array. Uses
     * Fisher-Yates shuffle algorithm.
     *
     * @param a output array
     */
    public void randomPermutation(int[] a) {
//        System.out.print("\n'a' at beginning' " ); printArr(a);
        Random rnd = new Random();
        for (int i = verticesNumber - 1; i >= 0; i--) {
            // generates a random index in [0,i]
            int randomLocation = rnd.nextInt(i + 1);

            if (randomLocation != i) {
                // swap a[i] and a[randomLocation]
                int temp = a[i];
                a[i] = a[randomLocation];
                a[randomLocation] = temp;
            }
        }
        a[a.length-1] = a[0]; //start and end in same point
//        System.out.print("\n'a' at end' " ); printArr(a);


    }
    
    /**
     * Calculates distance of given route.
     *
     * @param a route
     *
     * @return distance
     *
     */
    public int totalDistance(int[] a) {

        int n = a.length;
        int x1,y1,x2,y2;
        // add weights of all edges in 'a'
        int totalWeight = 0;

        for (int i = 0; i < n-1; i++) {
//            int weight = edgeMatrix[a[i]][a[(i + 1)]];
//            x1 = coordinateMatrix[a[i]][0];
//            y1 = coordinateMatrix[a[i]][1];
//            x2 = coordinateMatrix[a[i+1]][0];
//            y2 = coordinateMatrix[a[i+1]][1];
            totalWeight += edgeMatrix[a[i]][a[(i + 1)]];
//            totalWeight += calculatePointDistance(x1,y1,x2,y2);
        }
//        System.out.println("total weight inside totalDistance()->\n" + totalWeight);
        return totalWeight;
    }
    public int[] fillArr(int len){
        int[] initialPath = new int[len];
        for (int i = 0; i < len; i++)
            initialPath[i] = i;
        return initialPath;
    }
    /**
     * Finds a shortest route that visits every vertex exactly once and returns to
     * the starting point. Uses local search, so optimal solution is not obtained,
     * in general.
     *
     * @return shortest distance
     */
    public int[] TSP_localSearch() {
        System.out.println("TSP_localSearch begin");
        System.out.println("vertices: "+ verticesNumber);
        System.out.print("\nedge matrix ->>> \n" ); printMatrix(edgeMatrix);
        int d; //hold distance weight
        boolean betterSolutionFound;
        int[] shortestRoute = fillArr(verticesNumber+1);
        int[] a = fillArr(verticesNumber+1);

        randomPermutation(a); // generate initial solution as a random permutation
        System.arraycopy(a, 0, shortestRoute, 0, verticesNumber+1);
        d = totalDistance(shortestRoute);
        System.out.print("\nrandom route: " ); printArr(shortestRoute);
        System.out.println("\nrandom route distance: " + d );

        /*
         * Loop will continue as long as there is a neighbor that improves best distance
         * obtained so far
         */
        do {
            betterSolutionFound = false;
            PermutationNeighborhood pn = new PermutationNeighborhood(shortestRoute);

            while (pn.hasNext()) {
                a = pn.next();

                int currentDistance = totalDistance(a);
                System.out.print("testing route -> "); printArr(a);
                System.out.println("\ndistance -> " + currentDistance);

                if (currentDistance < d) {
                    // shortestRoute = current Solution
                    System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
                    d = currentDistance;
                    betterSolutionFound = true;
                    System.out.println("better solution found");
                    System.out.print("new shortest route -> "); printArr(shortestRoute);
                    System.out.println();
                }
            }
        } while (betterSolutionFound);

        System.out.print("\n** shortest route end: " ); printArr(shortestRoute);
        System.out.println("\n** route distance end: " + d );

        return shortestRoute;
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
