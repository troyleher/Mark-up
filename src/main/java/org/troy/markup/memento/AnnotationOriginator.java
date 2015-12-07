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
public class AnnotationOriginator {
    private List<Annotation> annotationList;
    
    public void setState(List<Annotation> annotationList){
        this.annotationList = annotationList;
    }
    
    public AnnotationMemento saveStateToMemento(){
      return new AnnotationMemento(annotationList);
   }

   public ArrayList<Annotation> restoreFromMemento(AnnotationMemento memento){
       annotationList = memento.getState();
      return memento.getState();
   }
}
