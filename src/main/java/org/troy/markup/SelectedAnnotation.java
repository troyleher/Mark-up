/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

/**
 *
 * @author Troy
 */
public class SelectedAnnotation {

    private Annotation annotation;
    private boolean mousePressedOnCircle = false;

    public SelectedAnnotation(Annotation a) {
        annotation = a;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public boolean isMousePressedOnCircle() {
        return mousePressedOnCircle;
    }

    public void setMousePressedOnCircle(boolean mousePressedOnCircle) {
        this.mousePressedOnCircle = mousePressedOnCircle;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if ((obj instanceof SelectedAnnotation)) {
            SelectedAnnotation sa = (SelectedAnnotation)obj;
            if(sa.getAnnotation().equals(this.annotation)){
                equals = true;
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return 31 * annotation.hashCode();
    }

}
