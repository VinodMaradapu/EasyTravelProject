package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.bean.PdfDocument;
@Repository
public interface PdfDocumentRepository extends JpaRepository<PdfDocument, Integer>{

	@Query("select c from PdfDocument c where c.fileName=:fileName")
	Optional<PdfDocument> findByFileName(@Param("fileName") String fileName);

}
 