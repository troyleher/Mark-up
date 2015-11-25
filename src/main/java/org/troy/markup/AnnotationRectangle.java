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
   
    
    
    public AnnotationRectangle(double x, double y, double width, double height){
        super(x, y, width, height);
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

  
    
    
    
}
