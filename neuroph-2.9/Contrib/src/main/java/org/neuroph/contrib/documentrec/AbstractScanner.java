/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Luka
 */
public abstract class AbstractScanner {
    
    /**
     * Represents an image of a pattern to search for
     */
    protected BufferedImage pattern;
    
    /**
     * Represents the default threshold
     */
    protected double threshold;
    

    public AbstractScanner(BufferedImage pattern) {
        this.pattern = pattern;
    }
 
    public BufferedImage getPattern() {
        return pattern;
    }

    public void setPattern(BufferedImage pattern) {
        this.pattern = pattern;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    public abstract List<Point> scan(BufferedImage image, double threshold);
    
}
