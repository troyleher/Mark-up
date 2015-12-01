/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.util.ArrayList;

/**
 *
 * @author Troy
 */
public class AnnotationCareTaker {
    private ArrayList<AnnotationMemento> mementoList = new ArrayList<>();
    
    public AnnotationCareTaker(){
        
    }
    
    public void addMemento(AnnotationMemento am){
        mementoList.add(am);
    }
    public AnnotationMemento getMemento(int index){
        return mementoList.get(index);
    }
    
}
