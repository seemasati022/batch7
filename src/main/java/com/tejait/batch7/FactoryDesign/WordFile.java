package com.tejait.batch7.FactoryDesign;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.tejait.batch7.model.Employee;


public class WordFile implements FileGen{

	@Override
	public void genFile(List<Employee> empList, String folder) throws IOException {
		XWPFDocument document= new XWPFDocument();
		XWPFTable table = document.createTable();
		boolean isFirstime=true;
		for (Employee emp : empList) {
		XWPFTableRow row =null;
		if(isFirstime){
		row = table.getRow(0);
		row.getCell(0).setText(emp.getId()+"");
		row.addNewTableCell().setText(emp.getFname()+"");
		row.addNewTableCell().setText(emp.getLname()+"");
		row.addNewTableCell().setText(emp.getFullname()+"");
		row.addNewTableCell().setText(emp.getSalary()+"");
		row.addNewTableCell().setText(emp.getDept()+"");
		row.addNewTableCell().setText(emp.getEmpCode()+"");
		isFirstime=false;
		}else{
		row=table.createRow();
		row.getCell(0).setText(emp.getId()+"");
		row.getCell(1).setText(emp.getFname()+"");
		row.getCell(2).setText(emp.getLname()+"");
		row.getCell(3).setText(emp.getFullname()+"");
		row.getCell(4).setText(emp.getSalary()+"");
		row.getCell(5).setText(emp.getDept()+"");
		row.getCell(6).setText(emp.getEmpCode()+"");
	
		}

		XWPFParagraph paraGraph = document.createParagraph();
		XWPFRun run = paraGraph.createRun();
		run.setText(emp.getId()+" "+emp.getFname()+" "+emp.getLname()+"  "+emp.getFullname()+" "+emp.getSalary()+" "+emp.getDept()+" "+emp.getEmpCode());
		}

		FileOutputStream wordfos = new FileOutputStream(folder+"employee.docx");
		document.write(wordfos);
		wordfos.close();
	}

}
