/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;


import java.awt.geom.Ellipse2D;

/**
 *
 * @author Troy
 */
public class AnnotationCircle extends Ellipse2D.Double {
    private String letter;
    private boolean isFocused;
    
    public AnnotationCircle(double x, double y, String letter){
        this(x, y, 20, 20, letter);
        
    }
    public AnnotationCircle(double x, double y, double width,
            double height, String letter){
        super(x, y, width, height);
        this.letter = letter;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * @return the isFocused
     */
    public boolean isFocused() {
        return isFocused;
    }

    /**
     * @param isFocused the isFocused to set
     */
    public void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public void setX(double x) {
        this.x = x;
    }
    
    
    
    
    
}
