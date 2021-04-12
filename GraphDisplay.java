package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphDisplay extends JPanel {
    readInput myText = new readInput();
    private int[][] coordinates;
    int vertices;
    int printonceonlyomg =0;
    public void paint(Graphics g) {
        coordinates = myText.getMatrix(); //coordinates
        vertices = myText.getVertices(); //vertices

        while(printonceonlyomg == 0){
            printMatrix(coordinates);
            printonceonlyomg++;
        }

       drawVertices(coordinates, vertices, g);

//      draw edge (1,2)
//        g.setColor(Color.BLACK);
//        g.drawLine(x1, y1+height/2, x2, y2+height/2);
    }
    public void drawVertices(int[][] m, int v, Graphics g){
        int x,y;
        int width = 50;
        int height = 50;
        int labelX = 15;
        int labelY = 30;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

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
    public void printMatrix(int[][] m){ //print a matrix
        for (int[] x : m) {
            for (int y : x)
                System.out.print(y + " ");
            System.out.println();
        }
    }
}