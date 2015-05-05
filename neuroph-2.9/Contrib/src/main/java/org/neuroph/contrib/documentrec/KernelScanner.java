/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Luka
 */
public class KernelScanner {
    
    public KernelScanner() {
        setKernel();
    }
    
    int[][] kernel;

    public void setKernel() {
        kernel = new int[31][14];
        for (int i = 0; i < 31; i++) {
            kernel[i][0] = 1;
        }
        for (int i = 0; i < 14; i++) {
            kernel[0][i] = 1;
            kernel[30][i] = 1;
        }
        
    }
    
    
    
    public Position scanImage(BufferedImage imageToScan) {
        Position position = new Position();
        int maxSum = Integer.MIN_VALUE;
        
        for (int x = 0; x < imageToScan.getWidth() - 14; x++) {
            for (int y = 0; y < imageToScan.getHeight() - 31; y++) {
                
                int sum = 0;
                
                for (int j = 0; j < 14; j++) {
                    for (int i = 0; i < 31; i++) {
                        Color pixel = new Color(imageToScan.getRGB(x + j, y + i));
                        if (pixel.getRed() < 100 && pixel.getGreen() < 100 && pixel.getBlue() < 100) {
                            sum += (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) * kernel[i][j];
                        }
                    }

                }
                if (maxSum < sum) {
                    maxSum = sum;
                    position.setCoordinateX(x);
                    position.setCoordinateY(y);
                    
                }
                
            }
        }

        
        return position;
    }
    
}
