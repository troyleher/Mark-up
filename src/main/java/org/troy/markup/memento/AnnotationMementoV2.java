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
public interface AnnotationMementoV2 {
    
    public void save(List<Annotation> list);
    public List<Annotation> undo();
    public List<Annotation> redo();
    
}
