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
    
    public AnnotationCircle(double x, double y, String letter){
        super(x, y, 20, 20);
        this.letter = letter;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }
    
    
}
