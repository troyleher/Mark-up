/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Troy
 */
public class TestJAXB {

    public static void main(String[] args) {
        try {
            Annotation a = new Annotation(12, 12, 20, 20);
            a.setDetails("Hello world details");

            JAXBContext context = JAXBContext.newInstance(Annotation.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(a, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(TestJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
