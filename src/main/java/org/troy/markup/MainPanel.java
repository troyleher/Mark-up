/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.sourceforge.tess4j.TesseractException;
import org.troy.utilities.OCR;

/**
 *
 * @author Troy
 */
public class MainPanel extends JPanel implements MouseListener, MouseMotionListener {

   
    private boolean isOCREnabled = false;
    private ArrayList<AnnotationRectangle> selectionList = new ArrayList<>();
    private AnnotationCircle currentAnnotationSymbol;
    private MainPanelModel model;

    public MainPanel() {
        this.model = new MainPanelModel(this);

        addMouseListener(this);
        addMouseMotionListener(this);

        AnnotationCircleMouseListener circleListener
                = new AnnotationCircleMouseListener(model);
        addMouseListener(circleListener);
        addMouseMotionListener(circleListener);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (model.getImage() != null) {
            g.drawImage(model.getImage(), 0, 0, null);
        }
        if (model.isDraggingRectangle() && model.getCurrentFocusedCircle() == null) {
            g2d.drawRect(model.getxPos(),
                    model.getyPos(),
                    model.getDragWidth(),
                    model.getDragHeight());
        }
        Iterator<AnnotationRectangle> sList = 
                model.getAnnotationRectangleList().iterator();
        while (sList.hasNext()) {
            AnnotationRectangle rect = sList.next();
            if (!rect.isFocus()) {
                paintAnnotation(g2d,
                        rect,
                        model.getBlurredAnnotationColor(),
                        model.getBlurredAnnotationStroke());

            } else {
                paintAnnotation(g2d,
                        rect,
                        model.getFocusedAnnotationColor(),
                        model.getFocusedAnnotationStroke());
            }
        }
    }

    private void paintAnnotation(Graphics2D g2d, AnnotationRectangle rect, Color c, Stroke stroke) {
        g2d.setStroke(stroke);
        g2d.setColor(c);
        g2d.draw(rect);
        g2d.draw(rect.getAnnotation());

        paintAnnotationLetter(g2d, rect.getAnnotation());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Iterator<AnnotationRectangle> sList = 
                model.getAnnotationRectangleList().iterator();
        Graphics2D g2d = (Graphics2D) this.getGraphics();
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

    private void setSelected(AnnotationRectangle rect) {
        if (model.getCurrentSelectedAnnotationRectangle() != null) {
            model.getCurrentSelectedAnnotationRectangle().setIsFocus(false);
        }
        rect.setIsFocus(true);
        model.setCurrentSelectedAnnotationRectangle(rect);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        model.setxPos(e.getX());
        model.setyPos(e.getY());
        model.setDraggingRectangle(true);

    }

    @Override
    public Dimension getPreferredSize() {
        if(model.getImage() != null){
            return new Dimension(model.getImage().getWidth(null),
                   model.getImage().getHeight(null));
        }
        return null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       model.setDraggingRectangle(false);

        //System.out.println("Mouse released");
        //System.out.println("x=" + xTop + " y=" + yTop + " width=" + width + " height=" + height);
        saveAnnotationRectangle();
        if (isOCREnabled) {
            performOCR();
        }
        repaint();
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
        model.setDragWidth(e.getX() - model.getxPos());
        model.setDragHeight(e.getY() - model.getyPos());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void performOCR() {
        BufferedImage imageToOCR = ((BufferedImage)model.getImage()).getSubimage(model.getxPos(),
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

    //done
    private void paintAnnotationLetter(Graphics2D g2d, AnnotationCircle annotation) {
        g2d.setFont(model.getAnnotationFont());
        g2d.setColor(model.getAnnotationFontColor());
        g2d.drawString(annotation.getLetter(), (int) annotation.x + 6, (int) annotation.y + 15);
    }

    public Image getImage() {
        return model.getImage();
    }

    public void setImage(BufferedImage image) {
        model.setImage(image);
    }

}
