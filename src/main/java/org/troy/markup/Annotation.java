/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Troy
 */
@XmlRootElement
public class Annotation {

    private AnnotationRectangle selectionBox;
    private AnnotationCircle selectionLabel;

    private String details;
    public Annotation() {
    }

    public Annotation(double x, double y, double width, double height) {
        selectionBox = new AnnotationRectangle(x, y, width, height);
        selectionLabel = new AnnotationCircle(x - 25, y, AnnotationLetterFactory.createLetter());
        details = "TODO";
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public AnnotationRectangle getSelectionBox() {
        return selectionBox;
    }

    @XmlTransient
    public void setSelectionBox(AnnotationRectangle selectionBox) {
        this.selectionBox = selectionBox;
    }

    public AnnotationCircle getSelectionLabel() {
        return selectionLabel;
    }

    @XmlTransient
    public void setSelectionLabel(AnnotationCircle selectionLabel) {
        this.selectionLabel = selectionLabel;
    }

    @XmlElement(name = "circleModel")
    /**
     * Used only for JAXB marshaling to populate/serialize AnnotationCircle
     */
    private void setLabelModel(AnnotationCircleModel model) {
        if (selectionLabel == null) {
            selectionLabel = new AnnotationCircle(model.getX(), model.getY(),
                    model.getWidth(), model.getHeight(), model.getLabelID());
        } else {
            selectionLabel.x = model.getX();
            selectionLabel.y = model.getY();
            selectionLabel.width = model.getWidth();
            selectionLabel.height = model.getHeight();
            selectionLabel.setLetter(model.getLabelID());
        }
    }

    /**
     * Used only for JAXB marshaling to populate/serialize AnnotationCircle
     */
    private AnnotationCircleModel getLabelModel() {
        AnnotationCircleModel model = new AnnotationCircleModel();
        model.setX(selectionLabel.x);
        model.setY(selectionLabel.y);
        model.setWidth(selectionLabel.width);
        model.setHeight(selectionLabel.height);
        model.setLabelID(selectionLabel.getLetter());
        return model;
    }

    @XmlElement(name = "rectangleModel")
    private void setSelectionBoxModel(AnnotationRectangleModel m) {
        if (selectionBox == null) {
            selectionBox = new AnnotationRectangle(m.getX(),
                    m.getY(),
                    m.getWidth(),
                    m.getHeight());
        } else {
            selectionBox.x = m.getX();
            selectionBox.y = m.getY();
            selectionBox.width = m.getWidth();
            selectionBox.height = m.getHeight();
        }
    }

    private AnnotationRectangleModel getSelectionBoxModel() {
        AnnotationRectangleModel m = new AnnotationRectangleModel();
        m.setX(selectionBox.x);
        m.setY(selectionBox.y);
        m.setWidth(selectionBox.width);
        m.setHeight(selectionBox.height);
        return m;
    }

}
