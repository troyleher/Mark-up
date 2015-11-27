/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import org.troy.markup.beans.AnnotationRectangleBean;
import org.troy.markup.beans.AnnotationCircleBean;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Troy
 */
public class Utilities {

    private static int mouseBoxWidth = 10;
    private static int mouseBoxHieght = 10;

    public static Rectangle getMouseBox(MouseEvent e) {
        Rectangle box = new Rectangle(e.getX() - mouseBoxWidth / 2,
                e.getY() - mouseBoxHieght / 2,
                mouseBoxWidth,
                mouseBoxHieght);
        return box;
    }
    public static Ellipse2D.Double createCircle(AnnotationCircleBean bean){
        return new Ellipse2D.Double(bean.getX(), bean.getY(), bean.getWidth(), bean.getHeight());
    }
    public static Rectangle2D.Double createRectangle(AnnotationRectangleBean bean){
        return new Rectangle2D.Double(bean.getX(), bean.getY(), bean.getWidth(), bean.getHeight());
    }

}
