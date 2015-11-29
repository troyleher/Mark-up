/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.beans;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

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
    private int mouseBoxWidth = 10;
    private int mouseBoxHeight = 10;
    private Stroke focusedAnnotationStroke = new BasicStroke(2);
    private Stroke blurredAnnotationStroke = new BasicStroke(1);
    private Color focusedAnnotationColor = Color.RED;
    private Color blurredAnnotationColor = Color.LIGHT_GRAY;

    private static SystemConfigBean configBean;

    private SystemConfigBean() {
    }

    public static SystemConfigBean createSystemConfigBean() {
        if (configBean == null) {
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

    public int getMouseBoxWidth() {
        return mouseBoxWidth;
    }

    public void setMouseBoxWidth(int mouseBoxWidth) {
        this.mouseBoxWidth = mouseBoxWidth;
    }

    public int getMouseBoxHeight() {
        return mouseBoxHeight;
    }

    public void setMouseBoxHeight(int mouseBoxHeight) {
        this.mouseBoxHeight = mouseBoxHeight;
    }
        public Stroke getFocusedAnnotationStroke() {
        return focusedAnnotationStroke;
    }

    public void setFocusedAnnotationStroke(Stroke focusedAnnotationStroke) {
        this.focusedAnnotationStroke = focusedAnnotationStroke;
    }

    public Stroke getBlurredAnnotationStroke() {
        return blurredAnnotationStroke;
    }

    public void setBlurredAnnotationStroke(Stroke unFocusedAnnotationStroke) {
        this.blurredAnnotationStroke = unFocusedAnnotationStroke;
    }

    public Color getFocusedAnnotationColor() {
        return focusedAnnotationColor;
    }

    public void setFocusedAnnotationColor(Color focusedAnnotationColor) {
        this.focusedAnnotationColor = focusedAnnotationColor;
    }

    public Color getBlurredAnnotationColor() {
        return blurredAnnotationColor;
    }

    public void setBlurredAnnotationColor(Color blurredAnnotationColor) {
        this.blurredAnnotationColor = blurredAnnotationColor;
    }

}
