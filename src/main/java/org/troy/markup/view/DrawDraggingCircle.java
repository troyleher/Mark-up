/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import org.troy.markup.beans.AnnotationCircleBean;

/**
 *
 * @author Troy
 */
public class DrawDraggingCircle implements DrawMouseBehaviour{
    private AnnotationCircleBean circleBean;
    
    public DrawDraggingCircle(AnnotationCircleBean circle){
        this.circleBean = circle;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.draw(new  Ellipse2D.Double(circleBean.getX(),
                circleBean.getY(),
                circleBean.getWidth(),
                circleBean.getHeight()));
    }
    
}
