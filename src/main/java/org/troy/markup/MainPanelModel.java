/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import org.troy.markup.beans.AnnotationRectangleBean;
import org.troy.markup.beans.AnnotationCircleBean;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.troy.markup.view.DrawMouseBehaviour;

/**
 *
 * @author Troy
 */
public class MainPanelModel implements ListSelectionListener {

    public static int MIN_RECTANGLE_WIDTH = 10;
    public static int MIN_RECTANGLE_HEIGHT = 10;
    public static String ADD_ANNOTATION_TO_LIST = "addAnnotationToList";
    public static String CURRENT_SELECTED_RECTANGLE = "setCurrentSelectedAnnotationRectangle";
    public static String SELECTED_ANNOTATION_CHANGED = "setSelectedAnnotation";
    public static String ANNOTATION_LIST_CHANGED = "annotationListChanged";

    private AnnotationRectangleBean currentSelectedAnnotationRectangle;
    private boolean isOCREnabled;
    private Font annotationFont;
    private Color annotationFontColor;
    private AnnotationCircleBean currentFocusedCircle;
    private AnnotationCircleBean currentPressedCircle;
    private List<Annotation> annotationList = new ArrayList<>();
    private JPanel panel;
    private Image image;
    private SelectedAnnotation selectedAnnotation = new SelectedAnnotation(null);
    private DrawMouseBehaviour dmb;
    private JTable table;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public MainPanelModel(JPanel panel) {
        this.panel = panel;
        annotationFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        annotationFontColor = Color.BLACK;
        isOCREnabled = false;

    }

    /**
     * @return the currentFocusedCircle
     */
    public AnnotationCircleBean getCurrentFocusedCircle() {
        return currentFocusedCircle;
    }

    /**
     * @param currentFocusedCircle the currentFocusedCircle to set
     */
    public void setCurrentFocusedCircle(AnnotationCircleBean currentFocusedCircle) {
        this.currentFocusedCircle = currentFocusedCircle;
    }

    /**
     * @return the currentPressedCircle
     */
    public AnnotationCircleBean getCurrentPressedCircle() {
        return currentPressedCircle;
    }

    /**
     * @param currentPressedCircle the currentPressedCircle to set
     */
    public void setCurrentPressedCircle(AnnotationCircleBean currentPressedCircle) {
        this.currentPressedCircle = currentPressedCircle;
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    public void addAnnotationToList(Annotation annotation) {
        if (annotation != null) {
            this.annotationList.add(annotation);
        }
        pcs.firePropertyChange(ADD_ANNOTATION_TO_LIST, null, annotation);
    }

    public List<Annotation> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<Annotation> annotationList) {
        annotationList = Utilities.reLetter(annotationList);
        List<Annotation> oldList = this.annotationList;
        this.annotationList = annotationList;
        pcs.firePropertyChange(ANNOTATION_LIST_CHANGED, oldList, annotationList);
    }

    /**
     * @return the annotationFont
     */
    public Font getAnnotationFont() {
        return annotationFont;
    }

    public void setAnnotationFont(Font annotationFont) {
        this.annotationFont = annotationFont;
    }

    public Color getAnnotationFontColor() {
        return annotationFontColor;
    }

    public void setAnnotationFontColor(Color annotationFontColor) {
        this.annotationFontColor = annotationFontColor;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public AnnotationRectangleBean getCurrentSelectedAnnotationRectangle() {
        return currentSelectedAnnotationRectangle;
    }

    public void setCurrentSelectedAnnotationRectangle(AnnotationRectangleBean currentSelectedAnnotationRectangle) {
        AnnotationRectangleBean oldBean = this.currentSelectedAnnotationRectangle;
        this.currentSelectedAnnotationRectangle = currentSelectedAnnotationRectangle;
        if (oldBean == null || !oldBean.equals(currentSelectedAnnotationRectangle)) {
            pcs.firePropertyChange(CURRENT_SELECTED_RECTANGLE, oldBean, currentSelectedAnnotationRectangle);
        }
        System.out.println("current rectangle changed");

    }

    public boolean isOCREnabled() {
        return isOCREnabled;
    }

    public void setIsOCREnabled(boolean isOCREnabled) {
        this.isOCREnabled = isOCREnabled;
    }

    public DrawMouseBehaviour getDmb() {
        return dmb;
    }

    public void setDmb(DrawMouseBehaviour dmb) {
        this.dmb = dmb;
    }

    public void highlightAnnotation(Annotation annotation) {
        /**
         * if (this.getCurrentSelectedAnnotationRectangle() != null) {
         * this.getCurrentSelectedAnnotationRectangle().setIsFocus(false); }
         *
         */
        //annotation.getAnnotationRectangle().setIsFocus(true);
        this.setCurrentSelectedAnnotationRectangle(annotation.getAnnotationRectangle());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Annotation currentAnnotation = selectedAnnotation.getAnnotation();
        if (currentAnnotation != null && !e.getValueIsAdjusting()) {
            int itemIndex = table.getSelectedRow();
            if (itemIndex >= 0) {
                Annotation a = annotationList.get(itemIndex);
                if (!currentAnnotation.equals(a)) {
                    selectedAnnotation.setAnnotation(a);
                }
            }

        }

    }

    public SelectedAnnotation getSelectedAnnotation() {
        return selectedAnnotation;
    }

    public void setSelectedAnnotation(SelectedAnnotation selectedAnnotation) {
        SelectedAnnotation old = this.selectedAnnotation;
        this.selectedAnnotation = selectedAnnotation;
        if (!old.equals(selectedAnnotation)) {
            pcs.firePropertyChange(SELECTED_ANNOTATION_CHANGED, old, selectedAnnotation);
        }
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

}
