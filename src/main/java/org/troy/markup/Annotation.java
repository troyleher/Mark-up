/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import org.troy.markup.beans.AnnotationRectangleBean;
import org.troy.markup.beans.SystemConfigBean;
import org.troy.markup.beans.AnnotationCircleBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Troy
 */
@XmlRootElement
public class Annotation {

    //TODO Create common interface for circle and rectangle
    private AnnotationRectangleBean annotationRectangle;
    private AnnotationCircleBean annotationCircle;
    private String details;
    private boolean highlight;
    private SystemConfigBean configBean;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Used only by JAXB, to marshal data to a persistant storage. Do not use
     * this constructor to create an annotation
     */
    private Annotation() {

    }

    public Annotation(double x, double y, double width, double height) {
        configBean = SystemConfigBean.createSystemConfigBean();

        annotationRectangle = new AnnotationRectangleBean(x, y, width, height);
        annotationCircle = new AnnotationCircleBean(
                x - configBean.getCircleXDistanceFromRectangle(),
                y,
                configBean.getCircleWidth(),
                configBean.getCircleHeight(),
                AnnotationLetterFactory.createLetter());
        details = configBean.getDetails();
    }

    public Annotation(Annotation a) {
        configBean = SystemConfigBean.createSystemConfigBean();
        this.annotationCircle = new AnnotationCircleBean(a.getAnnotationCircle());
        this.annotationRectangle = new AnnotationRectangleBean(a.annotationRectangle);
        this.details = a.getDetails();

    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        String oldDetails = this.details;
        this.details = details;
        pcs.firePropertyChange("details", oldDetails, details);
    }

    public AnnotationRectangleBean getAnnotationRectangle() {
        return annotationRectangle;
    }

    public void setAnnotationRectangle(AnnotationRectangleBean selectionBox) {
        this.annotationRectangle = selectionBox;
    }

    public AnnotationCircleBean getAnnotationCircle() {
        return annotationCircle;
    }

    public void setAnnotationcircle(AnnotationCircleBean annotationCircle) {
        this.annotationCircle = annotationCircle;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Annotation) {
            Annotation a = (Annotation) obj;
            if (a.getAnnotationCircle().getSymbol().
                    equals(this.annotationCircle.getSymbol())) {
                if (a.getDetails().equalsIgnoreCase(this.details)) {
                    equals = true;
                }
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return 31 * this.annotationCircle.getSymbol().hashCode();
    }

}
