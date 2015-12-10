/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import org.troy.markup.memento.UndoRedoManager;
import org.troy.markup.memento.UndoRedoManagerImpl;

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
            UndoRedoManager undoRedoManager = UndoRedoManagerImpl.getInstance();
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/test3.png"));
            mainPanel = new MainPanel();
            MainPanelModel model = mainPanel.getModel();
            mainPanel.setImage(image);

            AnnotationMouseAdapter ama = new AnnotationMouseAdapter(model);
            model.addPropertyChangeListener(mainPanel);

            mainPanel.addMouseListener(ama);
            mainPanel.addMouseMotionListener(ama);

            JScrollPane scrollTablePane
                    = setUpTable(mainPanel);
            
            JMenuBar menuBar = new JMenuBar();
            
            JMenu fileMenu = new JMenu("File");
            menuBar.add(fileMenu);
            
            JMenu editMenu = new JMenu("Edit");
            UndoAction undoAction = new UndoAction("Undo", null,
                    "Undo last command", new Integer(KeyEvent.VK_Z) );
            undoRedoManager.addPropertyChangeListener(undoAction);
            RedoAction redoAction = new RedoAction("Redo", null,
                    "Redo last command", new Integer(KeyEvent.VK_R));
            redoAction.setModel(model);
            undoAction.setMainPanelModel(model);
            JMenuItem undoItem = new JMenuItem(undoAction);
            JMenuItem redoItem = new JMenuItem(redoAction);
            editMenu.add(undoItem);
            editMenu.add(redoItem);
            menuBar.add(editMenu);
            frame.setJMenuBar(menuBar);
            
            frame.add(scrollTablePane, BorderLayout.EAST);
            frame.add(mainPanel, BorderLayout.WEST);
            frame.pack();
            frame.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private JScrollPane setUpTable(MainPanel mainPanel) {
        MainPanelModel model = mainPanel.getModel();
        AnnotationTableModel tableModel
                = new AnnotationTableModel(mainPanel.getModel().getAnnotationList());
        AnnotationTable table = new AnnotationTable(tableModel);
        AnnotationTableMouseAdaptor atma =
                new AnnotationTableMouseAdaptor(table, mainPanel);
        table.addMouseListener(atma);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTablePane = new JScrollPane(table);
        model.addPropertyChangeListener(tableModel);
        model.setTable(table);
        model.getSelectedAnnotation().addPropertyChangeListener(table);
        model.getSelectedAnnotation().addPropertyChangeListener(mainPanel);
        table.getSelectionModel().addListSelectionListener(model);
        return scrollTablePane;
    }

}
