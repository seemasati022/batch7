package com.tejait.batch7.service;


import com.tejait.batch7.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FilesService {


    String uploadFile(MultipartFile multipartFile) throws IOException;

    Optional<FileData> downloadFileFromDb(Integer fileId);

    byte[] downloadFile(String filename) throws IOException;
}
