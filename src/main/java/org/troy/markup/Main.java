/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Troy
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Main main = new Main();
                main.displayGUI();
            }
        });

    }

    public void displayGUI() {
        JFrame frame = new JFrame("Mark Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel mainPanel;
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/test3.png"));
            mainPanel = new MainPanel();
            mainPanel.setImage(image);

            AnnotationCircleMouseListener circleListener
                    = new AnnotationCircleMouseListener(mainPanel.getModel());
            mainPanel.addMouseListener(circleListener);
            mainPanel.addMouseMotionListener(circleListener);

            AnnotationRectangleMouseEventHandler rectangleListener
                    = new AnnotationRectangleMouseEventHandler(mainPanel.getModel());
            mainPanel.addMouseListener(rectangleListener);
            mainPanel.addMouseMotionListener(rectangleListener);

            AnnotationTableModel tableModel = 
                    new AnnotationTableModel(mainPanel.getModel().getAnnotationList());
            AnnotationTable table = new AnnotationTable(tableModel);
            JScrollPane scrollTablePane = new JScrollPane(table);
            mainPanel.getModel().addPropertyChangeListener(tableModel);
            
            
            frame.add(scrollTablePane, BorderLayout.EAST);
            frame.add(mainPanel, BorderLayout.WEST);
            frame.pack();
            frame.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
