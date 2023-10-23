package com.ebsoft.ebstudytemplates4weekbackend.domain.file.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
