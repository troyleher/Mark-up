/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Component;
import java.awt.Graphics2D;
import org.troy.markup.beans.AnnotationRectangleBean;
import org.troy.markup.beans.AnnotationCircleBean;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import org.troy.markup.beans.SystemConfigBean;

/**
 *
 * @author Troy
 */
public class Utilities {

    public static Rectangle getMouseBox(MouseEvent e) {
        SystemConfigBean config = SystemConfigBean.createSystemConfigBean();
        Rectangle box = new Rectangle(e.getX() - config.getMouseBoxWidth() / 2,
                e.getY() - config.getMouseBoxHeight() / 2,
                config.getMouseBoxWidth(),
                config.getMouseBoxHeight());
        return box;
    }

    public static Ellipse2D.Double createCircle(AnnotationCircleBean bean) {
        return new Ellipse2D.Double(bean.getX(), bean.getY(), bean.getWidth(), bean.getHeight());
    }

    public static Rectangle2D.Double createRectangle(AnnotationRectangleBean bean) {
        return new Rectangle2D.Double(bean.getX(), bean.getY(), bean.getWidth(), bean.getHeight());
    }

    public static JDialog createEditingDialog(Graphics2D g2d, Annotation a, Component c) {
        AnnotationEditingPanel editingPanel = new AnnotationEditingPanel(a);
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(c));
        dialog.add(editingPanel);
        dialog.setLocation((int) (a.getAnnotationRectangle().getX() + a.getAnnotationRectangle().getWidth()),
                (int) (a.getAnnotationRectangle().getY() + a.getAnnotationRectangle().getHeight()));
        dialog.setSize(500, 150);
        dialog.setModal(true);
        return dialog;
    }

    public static List<Annotation> reLetter(List<Annotation> annotList) {
        List<Annotation> modifiedList = new ArrayList<>(annotList.size());
        AnnotationLetterFactory.setCurrentLetter(AnnotationLetterFactory.RESET);
        for (Annotation a : annotList) {
            Annotation newA  = new Annotation(a);
            newA.getAnnotationCircle().setSymbol(AnnotationLetterFactory.createLetter());
            modifiedList.add(newA);
        }
        return modifiedList;
    }

}
