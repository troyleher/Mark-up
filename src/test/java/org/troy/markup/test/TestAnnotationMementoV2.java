package org.troy.markup.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.troy.markup.Annotation;
import org.troy.markup.memento.UndoRedoManager;
import org.troy.markup.memento.UndoRedoMangerImpl;

/**
 *
 * @author Troy
 */
public class TestAnnotationMementoV2 {

    public TestAnnotationMementoV2() {
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
    public void testSave() {
        List<Annotation> listToSave = new ArrayList<>();
        Annotation a1 = new Annotation(20, 20, 100, 100);
        Annotation a2 = new Annotation(20, 20, 80, 80);
        listToSave.add(a1);
        listToSave.add(a2);

        UndoRedoMangerImpl amV2 = (UndoRedoMangerImpl) UndoRedoMangerImpl.getInstance();
        amV2.save(listToSave);

        List<Annotation> saveList = amV2.getSaveListByIndex(0);
        assertTrue(saveList.size() == 2);

    }

    @Test
    public void testUndo1() {

        UndoRedoMangerImpl amV2 = (UndoRedoMangerImpl) UndoRedoMangerImpl.getInstance();
        amV2.resetMemento();

        List<Annotation> listToSave1 = new ArrayList<>();
        Annotation a1 = new Annotation(20, 20, 100, 100);
        Annotation a2 = new Annotation(20, 20, 80, 80);
        listToSave1.add(a1);
        listToSave1.add(a2);

        amV2.save(listToSave1);

        List<Annotation> listToSave2 = new ArrayList<>();
        Annotation a3 = new Annotation(21, 20, 100, 100);
        Annotation a4 = new Annotation(21, 20, 80, 80);
        Annotation a5 = new Annotation(21, 20, 80, 80);
        listToSave2.add(a3);
        listToSave2.add(a4);
        listToSave2.add(a5);

        amV2.save(listToSave2);
        
        List<Annotation> listToSave3 = new ArrayList<>();
        Annotation a6 = new Annotation(20, 20, 100, 100);
        Annotation a7 = new Annotation(20, 20, 80, 80);
        listToSave3.add(a6);
        listToSave3.add(a7);

        amV2.save(listToSave3);

        List<Annotation> undoList = amV2.undo();

        assertTrue(undoList.size() == 3);

    }

    @Test
    public void testUndo2(){
        //Test for 1 element left in save list
        UndoRedoMangerImpl amV2 = (UndoRedoMangerImpl) UndoRedoMangerImpl.getInstance();
        amV2.resetMemento();

        List<Annotation> listToSave1 = new ArrayList<>();
        Annotation a1 = new Annotation(20, 20, 100, 100);
        Annotation a2 = new Annotation(20, 20, 80, 80);
        listToSave1.add(a1);
        listToSave1.add(a2);

        amV2.save(listToSave1);
        List<Annotation> undoResultList = amV2.undo();
        assertNull(undoResultList);

    }
    @Test
    public void testRedo() {
        UndoRedoMangerImpl amV2 = (UndoRedoMangerImpl) UndoRedoMangerImpl.getInstance();
        amV2.resetMemento();

        List<Annotation> listToSave1 = new ArrayList<>();
        Annotation a1 = new Annotation(20, 20, 100, 100);
        Annotation a2 = new Annotation(20, 20, 80, 80);
        listToSave1.add(a1);
        listToSave1.add(a2);

        amV2.save(listToSave1);

        List<Annotation> listToSave2 = new ArrayList<>();
        Annotation a3 = new Annotation(21, 20, 100, 100);
        Annotation a4 = new Annotation(21, 20, 80, 80);
        Annotation a5 = new Annotation(21, 20, 80, 80);
        listToSave2.add(a3);
        listToSave2.add(a4);
        listToSave2.add(a5);

        amV2.save(listToSave2);
        
        amV2.undo();
        List<Annotation> redoList = amV2.redo();
        assertTrue(redoList.size() == 3);
    }
}
