package com.tejait.batch7.repository;

import com.tejait.batch7.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<FileData, Integer> {
    Optional<FileData> findByFileName(String fileName);

}
