/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Kernel scanner determines if an image contains a pattern
 * by using a matrix that represents a pattern. It loops through
 * the image and multiplies each matrix value with the corresponding
 * image pixel value.
 *
 * @author Luka
 */
public class KernelScanner {
    
    
    private int[][] kernel;
    private int kernelRows;
    private int kernelColumns;
    private int patternArea;
    
    
    
    public KernelScanner(BufferedImage pattern) {
        this.kernelRows = pattern.getHeight();
        this.kernelColumns = pattern.getWidth();
        this.kernel = generateKernel(pattern);
        this.patternArea = pattern.getHeight()*pattern.getWidth();
    }
    
    /**
     * Generates a two dimensional array from a pattern image.
     * The array contains values 0 and 1.
     * 
     * @param image image of a pattern
     * @return two dimensional integer array
     */
    public int[][] generateKernel(BufferedImage image) {
        int[][] kernel = new int[image.getHeight()][image.getWidth()];
        
        //loop through the pattern image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                
                //set the kernel value depending on the color component value 
                Color pixel = new Color(image.getRGB(x, y));
                if (pixel.getRed() > 150) {
                    kernel[y][x] = 0;
                    
                } else {
                    kernel[y][x] = 1;
                    
                }
            }
        }
        
        return kernel;
    }
    
    /**
     * Returns a BufferedImage with inverted input image colors.
     * 
     * @param image input image
     * @return BufferedImage with inverted colors
     */
    public BufferedImage invertColors(BufferedImage image) {
        
        //loop through the original image
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                
                Color originalRGB = new Color(image.getRGB(x, y));
                
                //invert color
                Color invertedRGB = new Color(
                        255 - originalRGB.getRed(),
                        255 - originalRGB.getGreen(), 
                        255 - originalRGB.getBlue());
                image.setRGB(x, y, invertedRGB.getRGB());
            }
        }
        return image;
    }
    
    /**
     * Scans an image for a pattern and returns a list of Point objects
     * that represent match positions. The match is determined by multiplying
     * kernel values with image pixel values. If the multiplication output
     * is greater than the threshold, the position is considered a match
     * and added to the list.
     * 
     * @param image image to scan
     * @param threshold threshold for multiplication output
     * @return list of Point objects
     */
    public List<Point> scan(BufferedImage image, int threshold) {
        //inverts image colors
        BufferedImage invertedImage = invertColors(image);
        List<Point> matchList = new ArrayList<>();
        
        //loops through the image
        for (int y = 0; y < invertedImage.getHeight() - kernelRows; y++) {
            for (int x = 0; x < invertedImage.getWidth() - kernelColumns; x++) {

                //resets the sum
                int sum = 0;
                
                //loops through the kernel
                for (int i = 0; i < kernelRows; i++) {
                    for (int j = 0; j < kernelColumns; j++) {
                        
                        //multiplies image pixel values with kernel values and adds them to the sum
                        Color pixel = new Color(invertedImage.getRGB(x + j, y + i));
                        sum += (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) * kernel[i][j];
                        
                    }
                }
                if (threshold < sum) {
                    //adds a match to the list
                    Point p = new Point(x, y);
                    matchList.add(p);
                }
            }
        }
        return matchList;
    }
    
    public List<Point> scan(BufferedImage image) {
        return scan(image, patternArea * 100);
    }
    
}
