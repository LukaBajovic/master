/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Luka
 */
public class MatchViewPanel extends JPanel {
    
    /**
     * Image to draw on
     */
    private Image imageToDraw;
    
    /**
     * Offscreen image used for double buffering
     */
    private Image drawingBuffer;
    
    /**
     * List of positions for drawing
     */
    private List<Point> matchList;

    public MatchViewPanel(BufferedImage image, List<Point> matchList) {
        this.imageToDraw = image;
        this.matchList = matchList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        //create an offscreen image
        drawingBuffer = this.createImage(getWidth(), getHeight());
        
        if (drawingBuffer != null) {
            
            //create a graphics context for drawing
            Graphics graphicsBuffer = drawingBuffer.getGraphics();
            
            //draw the image on the back buffer
            graphicsBuffer.drawImage(imageToDraw, 0, 0, null);
            
            //draw rectangles on the image
            graphicsBuffer.setColor(Color.RED);
            for (Point p : matchList) {
                int x = (int) p.getX();
                int y = (int) p.getY();
                graphicsBuffer.drawRect(x - 5, y - 5, 31, 31);
            }
            
        }
        //draw the image to the screen
        g.drawImage(drawingBuffer, 0, 0, null);
    }
    
    
     
    
}
