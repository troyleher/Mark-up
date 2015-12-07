/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import org.troy.markup.beans.AnnotationCircleBean;
import org.troy.markup.beans.AnnotationRectangleBean;
import org.troy.markup.memento.AnnotationMementoMediator;
import org.troy.markup.view.DrawDraggingRectangle;

/**
 *
 * @author Troy
 */
public class AnnotationMouseAdapter extends MouseAdapter {

    private MainPanelModel model;
    private int pressedPosX;
    private int pressedPosY;
    private boolean isMouseBeenDragged;
    private double xDistanceBtwMouseAndCircle;
    private double yDistanceBtwMouseAndCircle;

    public AnnotationMouseAdapter(MainPanelModel model) {
        this.model = model;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Graphics2D g2d = (Graphics2D) e.getComponent().getGraphics();
        SelectedAnnotation sa = model.getSelectedAnnotation();
        pressedPosX = e.getX();
        pressedPosY = e.getY();

        //Is pressed area within area of circle
        boolean match = false;
        for (Annotation a : model.getAnnotationList()) {
            AnnotationCircleBean circle = a.getAnnotationCircle();
            AnnotationRectangleBean rect = a.getAnnotationRectangle();

            if (g2d.hit(Utilities.getMouseBox(e),
                    Utilities.createCircle(circle),
                    false)) {
                sa.setAnnotation(a);
                sa.setMousePressedOnCircle(true);
                xDistanceBtwMouseAndCircle = a.getAnnotationCircle().getX() - e.getX();
                yDistanceBtwMouseAndCircle = a.getAnnotationCircle().getY() - e.getY();
                match = true;

                //is pressed area within area of rectangle
            } else if (g2d.hit(Utilities.getMouseBox(e),
                    Utilities.createRectangle(rect),
                    true)) {
                sa.setAnnotation(a);
                sa.setMousePressedOnCircle(false);
                match = true;

                //Else no element of annotation selected
            } else if (!match) {
                sa.setAnnotation(null);
                sa.setMousePressedOnCircle(false);

            }
            e.getComponent().repaint();

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        SelectedAnnotation sa = model.getSelectedAnnotation();
        if (isMouseBeenDragged) {
            //If Circle was pressed
            if (sa.isMousePressedOnCircle()) {

                //If empty area was pressed
            } else {
                double width = e.getX() - pressedPosX;
                double height = e.getY() - pressedPosY;
                if (width > 10 && height > 10) {
                    
                    Annotation newAnnotation = new Annotation(pressedPosX,
                            pressedPosY,
                            width,
                            height);
                    model.addAnnotationToList(newAnnotation);
                    sa.setAnnotation(newAnnotation);
                    AnnotationMementoMediator.getInstance().save(model.getAnnotationList());
                }

            }
        }
        resetVariables();
        e.getComponent().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        isMouseBeenDragged = true;
        SelectedAnnotation sa = model.getSelectedAnnotation();
        if (sa.isMousePressedOnCircle()) {
            AnnotationCircleBean circle = sa.getAnnotation().getAnnotationCircle();
            circle.setX(e.getX() + xDistanceBtwMouseAndCircle);
            circle.setY(e.getY() + yDistanceBtwMouseAndCircle);

        } else {
            model.setDmb(new DrawDraggingRectangle(pressedPosX,
                    pressedPosY,
                    e.getX() - pressedPosX,
                    e.getY() - pressedPosY));
        }
        e.getComponent().repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            Graphics2D g2d = (Graphics2D) e.getComponent().getGraphics();
            e.consume();
            //If annotation circle is double clicked show editing dialog
            List<Annotation> aList = model.getAnnotationList();
            for (final Annotation a : aList) {
                if (g2d.hit(Utilities.getMouseBox(e),
                        Utilities.createCircle(a.getAnnotationCircle()),
                        false)) {
                    JDialog d = Utilities.createEditingDialog(g2d, a, e.getComponent());
                    d.setVisible(true);
                }
            }

        }
    }

    private void resetVariables() {
        SelectedAnnotation sa = model.getSelectedAnnotation();
        sa.setMousePressedOnCircle(false);
        model.setDmb(null);
        xDistanceBtwMouseAndCircle = 0;
        yDistanceBtwMouseAndCircle = 0;

    }

}
