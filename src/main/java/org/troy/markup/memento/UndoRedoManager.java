/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.beans.PropertyChangeListener;
import java.util.List;
import org.troy.markup.Annotation;

/**
 *
 * @author Troy
 */
public interface UndoRedoManager {
    public final static String SAVE_LIST_POPULATED = "saveListPopulated";
    public final static String SAVE_LIST_EMPTY = "saveListEmpty";

    public void save(List<Annotation> list);

    public List<Annotation> undo();

    public List<Annotation> redo();

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

}
