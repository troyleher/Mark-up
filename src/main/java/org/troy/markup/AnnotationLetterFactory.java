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
    private static ArrayList<Integer> letterListPostFix;

    private AnnotationLetterFactory() {

    }

    public static String createLetter() {
        StringBuilder returnLetter = new StringBuilder();

        if (letterList == null) {
            letterList = new ArrayList<>();
            letterList.add(65);
        }
        int letter = letterList.get(letterList.size() - 1);
        returnLetter.append(Character.toString((char) letter) );

        //if not Z increment ascii alphebet list
        if (!returnLetter.toString().equalsIgnoreCase("Z")) {
            letterList.add(letter + 1);
        } else {
            appendPostfixInteger(returnLetter);
        }
        return returnLetter.toString();
    }

    private static void appendPostfixInteger(StringBuilder returnLetter) {
        if (letterListPostFix == null) {
            letterListPostFix = new ArrayList<>();
            letterListPostFix.add(1);
        } else {
            int postfix = letterListPostFix.get(letterListPostFix.size() - 1);
            returnLetter = returnLetter.append( Integer.toString(postfix) );
            letterListPostFix.add(postfix + 1);
        }
    }
}
