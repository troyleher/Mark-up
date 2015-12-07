/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Troy
 */
public class AnnotationTable extends JTable implements PropertyChangeListener {

    public AnnotationTable(TableModel dataModel) {
        super(dataModel);

    }
    

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (SelectedAnnotation.SET_ANNOTATION.equalsIgnoreCase(evt.getPropertyName())) {
            setRowSelectionInterval(0,
                    getAnnotationIndex((Annotation) evt.getNewValue()));
        }
        
    }

    private int getAnnotationIndex(Annotation selectedAnnotation) {
        int index = 0;
        if (selectedAnnotation != null) {
            List<Annotation> annotationList
                    = ((AnnotationTableModel) this.getModel()).getAnnotationList();
            for (Annotation a : annotationList) {
                if (a.equals(selectedAnnotation)) {
                    return index;
                }
                index++;
            }
        }
        return index;
    }


    

}
