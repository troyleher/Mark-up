/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.troy.markup;

import java.util.ArrayList;

/**
 *
 * @author Troy
 */
public class AnnotationLetterFactory {

    private static ArrayList<Integer> letterList;

    private AnnotationLetterFactory() {

    }

    public static String createLetter() {
        if (letterList == null) {
            letterList = new ArrayList<>();
            letterList.add(65);
        }
        int letter = letterList.get(letterList.size() - 1);
        letterList.add(letter + 1);
        return Character.toString((char) letter);
    }
}
