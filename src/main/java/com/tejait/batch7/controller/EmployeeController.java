package com.tejait.batch7.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tejait.batch7.model.Employee;
import com.tejait.batch7.service.EmployeeService;
import com.tejait.batch7.utils.PdfGenerator;
import com.tejait.batch7.utils.UserExportExcel;
import com.tejait.batch7.utils.WordHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ContentDisposition;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;



/*
* @RestController    ->   this is marked as a component & spring created obj for this in ioc
* @RequestMapping("employee")    ->    class level mapping
* @RequestMapping(value = "saveEmp", method = RequestMethod.POST)    ->   method level mapping & post request call
*   the above saveEmp will be affixed to the class level mapping and gets the call based on the RequestMethod we provided
* @RequestBody Employee employee   ->  this reqbody allows to take the data from frontend JSON obj and set the value to employee obj here in java
* @RequestParam -> in case of strings, we use this and this is not included with url, this is passed in params as k,v
*/

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "saveEmp", method = RequestMethod.POST)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee empBody = employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(empBody, HttpStatus.OK);
    }

    @RequestMapping(value = "updateEmp", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee empBody = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(empBody, HttpStatus.OK);
    }

    @RequestMapping(value = "deleteEmp/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "getEmp/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Employee>> getEmpById(@PathVariable Integer id) {
        Optional<Employee> employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> allEmployee = employeeService.getAllEmployee();
        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "getByFname", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmpsByFname(@RequestParam String fname) {
        return new ResponseEntity<>(employeeService.getEmpsByFname(fname), HttpStatus.OK);
    }

    @RequestMapping(value = "getByFnLn", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmpsByFnameAndLname(@RequestParam String fn, String ln) {
        return new ResponseEntity<>(employeeService.getEmpsByFAndLname(fn, ln), HttpStatus.OK);
    }

    @RequestMapping(value = "getEmpsBetweenSalary/{sal1}/{sal2}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmpsBetweenSalary(@PathVariable("sal1") Long sal1, @PathVariable(name = "sal2") Long sal2) {
        return new ResponseEntity<>(employeeService.getEmpsBetweenSal(sal1, sal2), HttpStatus.OK);
    }

    @RequestMapping(value = "getEmpsGreaterThanAge/{a}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmpsGreaterThanGivenAge(@PathVariable Integer a) {
        return new ResponseEntity<>(employeeService.getEmpsListByGreaterThanAge(a), HttpStatus.OK);
    }

    @RequestMapping(value = "getEmpListContains", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmpListContaining(@RequestParam String name) {
        return new ResponseEntity<>(employeeService.getEmpListNameContains(name), HttpStatus.OK);
    }

    @RequestMapping(value = "getEmpSortedListDesc/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getSortedListDesc(@PathVariable Integer age) {
        return new ResponseEntity<>(employeeService.getEmpListBasedOnNameDesc(age), HttpStatus.OK);
    }

    @RequestMapping(value = "getEmpListDistinct", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getDistinctList(@RequestParam String name) {
        return new ResponseEntity<>(employeeService.getDistinctLnameList(name), HttpStatus.OK);
    }

    @RequestMapping(value = "getSalIn/{sal}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getInEmp(@PathVariable List<Long> sal) {
        return new ResponseEntity<>(employeeService.getSalaryIn(sal), HttpStatus.OK);
    }

    @RequestMapping(value = "getEmpAgeIsNull", method = RequestMethod.GET)
    public ResponseEntity<Optional<Employee>> getEmpAgeIsNull(@RequestParam Integer age) {
        return new ResponseEntity<>(employeeService.getAgeIsNull(), HttpStatus.OK);
    }

    @RequestMapping(value = "getPagination", method = RequestMethod.GET)
    public Page<Employee> getPagination(@RequestParam Integer pagenum, Integer recsize){
        return employeeService.getPagination(pagenum,recsize);
    }

    @RequestMapping(value = "getLikeData", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getLikeData(@RequestParam String lastName){
        return new ResponseEntity<>(employeeService.getLikeData(lastName),HttpStatus.OK);
    }

    @RequestMapping(value = "search/{val}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getSearchData(@PathVariable String val){
        return new ResponseEntity<>(employeeService.getCompleteSearchData(val),HttpStatus.OK);
    }

    @RequestMapping(value = "searchFilter",method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> searchFileterList(@RequestParam String filterType, String empCode){
        return new ResponseEntity<>(employeeService.searchFilter(filterType,empCode),HttpStatus.OK);
    }


//download -  emp -  json
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadErrorData() throws Exception {
        List<Employee> employees = employeeService.getAllEmployee();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employees);
        byte[] isr = json.getBytes();
        String fileName = "employees.json";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
    }


//  text - emps
    @RequestMapping(value="textfile" , method = RequestMethod.GET)
    public void  texfilecreate(){
        List<Employee> list =employeeService.getAllEmployee();
        try {
            File file=new File("/Users/seemanthinisathi/Files/downloads/emps.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bf = new BufferedWriter(fw );
            for(Employee e:list) {
                bf.write(e.getFname()+" "+e.getLname()+" "+e.getFullname()+" "+String.valueOf(e.getAge()));
                bf.newLine();
            }
            bf.flush();
            bf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//         PDF -   emp
    @GetMapping("/emps/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Employee> listEmps = employeeService.getAllEmployee();
        PdfGenerator pdfGenerator = new PdfGenerator(listEmps);
        pdfGenerator.export(response);
    }


//  excel -emp
    @GetMapping("export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Employee> listEmps = employeeService.getAllEmployee();

        UserExportExcel excelExporter = new UserExportExcel(listEmps);

        excelExporter.export(response);
    }

//      word - emps
    @GetMapping(value = "/word",
            produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> word() throws IOException, InvalidFormatException {
        ByteArrayInputStream bis = WordHelper.generateWord(employeeService.getAllEmployee());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=mydoc.docx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
    }


    // download - docx file - from text file. not connected with empservice
    @GetMapping(value = "/msworddocument", produces = { "application/vnd.openxmlformats-officedocument.wordprocessingml.document\n" })
    public ResponseEntity<byte[]> download() {
        try {
            File file = ResourceUtils.getFile("/Users/seemanthinisathi/Files/downloads/emps.txt");
            byte[] contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment().filename("file-sample_100kB.doc").build());
            return new ResponseEntity<>(contents, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
