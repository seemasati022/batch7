package com.tejait.batch7.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tejait.batch7.model.Employee;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    private List<Employee> EmpList;

    public PdfGenerator(List<Employee> empList) {
        EmpList = empList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("fname", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("lname", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("fullName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("dept", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("age", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("salary", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("empCode", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Employee emp : EmpList) {
            table.addCell(String.valueOf(emp.getId()));
            table.addCell(emp.getFname());
            table.addCell(emp.getLname());
            table.addCell(emp.getFullname());
            table.addCell(emp.getDept());
            table.addCell(String.valueOf(emp.getAge()));
            table.addCell(String.valueOf(emp.getSalary()));
            table.addCell(emp.getEmpCode());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Employee", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();

    }

}
