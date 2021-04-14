package com.company;

public interface GraphInterface{
    public void addEdge(int i, int j, int d);
    public void removeEdge(int i, int j);
    public int[] findAdjacencyVertices(int v);
    public String toString();
}