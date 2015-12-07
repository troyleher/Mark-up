/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.troy.markup.Annotation;

/**
 *
 * @author Troy
 */
public class AnnotationCareTaker {

    private ArrayList<AnnotationMemento> mementoList = new ArrayList<>();

    public AnnotationCareTaker() {

    }

    public void addMemento(AnnotationMemento am) {
        mementoList.add(am);
        for (Annotation a : am.getState()) {
            System.out.println("circle symbol = " + a.getAnnotationCircle().getSymbol());
        }
    }

    public AnnotationMemento getMemento(int index) {
        return mementoList.get(index);
    }

    public void removeFromMemento(int startIndex) {
        List<AnnotationMemento> am = mementoList.subList(0, startIndex);
        ArrayList<AnnotationMemento> tempList =  new ArrayList<>();
        for(AnnotationMemento m : am ){
            tempList.add(m);
        }
        mementoList = tempList;
        System.out.println("Elements left in memento = " + mementoList.size());
        for(AnnotationMemento a : mementoList){
            for(Annotation aa : a.getState()){
                System.out.println("circle left = " + aa.getAnnotationCircle().getSymbol() );
            }
            System.out.println("********");
        }
        
    }

}
