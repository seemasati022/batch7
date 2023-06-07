package com.tejait.batch7.FactoryDesign;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.tejait.batch7.model.Employee;


public class TextFile implements FileGen{

	@Override
	public void genFile(List<Employee> empList, String folder) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(folder+"employee.txt"));

		for(Employee emp:empList) {
		writer.write(emp.getId()+" "+emp.getFname()+" "+emp.getLname()+"  "+emp.getFullname()+" "+emp.getSalary()+" "+emp.getDept()+" "+emp.getEmpCode());
		writer.newLine();
		}
		writer.close();
	}

}
