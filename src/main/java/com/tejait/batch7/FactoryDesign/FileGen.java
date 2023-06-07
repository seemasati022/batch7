package com.tejait.batch7.FactoryDesign;

import java.io.IOException;
import java.util.List;

import com.tejait.batch7.model.Employee;

public interface FileGen {
	public abstract  void genFile(List<Employee> empList,String folder)throws IOException;
}
