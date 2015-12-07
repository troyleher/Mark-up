/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.util.ArrayList;
import java.util.List;
import org.troy.markup.Annotation;

/**
 *
 * @author Troy
 */
public class AnnotationMementoMediator {

    private int saveIndex = -1;
    private int currentIndex = -1;

    private final AnnotationOriginator annotationOriginator;
    private final AnnotationCareTaker annotationCareTaker;
    private static AnnotationMementoMediator amm;

    private AnnotationMementoMediator() {

        annotationOriginator = new AnnotationOriginator();
        annotationCareTaker = new AnnotationCareTaker();
    }

    public static AnnotationMementoMediator getInstance() {
        if (amm == null) {
            amm = new AnnotationMementoMediator();
        }
        return amm;
    }

    public void save(List<Annotation> annotList) {

        if (currentIndex < saveIndex && currentIndex >=0) {
            annotationCareTaker.removeFromMemento(currentIndex + 1);
            saveIndex = currentIndex;
        } 
            saveIndex++;
            currentIndex++;

     
        annotationOriginator.setState(annotList);
        annotationCareTaker.addMemento(annotationOriginator.saveStateToMemento());

        System.out.println("save annotation memento :currentIndex=" + currentIndex + " :saveIndex=" + saveIndex);
    }

    /**
     *
     * @return null if the undo command cant be undone or returns the last
     * state.
     */
    public ArrayList<Annotation> undo() {
        ArrayList<Annotation> aList = null;
        if (currentIndex >= 1) {
            --currentIndex;
            System.out.println("undo annotation memento :currentIndex=" + currentIndex + " :saveIndex=" + saveIndex);
            aList = annotationOriginator.
                    restoreFromMemento(annotationCareTaker.getMemento(currentIndex));

        } else {
            aList = new ArrayList<>();
        }
        return aList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Annotation> redo() {
        ArrayList<Annotation> aList = null;
        System.out.println("Redo currentIndex=" + currentIndex + " saveIndex=" + saveIndex);
        if (currentIndex < saveIndex && currentIndex >= 0) {
            aList = annotationOriginator.
                    restoreFromMemento(annotationCareTaker.getMemento(++currentIndex));

        }
        return aList;
    }

}
