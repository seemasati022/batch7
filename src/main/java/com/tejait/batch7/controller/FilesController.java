package com.tejait.batch7.controller;

import com.tejait.batch7.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("files")
public class FilesController {

    @Autowired
    private FilesService service;

        @PostMapping("uploadstatic")
        public ResponseEntity<Object> uploadStaticFile(@RequestParam MultipartFile file) throws IOException {
            File convertFile = new File("/Users/seemanthinisathi/Files/uploads" + file.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
        }
        
        
        @RequestMapping
        public ResponseEntity<?> upload (@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
            String uploadedFile = service.uploadFile(multipartFile);
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(uploadedFile);
        }
        
       /* @GetMapping("downloadstatic/{fileName}")
        private ResponseEntity<?> download(@PathVariable String fileName) throws IOException {
            byte[] bytes = service.downloadFile(fileName);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(bytes);
        }*/

//    download - json - static file
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadErrorData(Integer fileId) throws Exception {
         Path path = Paths.get("/System/Volumes/Data/macOS Install Data/UpdateBundle/AssetData/boot/EFI/SMCJSONs/Mac-35C1E88140C3E6CF.json");
        //Path path = Paths.get("/Users/seemanthinisathi/Downloads/unnamed.png");
        byte[] isr = Files.readAllBytes(path);
        String fileName = "Mac-35C1E88140C3E6CF.json";
       // String fileName="unnamed.png";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
    }

    //download - image - static file
    @GetMapping("/download1")
    public ResponseEntity<byte[]> downloadErrorData1(Integer fileId) throws Exception {
        Path path = Paths.get("/Users/seemanthinisathi/Downloads/unnamed.png");
        byte[] isr = Files.readAllBytes(path);
        String fileName = "unnamed.png";
        // String fileName="unnamed.png";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("image", "png"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
    }


}




