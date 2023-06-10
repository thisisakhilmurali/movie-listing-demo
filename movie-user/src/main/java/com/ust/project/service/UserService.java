package com.ust.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import java.util.concurrent.atomic.AtomicDouble;
import java.util.concurrent.atomic.AtomicReference;

import com.ust.project.dto.MovieDto;
import com.ust.project.dto.RatingDto;
import com.ust.project.model.Movie;
import com.ust.project.model.Rating;
import com.ust.project.repository.MovieRepository;
import com.ust.project.repository.RatingRepository;

@Service
public class UserService {
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	RatingRepository ratingRepository;

	public List<Movie> fetchAllMovies() {
		return movieRepository.findAll();
	}

	public Movie fecthMovieByName(String name) {
		return movieRepository.findByMovieName(name);
	}

	public List<Movie> fecthMovieByDate(String date) {
		return movieRepository.findAllByMovieReleaseDate(date);
	}

	
//	public boolean addMovieRating(RatingDto ratingDto, Long movieId) {
//	Optional<Movie> op= movieRepository.findById(movieId);
//		Movie movie = op.get();
//		Rating ratingObj = new Rating(movieId,ratingDto.getUserId(),ratingDto.getRating(),ratingDto.getMessage());
//		ratingRepository.save(ratingObj);
//		List<Rating> list = ratingRepository.findAllByMovieId(movieId);
//		double overallRate = 0.0;
//		list.forEach((e)->
//		{
//		overallRate += e.getRating();
//		});
//		overallRate = overallRate/list.size();
//		movie.setOverallRate(overallRate);
//		movieRepository.save(movie);
//		return true;
//	}
	

	public boolean addMovieRating(RatingDto ratingDto, Long movieId) {
	    Optional<Movie> op = movieRepository.findById(movieId);
	    Movie movie = op.get();
	    Rating ratingObj = new Rating();
	    ratingObj.setMovieId(ratingDto.getMovieId());
	    ratingObj.setUserId(ratingDto.getUserId());
	    ratingObj.setRating(ratingDto.getRating());
	    ratingObj.setMessage(ratingDto.getMessage());
//	    Rating ratingObj = new Rating(movieId, ratingDto.getUserId(), ratingDto.getRating(), ratingDto.getMessage());
	    ratingRepository.save(ratingObj);
	    List<Rating> list = ratingRepository.findAllByMovieId(movieId);
	    AtomicReference<Double> overallRate = new AtomicReference<>(0.0);
	    list.forEach((e) -> overallRate.updateAndGet(currentRate -> currentRate + e.getRating()));
	    overallRate.set(overallRate.get() / list.size());
	    movie.setOverallRate(overallRate.get());
	    movieRepository.save(movie);
	    return true;
	}

}
