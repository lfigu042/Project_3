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

public class FrameDisplay extends JFrame {
    int WIDTH = 1200;
    int HEIGHT = 600;

    public FrameDisplay() {
        System.out.println("FrameDisplay() ran");
        setTitle("Local and Exhaustive Search Visualizer");
        setSize(WIDTH, HEIGHT);
        GraphDisplay panel = new GraphDisplay(WIDTH , HEIGHT);
        add(panel);
    }
}