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
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Troy
 */
public class AnnotationTableMouseAdaptor extends MouseAdapter {

    private JTable table;
    private MainPanel mainPanel;

    public AnnotationTableMouseAdaptor(JTable table, MainPanel mainPanel) {
        this.table = table;
        this.mainPanel = mainPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            e.consume();
            int index = table.getSelectedRow();
            Annotation a = mainPanel.getModel().getAnnotationList().get(index);
            JDialog d = Utilities.createEditingDialog((Graphics2D)mainPanel.getGraphics(), a, mainPanel);
            d.setVisible(true);
        }

    }

}
