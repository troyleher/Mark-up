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
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;
import org.troy.utilities.OCR;

/**
 *
 * @author Troy
 */
public class AnnotationRectangleMouseEventHandler implements MouseListener, MouseMotionListener {

    private MainPanelModel model;

    public AnnotationRectangleMouseEventHandler(MainPanelModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Iterator<AnnotationRectangle> sList
                = model.getAnnotationRectangleList().iterator();
        Graphics2D g2d = (Graphics2D) model.getPanel().getGraphics();
        Rectangle mouseBox = model.getMouseBox(e.getX(), e.getY());
        while (sList.hasNext()) {
            AnnotationRectangle rect = sList.next();
            if (g2d.hit(mouseBox, rect, true)) {
                //System.out.println("Rectangle clicked");
                setSelected(rect);
            }

        }

        //System.out.println("Mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        model.setxPos(e.getX());
        model.setyPos(e.getY());
        model.setDraggingRectangle(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        model.setDraggingRectangle(false);

        //System.out.println("Mouse released");
        //System.out.println("x=" + xTop + " y=" + yTop + " width=" + width + " height=" + height);
        saveAnnotationRectangle();
        if (model.isOCREnabled()) {
            performOCR();
        }
        model.getPanel().repaint();
        model.setDragHeight(0);
        model.setDragWidth(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!model.isDraggingCircle()) {
            model.setDragWidth(e.getX() - model.getxPos());
            model.setDragHeight(e.getY() - model.getyPos());
            model.getPanel().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //done
    private void saveAnnotationRectangle() {
        if (model.getDragWidth() > MainPanelModel.MIN_RECTANGLE_WIDTH
                && model.getDragHeight() > MainPanelModel.MIN_RECTANGLE_HEIGHT
                && model.getCurrentPressedCircle() == null) {
            AnnotationRectangle rect = new AnnotationRectangle(model.getxPos(),
                    model.getyPos(),
                    model.getDragWidth(),
                    model.getDragHeight());
            model.addAnnotationRectangleToList(rect);
        }
    }

    private void performOCR() {
        BufferedImage imageToOCR = ((BufferedImage) model.getImage()).getSubimage(model.getxPos(),
                model.getyPos(),
                model.getDragWidth(),
                model.getDragHeight());
        try {
            String result = OCR.parseImage(imageToOCR, "eng");
            //System.out.println(result);
        } catch (TesseractException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSelected(AnnotationRectangle rect) {
        if (model.getCurrentSelectedAnnotationRectangle() != null) {
            model.getCurrentSelectedAnnotationRectangle().setIsFocus(false);
        }
        rect.setIsFocus(true);
        model.setCurrentSelectedAnnotationRectangle(rect);
    }

}
