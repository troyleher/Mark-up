/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.util.ArrayList;
import org.troy.markup.Annotation;

/**
 *
 * @author Troy
 */
public class AnnotationMementoMediator {

    private int saveIndex = 0;
    private int currentIndex = 0;

    private final AnnotationOriginator annotationOriginator;
    private final AnnotationCareTaker annotationCareTaker;
    private static AnnotationMementoMediator amm;

    private AnnotationMementoMediator() {
        saveIndex = 0;
        currentIndex = 0;
        annotationOriginator = new AnnotationOriginator();
        annotationCareTaker = new AnnotationCareTaker();
    }

    public static AnnotationMementoMediator getInstance() {
        if (amm == null) {
            amm = new AnnotationMementoMediator();
        }
        return amm;
    }

    public void save(ArrayList<Annotation> annotList) {
        annotationOriginator.setState(annotList);
        annotationCareTaker.addMemento(annotationOriginator.saveStateToMemento());
        saveIndex++;
        currentIndex++;
        //System.out.println("save annotation memento :currentIndex=" + currentIndex + " :saveIndex=" + saveIndex);
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
            //System.out.println("undo annotation memento :currentIndex=" + currentIndex + " :saveIndex=" + saveIndex);
            if (currentIndex > 0) {
                aList = annotationOriginator.
                        restoreFromMemento(annotationCareTaker.getMemento(currentIndex - 1));
            }else{
                aList = new  ArrayList<>();
            }
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
        if (currentIndex < saveIndex) {
            ++currentIndex;
            aList = annotationOriginator.
                    restoreFromMemento(annotationCareTaker.getMemento(currentIndex-1));
        } else {
            aList = annotationOriginator.restoreFromMemento(annotationCareTaker.getMemento(currentIndex-1));
        }
        return aList;
    }

}
