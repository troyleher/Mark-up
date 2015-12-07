/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup.memento;

import java.util.List;
import org.troy.markup.Annotation;

/**
 *
 * @author Troy
 */
public class AnnotationMementoV2Imp implements AnnotationMementoV2{
    
    private static AnnotationMementoV2 annotationMemento ;
    private 
    
    private AnnotationMementoV2Imp(){}
    
    public static AnnotationMementoV2 getInstance(){
        if(annotationMemento == null){
            annotationMemento = new AnnotationMementoV2Imp();
        }
        return annotationMemento;
    }

    @Override
    public void save(List<Annotation> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Annotation> undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Annotation> redo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
