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
        
        setPropertyChangeListenerForAnnotation();
    }

    private void setPropertyChangeListenerForAnnotation() {
       for(Annotation a : annotationList){
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
                cell = annotation.getAnnotationCircle().getSymbol();
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
        System.out.println("Property change event table model");
        if(MainPanelModel.ADD_ANNOTATION_TO_LIST.equalsIgnoreCase(evt.getPropertyName())){
            if(evt.getNewValue() instanceof Annotation){
                ((Annotation)evt.getNewValue()).addPropertyChangeListener(this);
            }
        }else
            if(MainPanelModel.ANNOTATION_LIST_CHANGED.equalsIgnoreCase(evt.getPropertyName())){
                this.annotationList = (ArrayList<Annotation>)evt.getNewValue();
                setPropertyChangeListenerForAnnotation();
                System.out.println("Table property change");
            }
        fireTableDataChanged();
    }

    public ArrayList<Annotation> getAnnotationList() {
        return annotationList;
    }
    
    

}
