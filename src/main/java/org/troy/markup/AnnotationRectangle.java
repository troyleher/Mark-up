/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Troy
 */
public class AnnotationRectangle extends Rectangle.Double{
    
    private boolean isFocus = false;
    private AnnotationCircle annotation ;
    
    
    public AnnotationRectangle(double x, double y, double width, double height){
        super(x, y, width, height);
        annotation = new AnnotationCircle(x - 25, y, AnnotationLetterFactory.createLetter());
    }

    /**
     * @return the isFocus
     */
    public boolean isFocus() {
        return isFocus;
    }

    /**
     * @param isFocus the isFocus to set
     */
    public void setIsFocus(boolean isFocus) {
        this.isFocus = isFocus;
    }

    /**
     * @return the annotation
     */
    public AnnotationCircle getAnnotation() {
        return annotation;
    }
    
    
    
    
}
