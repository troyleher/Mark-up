/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

/**
 *
 * @author Troy
 */
public class AnnotationCircleMouseListener implements MouseListener, MouseMotionListener {

    private MainPanelModel model;
    private double xDistanceBtwMouseAndCircle;
    private double yDistanceBtwMouseAndCircle;

    public AnnotationCircleMouseListener(MainPanelModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Graphics2D g2d = (Graphics2D) e.getComponent().getGraphics();
        Iterator<AnnotationRectangle> rectList = model.getAnnotationRectangleList().iterator();
        Rectangle mouseBox = Utilities.getMouseBox(e);

        while (rectList.hasNext()) {
            AnnotationCircle circle = rectList.next().getAnnotation();
            if (g2d.hit(mouseBox, circle, true)) {
                if (model.getCurrentFocusedCircle() != null) {
                    model.getCurrentFocusedCircle().setIsFocused(false);
                }
                circle.setIsFocused(true);
                model.setCurrentFocusedCircle(circle);
                System.out.println("Mouse Clicked on circle");
                model.getPanel().repaint();
                
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Iterator<AnnotationRectangle> rectList = model.getAnnotationRectangleList().iterator();
        Graphics2D g2d = (Graphics2D) e.getComponent().getGraphics();
        Rectangle mousePoint = model.getMouseBox(e.getX(), e.getY());

        while (rectList.hasNext()) {
            AnnotationCircle circle = rectList.next().getAnnotation();
            if (g2d.hit(mousePoint, circle, true)) {
                //System.out.println("Annotation Symbol clicked");
                model.setCurrentPressedCircle(circle);
                xDistanceBtwMouseAndCircle = circle.x - e.getX();
                yDistanceBtwMouseAndCircle = circle.y - e.getY();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        model.setCurrentPressedCircle(null);
        model.setDraggingCircle(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        AnnotationCircle circle = model.getCurrentPressedCircle();
        if (circle != null) {
            model.setDraggingCircle(true);
            System.out.println("Before dragg x=" + circle.x);
            circle.x = e.getX() + xDistanceBtwMouseAndCircle;
            circle.y = e.getY() + yDistanceBtwMouseAndCircle;
            model.getPanel().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
