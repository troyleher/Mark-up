/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import org.troy.markup.memento.AnnotationMementoMediator;

/**
 *
 * @author Troy
 */
public class RedoAction extends AbstractAction{
    
    private MainPanelModel model;
   

    public RedoAction(String text, Icon icon, String toolTip, Integer mnemonic){
        super(text, icon);
        putValue(SHORT_DESCRIPTION, toolTip);
        putValue(MNEMONIC_KEY, mnemonic);
        this.model = model;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(model != null){
            ArrayList<Annotation> aList = AnnotationMementoMediator.getInstance().redo();
            if(aList != null){
                model.setAnnotationList(aList);
            }
        }
        
    }

    public MainPanelModel getModel() {
        return model;
    }

    public void setModel(MainPanelModel model) {
        this.model = model;
    }
    
}
