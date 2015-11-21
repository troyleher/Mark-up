/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.sourceforge.tess4j.TesseractException;
import org.troy.utilities.OCR;

/**
 *
 * @author Troy
 */
public class MainPanel extends JPanel implements MouseListener, MouseMotionListener {

    private int xTop = 0;
    private int yTop = 0;
    private int width = 0;
    private int height = 0;
    private boolean draw = false;
    private BufferedImage image = null;
    private boolean isOCREnabled = false;
    private BasicStroke notSelectedRectangleStroke;
    private BasicStroke isSelectedRectangleStroke;
    private AnnotationRectangle lastSelectedRectangle = null;
    private ArrayList<AnnotationRectangle> selectionList = new ArrayList<>();
    private AnnotationCircle currentAnnotationSymbol;
    private Font annotationFont;
    private Color annotationFontColor;
    private int minAnnotationRectWidth ;
    private int minAnnotationRectHeight ;
   

    public MainPanel(BufferedImage image) {

        this.image = image;

        annotationFont =  new Font(Font.SANS_SERIF, Font.BOLD, 12);
        annotationFontColor = Color.black;
        
        minAnnotationRectWidth = 10;
        minAnnotationRectHeight = 10;
        
        notSelectedRectangleStroke = new BasicStroke(1);
        isSelectedRectangleStroke = new BasicStroke(3);
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(image, 0, 0, null);
        if (draw && currentAnnotationSymbol == null) {
            g2d.drawRect(xTop, yTop, width, height);
        }
        Iterator<AnnotationRectangle> sList = selectionList.iterator();
        while (sList.hasNext()) {
            AnnotationRectangle rect = sList.next();
            if (!rect.isFocus()) {
                paintAnnotationRectangle(g2d, rect, Color.lightGray, notSelectedRectangleStroke);

            } else {
                paintAnnotationRectangle(g2d, rect, Color.RED, isSelectedRectangleStroke);
            }
        }
    }

    private void paintAnnotationRectangle(Graphics2D g2d, AnnotationRectangle rect, Color c, Stroke stroke) {
        g2d.setStroke(stroke);
        g2d.setColor(c);
        g2d.draw(rect);
        g2d.draw(rect.getAnnotation());
        paintAnnotationLetter(g2d, rect.getAnnotation());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Iterator<AnnotationRectangle> sList = selectionList.iterator();
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        Rectangle mousePoint = new Rectangle(e.getX() - 5, e.getY() - 5, 10, 10);
        while (sList.hasNext()) {
            AnnotationRectangle rect = sList.next();
            if (g2d.hit(mousePoint, rect, true)) {
                //System.out.println("Rectangle clicked");
                setSelected(rect);
            }

        }

        //System.out.println("Mouse clicked");

    }
    
    private void setSelected(AnnotationRectangle rect){
         if (lastSelectedRectangle != null) {
                    lastSelectedRectangle.setIsFocus(false);
                }
                rect.setIsFocus(true);
                lastSelectedRectangle = rect;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        xTop = e.getX();
        yTop = e.getY();
        draw = true;

        Iterator<AnnotationRectangle> sList = selectionList.iterator();
        Graphics2D g2d = (Graphics2D) this.getGraphics();
         Rectangle mousePoint = new Rectangle(e.getX() - 5, e.getY() - 5, 10, 10);
         
        while (sList.hasNext()) {
            AnnotationRectangle rect = sList.next();
            AnnotationCircle annotationSymbol = rect.getAnnotation();
            if (g2d.hit(mousePoint, annotationSymbol, true)) {
                //System.out.println("Annotation Symbol clicked");
                currentAnnotationSymbol = annotationSymbol;
                setSelected(rect);
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
    

    @Override
    public void mouseReleased(MouseEvent e) {
        draw = false;
        
        //System.out.println("Mouse released");
        //System.out.println("x=" + xTop + " y=" + yTop + " width=" + width + " height=" + height);

        saveAnnotationRectangle();
        if (isOCREnabled) {
            performOCR();
        }
        repaint();
        currentAnnotationSymbol = null;
        height = 0;
        width = 0;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        width = e.getX() - xTop;
        height = e.getY() - yTop;
       

        if (currentAnnotationSymbol != null) {
            
            System.out.println("Before dragg x=" + currentAnnotationSymbol.x);
            currentAnnotationSymbol.x = e.getX()-20;
            currentAnnotationSymbol.y = e.getY()-20 ;
            System.out.println("After dragg x=" + currentAnnotationSymbol.x);
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void performOCR() {
        BufferedImage imageToOCR = image.getSubimage(xTop, yTop, width, height);
        try {
            String result = OCR.parseImage(imageToOCR, "eng");
            //System.out.println(result);
        } catch (TesseractException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveAnnotationRectangle() {
        if (width > minAnnotationRectWidth && height > minAnnotationRectHeight && currentAnnotationSymbol == null) {
            AnnotationRectangle rect = new AnnotationRectangle(xTop, yTop, width, height);
            selectionList.add(rect);
        }
    }

    private void paintAnnotationLetter(Graphics2D g2d, AnnotationCircle annotation) {
        g2d.setFont(annotationFont);
        g2d.setColor(annotationFontColor);
        g2d.drawString(annotation.getLetter(), (int) annotation.x + 6, (int) annotation.y + 15);
    }

}
