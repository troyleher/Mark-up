/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.troy.markup.Annotation;
import org.troy.markup.Utilities;

/**
 *
 * @author Troy
 */
public class UndoRedoManagerImpl implements UndoRedoManager {

    private static UndoRedoManager annotationMemento;
    private List<List<Annotation>> saveList = new LinkedList<>();
    private List<List<Annotation>> redoList = new LinkedList<>();
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private UndoRedoManagerImpl() {
    }

    public static UndoRedoManager getInstance() {
        if (annotationMemento == null) {
            annotationMemento = new UndoRedoManagerImpl();
        }
        return annotationMemento;
    }

    @Override
    public void save(List<Annotation> list) {
        if (list != null && !list.isEmpty()) {
            saveList.add(Utilities.copy(list));
            pcs.firePropertyChange(SAVE_LIST_POPULATED, null, null);
            redoList.clear();
        }
    }

    @Override
    public List<Annotation> undo() {
        List<Annotation> currentList = new LinkedList<>();

        if (!saveList.isEmpty()) {
            redoList.add(saveList.remove(saveList.size() - 1));
            if (saveList.size() > 0) {
                currentList = Utilities.copy(saveList.get(saveList.size() - 1));
            }else{
                pcs.firePropertyChange(SAVE_LIST_EMPTY, null, null);
            }
        }
        return currentList;
    }

    @Override
    public List<Annotation> redo() {
        List<Annotation> l = null;
        if (!redoList.isEmpty()) {
            l = redoList.remove(redoList.size() - 1);
            saveList.add(l);
            pcs.firePropertyChange(SAVE_LIST_POPULATED, null, null);
        }
        return l;
    }

    public List<Annotation> getSaveListByIndex(int index) {
        return saveList.get(index);
    }

    public void resetMemento() {
        redoList = new LinkedList<>();
        saveList = new LinkedList<>();
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
    private void printList(List<Annotation> aList) {
        System.out.println("***************************");
        for (Annotation a : aList) {
            System.out.print(a.getAnnotationCircle().getSymbol());
            System.out.print(" " + a.getDetails() + "\n");
        }
        System.out.println("***************************");
    }

}
