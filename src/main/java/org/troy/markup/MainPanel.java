/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
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
public class MainPanel extends JPanel  {

    private MainPanelModel model;

    public MainPanel() {
        this.model = new MainPanelModel(this);
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
    public Dimension getPreferredSize() {
        if(model.getImage() != null){
            return new Dimension(model.getImage().getWidth(null),
                   model.getImage().getHeight(null));
        }
        return null;
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

    public MainPanelModel getModel() {
        return model;
    }

    public void setModel(MainPanelModel model) {
        this.model = model;
    }
    

}
