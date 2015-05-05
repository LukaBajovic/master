/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.image.BufferedImage;
import org.neuroph.contrib.documentrec.pattern.ImagePattern;

/**
 *
 * @author Luka
 */
public class TemplateMatchingScanner {
    
    public Position scanImage(BufferedImage imageToScan, ImagePattern pattern) {
        int minDifference = Integer.MAX_VALUE;
        Position position = new Position();
        
        for (int x = 0; x < imageToScan.getWidth() - pattern.getWidth(); x++) {
            for (int y = 0; y < imageToScan.getHeight() - pattern.getHeight(); y++) {

               int pixelDifferenceSum = 0;
               
                for (int i = 0; i < pattern.getWidth(); i++) {
                    for (int j = 0; j < pattern.getHeight(); j++) {
                        pixelDifferenceSum += Math.abs(pattern.getImage().getRGB(i, j) - imageToScan.getRGB(x + i, y + j));
                    
                    }
                }
                if (minDifference > pixelDifferenceSum) {
                    minDifference = pixelDifferenceSum;
                    position.setMatchScore(pixelDifferenceSum);
                    position.setCoordinateX(x);
                    position.setCoordinateY(y);
                   
                }
            }
        }
        return position;
    }
    
    
    
}
