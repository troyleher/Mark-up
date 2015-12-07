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
public class AnnotationMemento {
    private final ArrayList<Annotation> annotationList;
    
    public AnnotationMemento(List<Annotation> annotationList){
        //create a deep copy of annotationList
        this.annotationList = new  ArrayList<>(annotationList.size());
        for(Annotation a : annotationList){
            this.annotationList.add(new Annotation(a));
        }
    }
    public ArrayList<Annotation> getState(){
        return annotationList;
    }
    
}
