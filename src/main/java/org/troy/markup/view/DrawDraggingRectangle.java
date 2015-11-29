/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Troy
 */
public class DrawDraggingRectangle implements DrawMouseBehaviour{
    
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    
    
    public DrawDraggingRectangle(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
         this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.draw( new Rectangle2D.Double(x, y, width, height));
    }
    
}
