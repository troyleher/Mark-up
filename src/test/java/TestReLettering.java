/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.troy.markup.Annotation;
import org.troy.markup.Utilities;

/**
 *
 * @author Troy
 */
public class TestReLettering {

    public TestReLettering() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testReLettering() {

        List<Annotation> annotationListToBeReordered = new ArrayList<>();
        Annotation a1 = new Annotation(0, 0, 0, 0);
        Annotation a2 = new Annotation(0, 0, 0, 0);
        Annotation a3 = new Annotation(0, 0, 0, 0);

        annotationListToBeReordered.add(a1);
        annotationListToBeReordered.add(a2);
        annotationListToBeReordered.add(a3);

        annotationListToBeReordered.remove(a2);

        List<Annotation> reLetteredList = Utilities.reLetter(annotationListToBeReordered);
        assertTrue(reLetteredList.get(1).getAnnotationCircle().getSymbol().equalsIgnoreCase("b"));

    }
}
