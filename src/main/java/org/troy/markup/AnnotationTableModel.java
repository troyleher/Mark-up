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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Troy
 */
public class AnnotationTableModel extends AbstractTableModel implements PropertyChangeListener{

    private ArrayList<Annotation> annotationList;

    public AnnotationTableModel(ArrayList<Annotation> list) {
        this.annotationList = list;
        
        Iterator<Annotation> i = annotationList.iterator();
        while(i.hasNext()){
            Annotation a = i.next();
            a.addPropertyChangeListener(this);
        }
    }

    @Override
    public int getRowCount() {
        return annotationList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Annotation annotation = annotationList.get(rowIndex);
        String cell;
        switch (columnIndex) {
            case 0:
                cell = annotation.getSelectionLabel().getLetter();
                break;
            case 1:
                cell = annotation.getDetails();
                break;
            default:
                cell = "";
                break;
        }
        return cell;
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(MainPanelModel.ADD_ANNOTATION_TO_LIST.equalsIgnoreCase(evt.getPropertyName())){
            if(evt.getNewValue() instanceof Annotation){
                ((Annotation)evt.getNewValue()).addPropertyChangeListener(this);
            }
        }
        fireTableDataChanged();
    }

}
