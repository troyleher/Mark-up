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
public class AnnotationCircleBean {
    private double x;
    private double y;
    private double width;
    private double height;
    private String symbol;
    
    
    public AnnotationCircleBean(){}
    public AnnotationCircleBean(double x,
            double y, double width, double height, String symbol){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.symbol = symbol;
    
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String labelID) {
        this.symbol = labelID;
    }
    
    
    
    
}
