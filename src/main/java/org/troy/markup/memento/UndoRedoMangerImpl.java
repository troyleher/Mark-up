/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.troy.markup.Annotation;
import org.troy.markup.Utilities;

/**
 *
 * @author Troy
 */
public class UndoRedoMangerImpl implements UndoRedoManager {

    private static UndoRedoManager annotationMemento;
    private List<List<Annotation>> saveList = new LinkedList<>();
    private List<List<Annotation>> redoList = new LinkedList<>();

    private UndoRedoMangerImpl() {
    }

    public static UndoRedoManager getInstance() {
        if (annotationMemento == null) {
            annotationMemento = new UndoRedoMangerImpl();
        }
        return annotationMemento;
    }

    @Override
    public void save(List<Annotation> list) {
        if (list != null && !list.isEmpty()) {
            saveList.add(Utilities.copy(list));
            redoList.clear();
        }
    }

    @Override
    public List<Annotation> undo() {
        List<Annotation> currentList = null;

        if (!saveList.isEmpty()) {
            redoList.add(saveList.remove(saveList.size() - 1));
            if (saveList.size() > 0) {
                currentList = Utilities.copy(saveList.get(saveList.size() - 1));
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

}
