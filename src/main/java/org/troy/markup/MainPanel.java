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
import java.awt.Stroke;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.Iterator;
import javax.swing.JPanel;

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
        if (model.isDraggingRectangle() && !model.isDraggingCircle()) {
            g2d.drawRect(model.getxPos(),
                    model.getyPos(),
                    model.getDragWidth(),
                    model.getDragHeight());
        }
        Iterator<Annotation> annotationList = 
                model.getAnnotationList().iterator();
        while (annotationList.hasNext()) {
            Annotation annotation = annotationList.next();
            if (!annotation.getSelectionBox().isFocus()) {
                paintAnnotation(g2d,
                        annotation,
                        model.getBlurredAnnotationColor(),
                        model.getBlurredAnnotationStroke());

            } else {
                paintAnnotation(g2d,
                        annotation,
                        model.getFocusedAnnotationColor(),
                        model.getFocusedAnnotationStroke());
            }
        }
    }

    private void paintAnnotation(Graphics2D g2d, Annotation annotation, Color c, Stroke stroke) {
        g2d.setStroke(stroke);
        g2d.setColor(c);
        g2d.draw(annotation.getSelectionBox());
        g2d.draw(annotation.getSelectionLabel());
        paintAnnotationLetter(g2d, annotation.getSelectionLabel());
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
        String letter = annotation.getLetter();
        AttributedString as = new AttributedString(letter);
        if(letter.length() > 1){
            as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, letter.length());
        }
        as.addAttribute(TextAttribute.FONT, model.getAnnotationFont());
        as.addAttribute(TextAttribute.FOREGROUND, model.getAnnotationFontColor());
        g2d.drawString(as.getIterator(), (int) annotation.x + 6, (int) annotation.y + 15);
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
