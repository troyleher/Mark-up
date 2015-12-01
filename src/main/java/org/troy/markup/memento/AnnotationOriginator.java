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
public class AnnotationOriginator {
    private ArrayList<Annotation> annotationList;
    
    public void setState(ArrayList<Annotation> annotationList){
        this.annotationList = annotationList;
    }
    
    public AnnotationMemento saveStateToMemento(){
      return new AnnotationMemento(annotationList);
   }

   public ArrayList<Annotation> restoreFromMemento(AnnotationMemento memento){
      return memento.getState();
   }
}
