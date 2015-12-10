/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import org.troy.markup.memento.UndoRedoManager;
import org.troy.markup.memento.UndoRedoManagerImpl;

/**
 *
 * @author Troy
 */
public class DeleteAction extends AbstractAction {

    private MainPanelModel model;
    private final int rowToDelete;

    public DeleteAction(String text, Icon icon, String toolTip, int mnenimonic, int indexToDelete) {
        super(text, icon);
        this.rowToDelete = indexToDelete;
        putValue(SHORT_DESCRIPTION, toolTip);
        putValue(MNEMONIC_KEY, mnenimonic);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        UndoRedoManager undoRedoManager = UndoRedoManagerImpl.getInstance();
        //System.out.println(rowToDelete);
        List<Annotation> aList = model.getAnnotationList();
        aList.remove(rowToDelete);
        undoRedoManager.save(aList);
        //aList = Utilities.reLetter(aList);
        model.setAnnotationList(aList);
        model.addAnnotationToList(null); //hack to refresh table :( TODO 

    }

    public MainPanelModel getModel() {
        return model;
    }

    public void setModel(MainPanelModel model) {
        this.model = model;
    }

}
