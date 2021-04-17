package com.company;

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