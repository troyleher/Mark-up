/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.troy.markup.memento.UndoRedoManager;
import org.troy.markup.memento.UndoRedoManagerImpl;
import org.troy.utilities.AnnotationUtilities;

/**
 *
 * @author Troy
 */
public class AnnotationEditingPanel extends JPanel {

    private JTextField textField;
    private Annotation annotation;
    private final List<Annotation> aList;

    public AnnotationEditingPanel(Annotation annotation, List<Annotation> aList){
        this.annotation = annotation;
        this.aList = aList;
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                initGUI();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 150);
    }
    

    private void initGUI() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        Font font = AnnotationUtilities.retriveFont();
        JPanel symbolPanel = new JPanel();
        symbolPanel.setLayout(new GridLayout(0, 4));
   

        Iterator<String> symbolList = getSymbols().iterator();
        while (symbolList.hasNext()) {
            String symbol = symbolList.next();
            JButton symbolButton = new JButton(new SymbolAction(symbol));
            symbolButton.setActionCommand(symbol);
            symbolButton.setFont(font);
            symbolPanel.add(symbolButton);
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(symbolPanel, c);

        JPanel textPanel = new JPanel();

        textField = new JTextField(20);
        textField.setFont(font);
        textPanel.add(textField);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(textPanel, c);
        textField.requestFocusInWindow();
        
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               annotation.setDetails(textField.getText());
                UndoRedoManager undoManager = UndoRedoManagerImpl.getInstance();
                undoManager.save(aList);
               SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
            }
        });
        buttonPanel.add(saveButton);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(buttonPanel, c);
    }

    private ArrayList<String> getSymbols() {
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("\u2300"); //Diameter
        symbols.add("\u27C2"); //Pependicular
        symbols.add("\u2225"); //Paralell to
        symbols.add("\u2104"); //Center line
        symbols.add("\u00B1"); //Tolerance symbol
        return symbols;
    }

    private class SymbolAction extends AbstractAction {

        public SymbolAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol = e.getActionCommand();
            textField.setText(textField.getText() + symbol);
            textField.requestFocusInWindow();
        }

    }

}
