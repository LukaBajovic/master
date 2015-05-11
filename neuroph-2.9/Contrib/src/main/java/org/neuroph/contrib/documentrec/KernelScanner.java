/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Luka
 */
public class KernelScanner {
    
    
    private int[][] kernel;
    private int kernelRows;
    private int kernelColumns;

    
    
    
    public KernelScanner(BufferedImage pattern) {
        this.kernelRows = pattern.getHeight();
        this.kernelColumns = pattern.getWidth();
        this.kernel = generateKernel(pattern);
    }
    
    public int[][] generateKernel(BufferedImage image) {
        int[][] kernel = new int[image.getHeight()][image.getWidth()];
        
        for (int y = 0; y < image.getHeight(); y++) {
            
            for (int x = 0; x < image.getWidth(); x++) {
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
    
    public BufferedImage invertColors(BufferedImage image) {
        
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color originalRGB = new Color(image.getRGB(x, y));
                Color invertedRGB = new Color(
                        Math.abs(originalRGB.getRed() - 255),
                        Math.abs(originalRGB.getGreen() - 255), 
                        Math.abs(originalRGB.getBlue() - 255));
                image.setRGB(x, y, invertedRGB.getRGB());
            }
        }
        return image;
    }
    
    public Point scan(BufferedImage image) {
        BufferedImage invertedImage = invertColors(image);
        Point position = new Point();
        int maxSum = Integer.MIN_VALUE;
        
        for (int y = 0; y < invertedImage.getHeight() - kernelRows; y++) {
            for (int x = 0; x < invertedImage.getWidth() - kernelColumns; x++) {
                
                int sum = 0;
                
                for (int i = 0; i < kernelRows; i++) {
                    for (int j = 0; j < kernelColumns; j++) {
                       Color pixel = new Color(invertedImage.getRGB(x + j, y + i));
                       sum += (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) * kernel[i][j];
                    }
                }
                if (maxSum < sum) {
                    maxSum = sum;
                    position.setLocation(x, y);
                }
            }
        }
        
        return position;
    }
       
    
}
