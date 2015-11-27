/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import org.troy.markup.beans.AnnotationCircleBean;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
        Iterator<Annotation> annotationList = model.getAnnotationList().iterator();
        Rectangle mouseBox = Utilities.getMouseBox(e);
        
        while (annotationList.hasNext()) {
            Annotation annotation = annotationList.next();
            AnnotationCircleBean circle = annotation.getAnnotationCircle();
            if (g2d.hit(mouseBox, Utilities.createCircle(circle), false)) {
                
                if (e.getClickCount() == 1) {
                    model.setCurrentFocusedCircle(circle);
                    System.out.println("Mouse Clicked on circle");
                    model.getPanel().repaint();
                    
                }
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    JDialog d = new JDialog((JFrame) SwingUtilities.getWindowAncestor(e.getComponent()), true);
                    d.add(new AnnotationEditingPanel(annotation));
                    d.setLocation(e.getX() + 20, e.getY() + 20);
                    d.pack();
                    d.setVisible(true);
                }
                
            } else {
                if (model.getCurrentFocusedCircle() != null) {
                    model.setCurrentFocusedCircle(null);
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        Iterator<Annotation> annotationList = model.getAnnotationList().iterator();
        Graphics2D g2d = (Graphics2D) e.getComponent().getGraphics();
        Rectangle mousePoint = model.getMouseBox(e.getX(), e.getY());
        
        while (annotationList.hasNext()) {
            Annotation annotation = annotationList.next();
            AnnotationCircleBean circle = annotation.getAnnotationCircle();
            if (g2d.hit(mousePoint, Utilities.createCircle(circle), false)) {
                //System.out.println("Annotation Symbol clicked");
                model.setCurrentPressedCircle(circle);
                xDistanceBtwMouseAndCircle = circle.getX() - e.getX();
                yDistanceBtwMouseAndCircle = circle.getY() - e.getY();
                model.highlightAnnotation(annotation);
                e.getComponent().repaint();
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
        AnnotationCircleBean circle = model.getCurrentPressedCircle();
        if (circle != null) {
            model.setDraggingCircle(true);
            //System.out.println("Before dragg x=" + circle.x);
            circle.setX(e.getX() + xDistanceBtwMouseAndCircle);
            circle.setY(e.getY() + yDistanceBtwMouseAndCircle);
            model.getPanel().repaint();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
