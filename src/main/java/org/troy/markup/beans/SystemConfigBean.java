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
public class SystemConfigBean {
    
    
    //Annotation Circle defaults
    private double circleXDistanceFromRectangle = 25;
    private double circleWidth = 20;
    private double circleHeight = 20;
    private String details = "TODO";
    private static SystemConfigBean configBean;
    
    private SystemConfigBean(){}
    
    public static SystemConfigBean createSystemConfigBean(){
        if(configBean == null){
            configBean = new SystemConfigBean();
        }
        return configBean;
    }

    public double getCircleXDistanceFromRectangle() {
        return circleXDistanceFromRectangle;
    }

    public void setCircleXDistanceFromRectangle(double circleXDistanceFromRectangle) {
        this.circleXDistanceFromRectangle = circleXDistanceFromRectangle;
    }

    public double getCircleWidth() {
        return circleWidth;
    }

    public void setCircleWidth(double circleWidth) {
        this.circleWidth = circleWidth;
    }

    public double getCircleHeight() {
        return circleHeight;
    }

    public void setCircleHeight(double circleHeight) {
        this.circleHeight = circleHeight;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
    
    
    
}
