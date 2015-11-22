/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Troy
 */
public class AnnotationEditingPanel extends JPanel{
    
    public AnnotationEditingPanel(){
    
        JTextField textField = new JTextField(20);
        add(textField, BorderLayout.CENTER);
    }
    
}
