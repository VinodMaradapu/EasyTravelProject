package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.bean.UploadPhoto;
@Repository
public interface UploadPhotoRepository extends JpaRepository<UploadPhoto, Long>{

	@Query("SELECT c FROM UploadPhoto c WHERE c.name = :name")
	Optional<UploadPhoto> findByName(@Param("name") String name);

	@Query("SELECT c FROM UploadPhoto c WHERE c.name = :name")
	Optional<UploadPhoto> findByImageName(@Param("name")String name);


}
 