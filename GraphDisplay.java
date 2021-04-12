package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphDisplay extends JPanel {
    readInput myText = new readInput();
    private int[][] coordinates;
    int vertices;

    public void paint(Graphics g) {
        coordinates = myText.getMatrix(); //coordinates
        vertices = setV(coordinates); //vertices

//       int leftX = 100;
//       int topY = 100;
//       int width = 50;
//       int height = 50;
//       int labelX = 17;
//       int labelY = 31;
//       int gridWidth = 150;
       drawVertices(coordinates, vertices, g);
//       draw vertex 1
//       g.setColor(Color.ORANGE);
//       g.fillOval(leftX, topY, width, height);
//       g.setColor(Color.BLACK);
//       g.drawOval(leftX, topY, width, height);
//       g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
//       g.drawString("1", leftX+labelX, topY+labelY);

//      draw edge (1,2)
//        g.setColor(Color.BLACK);
//        g.drawLine(leftX+width, topY+height/2, leftX+gridWidth, topY+height/2);
    }
    public void drawVertices(int[][] m, int v, Graphics g){
        int x,y;
        int leftX = 100;
        int topY = 100;
        int width = 50;
        int height = 50;
        int labelX = 17;
        int labelY = 31;
        int gridWidth = 150;

        g.setColor(Color.ORANGE);
        g.fillOval(leftX+gridWidth, topY, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(leftX+gridWidth, topY, width, height);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        for (int i =0; i < v; i++){
            x = m[i][0];
            y = m[i][1];
            g.drawString(String.valueOf(i), x+labelX, y);
        }
    }
    public int setV(int[][] m){ //can also print matrix
        int v = 0;
        for (int[] x : m) {
            for (int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
            v++;
        }
        System.out.println("vertices: " + v);
        return v; //vertices
    }
}