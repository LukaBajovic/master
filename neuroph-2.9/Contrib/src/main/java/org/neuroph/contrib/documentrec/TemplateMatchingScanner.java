/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luka
 */
public class TemplateMatchingScanner {
    
    private BufferedImage pattern;
    private String patternName;
    private int patternSize;

    public TemplateMatchingScanner(BufferedImage pattern) {
        this.pattern = pattern;
        this.patternSize = pattern.getHeight()*pattern.getWidth();
    }

    public TemplateMatchingScanner(BufferedImage pattern, String patternName) {
        this.pattern = pattern;
        this.patternName = patternName;
        this.patternSize = pattern.getHeight()*pattern.getWidth();
    }
    
    
    
    public List<Point> scan(BufferedImage image) {
        List<Point> matchList = new ArrayList<>();
        double threshold = 20;
        
        int minDifference = Integer.MAX_VALUE;
        
        for (int x = 0; x < image.getWidth() - pattern.getWidth(); x++) {
            for (int y = 0; y < image.getHeight() - pattern.getHeight(); y++) {

               int pixelDifferenceSum = 0;
               
                for (int i = 0; i < pattern.getWidth(); i++) {
                    for (int j = 0; j < pattern.getHeight(); j++) {
                        
                        Color imagePixel = new Color(image.getRGB(x + i, y + j));
                        Color patternPixel = new Color(pattern.getRGB(i, j));
                        
                        pixelDifferenceSum += Math.abs(imagePixel.getRed() - patternPixel.getRed())
                                + Math.abs(imagePixel.getGreen() - patternPixel.getGreen())
                                + Math.abs(imagePixel.getBlue() - patternPixel.getBlue());

                    }
                }
                if (pixelDifferenceSum < threshold*patternSize) {
                    
                    Point position = new Point();
                    position.setLocation(x, y);
                    matchList.add(position);
                   
                }
            }
        }
        return matchList;
    }
    
    public List<Point> scan(BufferedImage image, double threshold) {
        List<Point> matchList = new ArrayList<>();
        
        int minDifference = Integer.MAX_VALUE;
        
        for (int x = 0; x < image.getWidth() - pattern.getWidth(); x++) {
            for (int y = 0; y < image.getHeight() - pattern.getHeight(); y++) {

               int pixelDifferenceSum = 0;
               
                for (int i = 0; i < pattern.getWidth(); i++) {
                    for (int j = 0; j < pattern.getHeight(); j++) {
                        
                        Color imagePixel = new Color(image.getRGB(x + i, y + j));
                        Color patternPixel = new Color(pattern.getRGB(i, j));
                        
                        pixelDifferenceSum += Math.abs(imagePixel.getRed() - patternPixel.getRed())
                                + Math.abs(imagePixel.getGreen() - patternPixel.getGreen())
                                + Math.abs(imagePixel.getBlue() - patternPixel.getBlue());

                    }
                }
                if (pixelDifferenceSum < threshold*patternSize) {
                    
                    Point position = new Point();
                    position.setLocation(x, y);
                    matchList.add(position);
                   
                }
            }
        }
        return matchList;
    }
    
}
