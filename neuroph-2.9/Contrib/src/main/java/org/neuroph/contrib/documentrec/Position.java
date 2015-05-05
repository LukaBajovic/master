/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

/**
 *
 * @author Luka
 */
public class Position {
    
    private int coordinateX;
    private int coordinateY;
    private double matchScore;
    
    public Position() {
        
    }

    public Position(int coordinateX, int coordinateY, double matchScore) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.matchScore = matchScore;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "Match at position X: "+coordinateX+", Y: "+coordinateY;
    }
    
    
    
}
