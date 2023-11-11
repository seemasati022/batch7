package com.tejait.batch7.FactoryDesign;

import com.tejait.batch7.model.Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FactoryDesignPattern {

    /*
    * FactoryDesignPattern  ->
    * from FE -> we get 1//2/many as a string type
    * we need to convert this string to string[]
    * based on the string[] vals, we need to create objects
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
   List<FileGen> fileGenList = new ArrayList<>();

    public FactoryDesignPattern(String type) {          //{"pdf,txt,docx,xlsx"}
        String[] format = type.split(",");        //{"pdf","txt"."docx","xlsx"}
        for (String fileObj :format) {
            // pdf
            // txt
            // docx
            // xlsx
            // when iterating the string[] -> util-class fileOBjGen(String type)
            // takes in the type we get from FE(for now,lets say 1st pdf) and returns the PdfFile() obj of that and
            // the returned obj is now added to list and again
            // iteration is started for docx until how many for there in format
            FileGen fg = FileGenUtil.fileObjGen(fileObj);
            fileGenList.add(fg);
        }

    }

    public void executeFileOBjects(List<Employee> employeeList, String folder) throws IOException {
        for (FileGen Ifile:fileGenList) {               //all objects in the list will call its respective fileobj and return
            Ifile.genFile(employeeList, folder);
        }
    }

}
