package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {

        FrameDisplay frame = new FrameDisplay();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // generate 10 random permutations of values in [0,5]
        // for (int i = 0; i < 10; i++) {
        // int[] a = new int[6];
        // randomPermutation(a);
        // System.out.println(Arrays.toString(a));
        // }
    }

    /**
     * Given an array, generates random permutation of values in [0, n-1], where n
     * is size of given array; random permutation will be stored in the array. Uses
     * Fisher-Yates shuffle algorithm.
     *
     * @param a output array
     */
    // public void randomPermutation(int[] a) {
    // for (int i = 0; i < a.length; i++) {
    // a[i] = i;
    // }
    // Random rnd = new Random();
    // for (int i = a.length - 1; i > 0; i--) {
    // // generates a random index in [0,i]
    // int randomLocation = rnd.nextInt(i + 1);
    //
    // if (randomLocation != i) {
    // // swap a[i] and a[randomLocation]
    // int temp = a[i];
    // a[i] = a[randomLocation];
    // a[randomLocation] = temp;
    // }
    // }
    // }

    /**
     * Finds a shortest route that visits every vertex exactly once and returns to
     * the starting point. Uses random sampling, so optimal solution is not
     * obtained, in general.
     *
     * @param shortestRoute array with the shortest path (return value)
     * @return shortest distance
     */
    // public int TSP_randomSampling(int[] shortestRoute) {
    // int numberOfSamples = 10;
    // int bestDistance = Integer.MAX_VALUE;
    // for (int i = 0; i < numberOfSamples; i++) {
    // int[] a = new int[verticesNumber];
    //
    // randomPermutation(a);
    // int currentDistance = totalDistance(a);
    // if (currentDistance < bestDistance) {
    // bestDistance = currentDistance;
    // System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
    // }
    // }
    // return bestDistance;
    // }

    /**
     * Finds a shortest route that visits every vertex exactly once and returns to
     * the starting point Uses exhaustive search.
     *
     * @param shortestRoute array with a shortest path (return value)
     *
     * @return shortest distance
     *
     */
    // public int TSP_exhaustiveSearch(int[] shortestRoute) {
    // // initialize shortestRoute
    // for (int i = 0; i < verticesNumber; i++) {
    // shortestRoute[i] = i;
    // }
    // int[] a = new int[verticesNumber];
    // TSP_exhaustiveSearch(shortestRoute, a, 0);
    // return totalDistance(shortestRoute);
    // }

    /**
     * Calculates distance of given route.
     *
     * @param a route
     *
     * @return distance
     *
     */
    // int totalDistance(int[] a) {
    // int n = verticesNumber;
    // // add weights of all edges in
    // int totalWeight = 0;
    // for (int i = 0; i < n; i++) {
    // int weight = matrix[a[i]][a[(i + 1) % n]];
    // totalWeight += weight;
    // }
    // return totalWeight;
    // }

    /**
     * Recursive algorithm.
     *
     * @param a array partially filled with permutation
     * @param k index of current element in permutation
     */
    // private void TSP_exhaustiveSearch(int[] shortestRoute, int[] a, int k) {
    // if (k == a.length) {
    // if (totalDistance(a) < totalDistance(shortestRoute)) {
    // System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
    // }
    // // System.out.print(totalDistance(a) +" ");
    // // printArray(a);
    // } else {
    // ArrayList<Integer> sk = constructCandidateSet(a, k);
    // for (int s : Sk) {
    // a[k] = s;
    // TSP_exhaustiveSearch(shortestRoute, a, k + 1);
    // }
    // }
    //
    // }

    /**
     * Construct candidate set (set will contain elements not used in locations
     * [0,k-1] of array a)
     */
    // private ArrayList<Integer> constructCandidateSet(int[] a, int k) {
    // ArrayList<Integer> candidates = new ArrayList<>();
    // boolean[] b = new boolean[a.length];
    // for (int i = 0; i < k; i++) {
    // b[a[i]] = true;
    // }
    // for (int i = 0; i < a.length; i++) {
    // if (!b[i])
    // candidates.add(i);
    // }
    // return candidates;
    // }

    /**
     * Prints array a.
     */
    // private void printArray(int[] a) {
    // for (int v : a) {
    // System.out.print(v + " ");
    // }
    // System.out.println();
    // }

    /**
     * Finds a shortest route that visits every vertex exactly once and returns to
     * the starting point. Uses local search, so optimal solution is not obtained,
     * in general.
     *
     * @param shortestRoute array with thea shortest path(return value)
     *
     * @return shortest distance
     */
    // public int TSP_localSearch(int[] shortestRoute) {
    // int bestDistance;
    // // generate initial solution as a random permutation
    // int[] a = new int[verticesNumber];
    // randomPermutation(a);
    //
    // // shortestRoute = initial solution
    // System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
    // bestDistance = totalDistance(shortestRoute);
    //
    // boolean betterSolutionFound;
    // /*
    // * Loop will continue as long as there is a neighbor that improves best
    // distance
    // * obtained so far
    // */
    // do {
    // betterSolutionFound = false;
    // PermutationNeighborhood pn = PermutationNeighborhood(shortestRoute);
    //
    // while (pn.hasNext()) {
    // a = pn.next();
    // int currentDistance = totalDistance(a);
    // if (currentDistance < bestDistance) {
    // // shortestRoute = current solution
    // System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
    // bestDistance = currentDistance;
    // betterSolutionFound = true;
    // }
    // }
    // } while (betterSolutionFound);
    // return bestDistance;
    // }

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
    public static void createAdjacencyMatrix() {

    }

}
