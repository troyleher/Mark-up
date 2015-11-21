/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author Troy
 */
public class MainPanelModel {

    public static int MIN_RECTANGLE_WIDTH = 10;
    public static int MIN_RECTANGLE_HEIGHT = 10;

    private AnnotationRectangle currentSelectedAnnotationRectangle;
    private int dragWidth;  //Current dragged width
    private int dragHeight;
    private int xPos;       //Current mouse pressed pos
    private int yPos ;
    private boolean draggingRectangle;
    private boolean draggingCircle;
    private boolean isOCREnabled;
    private Font annotationFont;
    private Color annotationFontColor;
    private int mouseBoxWidth = 10;
    private int mouseboxHieght = 10;
    private Stroke focusedAnnotationStroke ;
    private Stroke blurredAnnotationStroke ;
    private Color focusedAnnotationColor;
    private Color blurredAnnotationColor;
    private AnnotationCircle currentFocusedCircle;
    private AnnotationCircle currentPressedCircle;
    private ArrayList<AnnotationRectangle> rectList = new ArrayList<>();
    private JPanel panel;
    private Image image;

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
        initState();
    }

    private void initState() {
        Iterator<AnnotationRectangle> list = rectList.iterator();
        while (list.hasNext()) {
            AnnotationRectangle rect = list.next();
            if (rect.getAnnotation().isFocused()) {
                currentFocusedCircle = rect.getAnnotation();
            }
        }
    }

    /**
     * @return the currentFocusedCircle
     */
    public AnnotationCircle getCurrentFocusedCircle() {
        return currentFocusedCircle;
    }

    /**
     * @param currentFocusedCircle the currentFocusedCircle to set
     */
    public void setCurrentFocusedCircle(AnnotationCircle currentFocusedCircle) {
        this.currentFocusedCircle = currentFocusedCircle;
    }

    /**
     * @return the currentPressedCircle
     */
    public AnnotationCircle getCurrentPressedCircle() {
        return currentPressedCircle;
    }

    /**
     * @param currentPressedCircle the currentPressedCircle to set
     */
    public void setCurrentPressedCircle(AnnotationCircle currentPressedCircle) {
        this.currentPressedCircle = currentPressedCircle;
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    public void addAnnotationRectangleToList(AnnotationRectangle rect) {
        this.rectList.add(rect);
    }

    public ArrayList<AnnotationRectangle> getAnnotationRectangleList() {
        return rectList;
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

    public AnnotationRectangle getCurrentSelectedAnnotationRectangle() {
        return currentSelectedAnnotationRectangle;
    }

    public void setCurrentSelectedAnnotationRectangle(AnnotationRectangle currentSelectedAnnotationRectangle) {
        this.currentSelectedAnnotationRectangle = currentSelectedAnnotationRectangle;
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
    
    
    
    
    
    
    

}
