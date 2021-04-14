package com.company;

import javax.swing.*;

public class FrameDisplay extends JFrame {
    int WIDTH = 600;
    int HEIGHT = 600;

    public FrameDisplay() {
        setTitle("Local and Exhaustive Search Visualizer");
        setSize(WIDTH, HEIGHT);
        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}