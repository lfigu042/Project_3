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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readInput {
    private int[][] matrix;
    private int v;

    public void setMatrix(int[][] m) {
        this.matrix = m;
    }
    public void setV(int v){
        this.v = v;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }
    public int getVertices() {
        return this.v;
    }
    /**
     * Reads graph from text file (entries as given in COP4534 3rd assignment)
     * * and prints it
     **/

    public readInput() {
        File input = new File("src/com/company/input.txt");
        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        int vertices = 0;
        int mat_i_j = 0;
        int[][] coordinate_matrix;

        while (in.hasNextLine()) {
            vertices = in.nextInt();
            coordinate_matrix = new int[vertices][2]; // creating 2d array for file matrix

            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < 2; j++) {
                    mat_i_j = in.nextInt();
                    coordinate_matrix[i][j] = mat_i_j;
                }
                System.out.println();
            }
            setMatrix(coordinate_matrix);
            setV(vertices);
        }
        in.close();
    }
}