/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import org.troy.markup.memento.UndoRedoManager;
import org.troy.markup.memento.UndoRedoManagerImpl;

/**
 *
 * @author Troy
 */
public class RedoAction extends AbstractAction implements PropertyChangeListener{
    
    private MainPanelModel model;
   

    public RedoAction(String text, Icon icon, String toolTip, Integer mnemonic){
        super(text, icon);
        putValue(SHORT_DESCRIPTION, toolTip);
        putValue(MNEMONIC_KEY, mnemonic);
        this.model = model;
        setEnabled(false);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(model != null){
            List<Annotation> aList = UndoRedoManagerImpl.getInstance().redo();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equalsIgnoreCase(UndoRedoManager.REDO_LIST_EMPTY)){
            setEnabled(false);
        }
        if(evt.getPropertyName().equalsIgnoreCase(UndoRedoManager.REDO_LIST_POPULATED)){
            setEnabled(true);
        }
    }
    
}
