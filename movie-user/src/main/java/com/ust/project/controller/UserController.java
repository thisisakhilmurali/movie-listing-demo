package com.ust.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.project.dto.MovieDto;
import com.ust.project.dto.RatingDto;
import com.ust.project.model.Movie;
import com.ust.project.model.Rating;
import com.ust.project.service.UserService;

@RestController
@RequestMapping("/api/1.0/users")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/viewAllMovies")
	public ResponseEntity<List<Movie>> getAllMovies()
	{
		return ResponseEntity.ok().body(userService.fetchAllMovies());
	}
	
	
	@GetMapping("search/movie/name/{name}")
	public ResponseEntity<Movie> getMovieByName(@PathVariable String name)
	{
		return ResponseEntity.ok().body(userService.fecthMovieByName(name));
		
	}
	
	
	@GetMapping("search/movie/date/{date}")
	public ResponseEntity<List<Movie>> getMovieByDate(@PathVariable String date)
	{
		return ResponseEntity.ok().body(userService.fecthMovieByDate(date));
	}
	
	@PostMapping("add/rating/movie/{movieid}")
	public ResponseEntity<String> addRatingMovie(@RequestBody RatingDto ratingdto, @PathVariable Long movieid) {
		if (ratingdto.getRating() < 0 || ratingdto.getRating() > 10) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid rating value. Rating must be between 0 and 10.");
		}
		boolean ratingAdded = userService.addMovieRating(ratingdto, movieid);

		if (ratingAdded) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Rating added successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add rating.");
		}

	}

}
