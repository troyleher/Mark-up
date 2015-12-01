/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.beans;

/**
 *
 * @author Troy
 */
public class AnnotationRectangleBean {
    private double x;
    private double y;
    private double width;
    private double height;
    
    
    private AnnotationRectangleBean(){}
    public AnnotationRectangleBean(AnnotationRectangleBean rectBean){
        this.x = rectBean.x;
        this.y = rectBean.y;
        this.width = rectBean.width;
        this.height = rectBean.height;
    }
    public AnnotationRectangleBean(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = true;
        if(obj instanceof AnnotationRectangleBean){
            AnnotationRectangleBean arb = (AnnotationRectangleBean)obj;
            if( !(this.x == arb.x)){
                isEquals = false;
            }
            if( !(this.y == arb.y) ){
                isEquals = false;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }

    @Override
    public String toString() {
        return "Rectangle x=" + x + " y=" + y + " width=" + width + " height=" + height;
    }
    
    

   
    
}
