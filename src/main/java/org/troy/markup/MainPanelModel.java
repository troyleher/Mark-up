/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import org.troy.markup.beans.AnnotationRectangleBean;
import org.troy.markup.beans.AnnotationCircleBean;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Troy
 */
public class MainPanelModel implements ListSelectionListener {

    public static int MIN_RECTANGLE_WIDTH = 10;
    public static int MIN_RECTANGLE_HEIGHT = 10;
    public static String ADD_ANNOTATION_TO_LIST = "addAnnotationToList";
    public static String CURRENT_SELECTED_RECTANGLE = "setCurrentSelectedAnnotationRectangle";

    private AnnotationRectangleBean currentSelectedAnnotationRectangle;
    private int dragWidth;  //Current dragged width
    private int dragHeight;
    private int xPos;       //Current mouse pressed pos
    private int yPos;
    private boolean draggingRectangle;
    private boolean draggingCircle;
    private boolean isOCREnabled;
    private Font annotationFont;
    private Color annotationFontColor;
    private int mouseBoxWidth = 10;
    private int mouseboxHieght = 10;
    private Stroke focusedAnnotationStroke;
    private Stroke blurredAnnotationStroke;
    private Color focusedAnnotationColor;
    private Color blurredAnnotationColor;
    private AnnotationCircleBean currentFocusedCircle;
    private AnnotationCircleBean currentPressedCircle;
    private ArrayList<Annotation> annotationList = new ArrayList<>();
    private JPanel panel;
    private Image image;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public MainPanelModel(JPanel panel) {
        this.panel = panel;
        annotationFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        annotationFontColor = Color.BLACK;
        blurredAnnotationStroke = new BasicStroke(1);
        focusedAnnotationStroke = new BasicStroke(3);
        blurredAnnotationColor = Color.LIGHT_GRAY;
        focusedAnnotationColor = Color.RED;
        draggingRectangle = false;
        draggingCircle = false;
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
        this.annotationList.add(annotation);
        pcs.firePropertyChange(ADD_ANNOTATION_TO_LIST, null, annotation);
    }

    public ArrayList<Annotation> getAnnotationList() {
        return annotationList;
    }

    public Rectangle getMouseBox(int x, int y) {
        return new Rectangle(x - mouseBoxWidth / 2,
                y - mouseboxHieght / 2,
                mouseBoxWidth,
                mouseboxHieght);
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

    public Stroke getFocusedAnnotationStroke() {
        return focusedAnnotationStroke;
    }

    public void setFocusedAnnotationStroke(Stroke focusedAnnotationStroke) {
        this.focusedAnnotationStroke = focusedAnnotationStroke;
    }

    public Stroke getBlurredAnnotationStroke() {
        return blurredAnnotationStroke;
    }

    public void setBlurredAnnotationStroke(Stroke unFocusedAnnotationStroke) {
        this.blurredAnnotationStroke = unFocusedAnnotationStroke;
    }

    public Color getFocusedAnnotationColor() {
        return focusedAnnotationColor;
    }

    public void setFocusedAnnotationColor(Color focusedAnnotationColor) {
        this.focusedAnnotationColor = focusedAnnotationColor;
    }

    public Color getBlurredAnnotationColor() {
        return blurredAnnotationColor;
    }

    public void setBlurredAnnotationColor(Color blurredAnnotationColor) {
        this.blurredAnnotationColor = blurredAnnotationColor;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isDraggingRectangle() {
        return draggingRectangle;
    }

    public void setDraggingRectangle(boolean draggingRectangle) {
        this.draggingRectangle = draggingRectangle;
    }

    public int getDragWidth() {
        return dragWidth;
    }

    public void setDragWidth(int dragWidth) {
        this.dragWidth = dragWidth;
    }

    public int getDragHeight() {
        return dragHeight;
    }

    public void setDragHeight(int dragHeight) {
        this.dragHeight = dragHeight;
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

    public boolean isDraggingCircle() {
        return draggingCircle;
    }

    public void setDraggingCircle(boolean draggingCircle) {
        this.draggingCircle = draggingCircle;
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
        if(!e.getValueIsAdjusting()){
            Annotation a = annotationList.get(e.getFirstIndex());
            if (currentSelectedAnnotationRectangle == null
                    || !currentSelectedAnnotationRectangle.equals(a.getAnnotationRectangle())) {
                setCurrentSelectedAnnotationRectangle(a.getAnnotationRectangle());
            }
            System.out.println("List selection changed");
        }
        
    }

}
