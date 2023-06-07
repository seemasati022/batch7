package com.tejait.batch7.FactoryDesign;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.tejait.batch7.model.Employee;

public class PdfFile implements FileGen {

	@Override
	public void genFile(List<Employee> empList, String folder) {
		try {
			Document pdfDocument = new Document();
			FileOutputStream pdffos = new FileOutputStream(folder+"\\employee.pdf");
			PdfWriter.getInstance(pdfDocument, pdffos);
			pdfDocument.open();

			for(Employee emp:empList){
			Paragraph ph = new Paragraph(emp.getId()+" "+emp.getFname()+" "+emp.getLname()+"  "+emp.getFullname()+" "+emp.getSalary()+" "+emp.getDept()+" "+emp.getEmpCode());
			pdfDocument.add(ph);
			}
			pdfDocument.close();
			pdffos.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (DocumentException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}	
	}

}
