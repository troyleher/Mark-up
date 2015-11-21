/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Troy
 */
public class OCR {

    public static String parseImage(BufferedImage image, String rule) throws TesseractException {
        String result = null;
        Tesseract ocr = new Tesseract();
        ocr.setDatapath("data");
        ocr.setLanguage("eng");
        result = ocr.doOCR(image);
        return result;
    }

   

}
