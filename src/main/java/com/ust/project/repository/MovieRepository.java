package com.ust.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ust.project.entity.Movie;
import com.ust.project.entity.MovieDto;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
	

}
