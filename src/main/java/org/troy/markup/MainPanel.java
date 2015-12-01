/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import org.troy.markup.beans.AnnotationCircleBean;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.AttributedString;
import javax.swing.JPanel;
import org.troy.markup.beans.SystemConfigBean;

/**
 *
 * @author Troy
 */
public class MainPanel extends JPanel implements PropertyChangeListener {

    private MainPanelModel model;
    private SystemConfigBean config;

    public MainPanel() {
        this.model = new MainPanelModel(this);
        this.config = SystemConfigBean.createSystemConfigBean();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (model.getImage() != null) {
            g.drawImage(model.getImage(), 0, 0, null);
        }

        for (Annotation a : model.getAnnotationList()) {
            
            if (a.equals(model.getSelectedAnnotation().getAnnotation())) {
                paintAnnotation(g2d,
                        a,
                        config.getFocusedAnnotationColor(),
                        config.getFocusedAnnotationStroke());
            } else {
                paintAnnotation(g2d,
                        a,
                        config.getBlurredAnnotationColor(),
                        config.getBlurredAnnotationStroke());
            }

        }
        if (model.getDmb() != null) {
            model.getDmb().draw(g);
        }
    }

    private void paintAnnotation(Graphics2D g2d, Annotation annotation, Color c, Stroke stroke) {
        g2d.setStroke(stroke);
        g2d.setColor(c);
        g2d.draw(Utilities.createRectangle(annotation.getAnnotationRectangle()));
        g2d.draw(Utilities.createCircle(annotation.getAnnotationCircle()));
        paintAnnotationLetter(g2d, annotation.getAnnotationCircle());
    }

    @Override
    public Dimension getPreferredSize() {
        if (model.getImage() != null) {
            return new Dimension(model.getImage().getWidth(null),
                    model.getImage().getHeight(null));
        }
        return null;
    }

    //done
    private void paintAnnotationLetter(Graphics2D g2d, AnnotationCircleBean annotationCircle) {
        String letter = annotationCircle.getSymbol();
        AttributedString as = new AttributedString(letter);
        if (letter.length() > 1) {
            as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, letter.length());
        }
        as.addAttribute(TextAttribute.FONT, model.getAnnotationFont());
        as.addAttribute(TextAttribute.FOREGROUND, model.getAnnotationFontColor());

        g2d.drawString(as.getIterator(), (int) annotationCircle.getX() + 6, (int) annotationCircle.getY() + 15);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (SelectedAnnotation.setAnnotation.equalsIgnoreCase(evt.getPropertyName())) {
            this.repaint();
        }
    }

   

}
