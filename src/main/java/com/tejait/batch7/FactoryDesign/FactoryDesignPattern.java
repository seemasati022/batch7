package com.tejait.batch7.FactoryDesign;

import java.util.ArrayList;
import java.util.List;

public class FactoryDesignPattern {

    /*
    * FactoryDesignPattern  ->
    * from FE -> we get 1//2/many as a string type
    * we need to convert this string to string[]
    * based on the string[] vals, we need to create objs
    *
    *
    *
    * Call Parent class(I) method.
    * Based on the obj, it redirects/calls the child class impl method.
    * everyObj returned from FileGenUtil has to be stored in the List.
    * Otherwise, the objects get overridden and only last called obj is returned in FE
    *
    * */

   // private String type;

    public FactoryDesignPattern(String type) {          //{"pdf,txt,docx,xlsx"}
        String[] format = type.split(",");        //{"pdf","txt"."docx","xlsx"}

        List<FileGen> fileGenList = new ArrayList<>();

        for (String fileObj :format) {
            // pdf
            // txt
            // docx
            // xlsx
            // when iterating the string[] -> util-class fileOBjGen(String type)
            // takes in the type we get from FE(for now,lets say 1st pdf) and returns the PdfFile() obj of that and
            // the returned obj is now added to list and again
            // iteration is started for docx until how many for there in format
            FileGen fg = FileGenUtil.fileObjGen(type);
            fileGenList.add(fg);
        }

    }

}
