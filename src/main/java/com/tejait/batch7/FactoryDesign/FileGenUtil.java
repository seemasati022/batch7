package com.tejait.batch7.FactoryDesign;

public class FileGenUtil {

    public static FileGen fileObjGen(String fileType){     // this method return FileGen(I) based on typeType which we get from FE
                                                          //and cretes object for that fileType and adds to list and then returns

     FileGen fileGen = null;
     switch (fileType){
         case "pdf":
             fileGen = new PdfFile();
             break;
         case "txt":
             fileGen = new TextFile();
             break;
         case "xlsx":
             fileGen = new ExcelFile();
             break;
         case "docx":
             fileGen = new WordFile();
             break;
         default:
             break;
     }
        return fileGen;
    }

}
