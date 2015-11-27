/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import org.troy.markup.beans.AnnotationRectangleBean;

/**
 *
 * @author Troy
 */
public class AnnotationTable extends JTable implements PropertyChangeListener{
    
    public AnnotationTable(TableModel dataModel){
        super(dataModel);
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       if(MainPanelModel.CURRENT_SELECTED_RECTANGLE.equalsIgnoreCase(evt.getPropertyName())){
            setRowSelectionInterval(0,
                    getAnnotationIndex((AnnotationRectangleBean)evt.getNewValue()));
        }
    }
    private int getAnnotationIndex(AnnotationRectangleBean rectangle) {
        int index = -1;
        ArrayList<Annotation> annotationList = 
                ((AnnotationTableModel)this.getModel()).getAnnotationList();
        Iterator<Annotation> i = annotationList.iterator();
        int c = 0;
        while(i.hasNext()){
            Annotation a = i.next();
            AnnotationRectangleBean rect = a.getAnnotationRectangle();
            if(rect.equals(rectangle)){
                index = c;
            }
            c++;
        }
        return index;
    }
    
}
