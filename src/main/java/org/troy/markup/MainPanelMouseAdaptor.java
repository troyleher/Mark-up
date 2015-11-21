/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Troy
 */
public class MainPanelMouseAdaptor extends MouseAdapter{

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x=" + e.getX() + " y=" + e.getY());
        Object o = e.getSource();
        if(o instanceof JPanel){
            JPanel panel = (JPanel)o;
            Graphics2D gd = (Graphics2D)panel.getGraphics();
            gd.drawLine(e.getX(), e.getY(), e.getX() + 3, e.getY() + 3);
        }
    }
    
    
}
