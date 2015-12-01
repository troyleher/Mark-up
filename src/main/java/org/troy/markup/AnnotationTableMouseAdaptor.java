/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
            e.consume();
            int index = table.getSelectedRow();
            Annotation a = mainPanel.getModel().getAnnotationList().get(index);
            JDialog d = Utilities.createEditingDialog((Graphics2D)mainPanel.getGraphics(), a, mainPanel);
            d.setVisible(true);
        }
        displayContextMenu(e);

    }

    private void displayContextMenu(MouseEvent e) {
        
        if(SwingUtilities.isRightMouseButton(e)){
            //Change selected row to mouse clicked point
            int row = table.rowAtPoint(e.getPoint());
            table.getSelectionModel().setSelectionInterval(row, row);
            
            DeleteAction deleteAction = new DeleteAction("Delete",
                    null, "Remove annotation", new Integer(KeyEvent.VK_D ), row);
            deleteAction.setModel(mainPanel.getModel());
            JPopupMenu popUp = new JPopupMenu("Edit");
            popUp.add(deleteAction);
            popUp.show(e.getComponent(), e.getX(), e.getY());
        }
    }

}
