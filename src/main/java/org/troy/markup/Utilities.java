/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

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

}
