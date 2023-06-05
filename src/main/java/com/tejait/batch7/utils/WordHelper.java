package com.tejait.batch7.utils;

import java.io.*;
import java.util.List;

import com.tejait.batch7.model.Employee;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

public class WordHelper {

    static String imgFile = "/Users/seemanthinisathi/Downloads/unnamed.png";


    public static ByteArrayInputStream generateWord(List<Employee> allEmployee) throws FileNotFoundException, IOException, InvalidFormatException {

        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFParagraph p1 = doc.createParagraph();
            XWPFTable table = doc.createTable();
            boolean isFirstime = true;
            for (Employee emp : allEmployee) {
                XWPFTableRow row = null;
                if (isFirstime) {
                    row = table.getRow(0);
                    row.getCell(0).setText(emp.getId() + "");
                    row.addNewTableCell().setText(emp.getFname() + "");
                    row.addNewTableCell().setText(emp.getLname() + "");
                    row.addNewTableCell().setText(emp.getFullname() + "");
                    row.addNewTableCell().setText(emp.getSalary() + "");
                    row.addNewTableCell().setText(emp.getDept() + "");
                    row.addNewTableCell().setText(emp.getEmpCode() + "");
                    isFirstime = false;
                } else {
                    row = table.createRow();
                    row.getCell(0).setText(emp.getId() + "");
                    row.getCell(1).setText(emp.getFname() + "");
                    row.getCell(2).setText(emp.getLname() + "");
                    row.getCell(3).setText(emp.getFullname() + "");
                    row.getCell(4).setText(emp.getSalary() + "");
                    row.getCell(5).setText(emp.getDept() + "");
                    row.getCell(6).setText(emp.getEmpCode() + "");

                }


                XWPFParagraph paraGraph = doc.createParagraph();
                XWPFRun run = paraGraph.createRun();
                run.setText(emp.getId() + " " + emp.getFname() + " " + emp.getLname() + "  " + emp.getFullname() + " " + emp.getSalary() + " " + emp.getDept() + " " + emp.getEmpCode());
                // add png image
                XWPFRun r4 = doc.createParagraph().createRun();
                r4.addBreak();
                try (FileInputStream is = new FileInputStream(imgFile)) {
                    r4.addPicture(is, Document.PICTURE_TYPE_PNG, imgFile,
                            Units.toEMU(500), Units.toEMU(200));

                }


            }

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            doc.write(b);
            return new ByteArrayInputStream(b.toByteArray());
        }

    }
}
