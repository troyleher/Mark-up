/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.troy.markup.AnnotationEditingPanel;

/**
 *
 * @author Troy
 */
public class AnnotationUtilities {
    
     public static Font retriveFont() {
        URL fontUrl = AnnotationEditingPanel.class.getResource("/fonts/FreeSerif.ttf");
        Font font = null;
        try {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
                GraphicsEnvironment ge
                        = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);

            } catch (FontFormatException ex) {
                Logger.getLogger(AnnotationEditingPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(AnnotationEditingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return font.deriveFont(Font.PLAIN, 20f);
    }
}
