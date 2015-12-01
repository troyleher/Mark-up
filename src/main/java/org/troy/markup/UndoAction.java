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
public class UndoAction extends AbstractAction {

    private MainPanelModel mainPanelModel;

    public UndoAction(String text, Icon icon, String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainPanelModel != null) {
            ArrayList<Annotation> aList = AnnotationMementoMediator.getInstance().undo();
            if (aList != null) {
                mainPanelModel.setAnnotationList(aList);
                System.out.println("Undo command clicked");
            }

        }

    }

    public MainPanelModel getMainPanelModel() {
        return mainPanelModel;
    }

    public void setMainPanelModel(MainPanelModel mainPanelModel) {
        this.mainPanelModel = mainPanelModel;
    }

}
