package com.tejait.batch7.service.seriveImpl;

import com.tejait.batch7.model.Employee;
import com.tejait.batch7.model.FileData;
import com.tejait.batch7.repository.FilesRepository;
import com.tejait.batch7.service.FilesService;
import com.tejait.batch7.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    FilesRepository repository;


    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        FileData savedFile = repository.save(FileData.builder()
                .fileName(multipartFile.getOriginalFilename())
                .fileType(multipartFile.getContentType())
                .fileData(FileUtils.compressImage(multipartFile.getBytes()))
                .build());

        if (savedFile != null){
            return " file uploaded successfully "+multipartFile.getOriginalFilename();
        }
        return null;
    }

    @Override
    public Optional<FileData> downloadFileFromDb(Integer fileId){
        Optional<FileData> byId = repository.findById(fileId);
        return byId;
    }
    @Override
    public byte[] downloadFile(String filename) throws IOException {
        Optional<FileData> byFileName = repository.findByFileName(filename);
        byte[] bytes = FileUtils.decompressImage(byFileName.get().getFileData());
        return bytes;

  /*      File file = new File("/Users/seemanthinisathi/Downloads/FileStream/test.txt");
        String fileName = StringUtils.cleanPath(file.getName());
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader irs = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(irs);
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
        //FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());*/
    }





}
