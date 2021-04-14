package com.company;

public interface GraphInterface{
    public void addEdge(int i, int j, int d);
    public int[][] removeEdge(int i, int j);
    public int[] findAdjacencyVertices(int v);
    public String toString();
    public void randomPermutation(int[] a);
    public int totalDistance(int[] a);
    public int TSP_localSearch(int[] shortestRoute);
    public int TSP_localSearch();
    public int[] getInitialPath();

}