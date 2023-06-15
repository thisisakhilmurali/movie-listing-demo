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
import com.ust.project.exception.MovieNotFoundException;
import com.ust.project.exception.MoviesNotFoundException;
import com.ust.project.model.Movie;
import com.ust.project.model.Rating;
import com.ust.project.repository.MovieRepository;
import com.ust.project.repository.RatingRepository;

@Service
public class HomeService {
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	RatingRepository ratingRepository;

	public List<Movie> fetchAllMovies() throws MoviesNotFoundException {
		List<Movie> movies = movieRepository.findAll();
		if (movies.isEmpty()) {
            throw new MoviesNotFoundException("No movies found");
        }
		return movies;
	}

	
	public Movie fecthMovieByName(String name) throws MovieNotFoundException{
		Movie movie = movieRepository.findByMovieName(name);
		if(movie !=null)
		{
			return movie;
		}
		else
		{
			throw new MovieNotFoundException("Movie with name " + name + " not found");
		}
	}

	public List<Movie> fecthMovieByDate(String date) throws MoviesNotFoundException{
		List<Movie> movies = movieRepository.findAllByMovieReleaseDate(date);
		if (movies.isEmpty()) {
            throw new MoviesNotFoundException("No movies found with the release date: " + date);
        }
		return movies;
	}
	

	public boolean addMovieRating(RatingDto ratingDto, Long movieId,Long userId) {
	    Optional<Movie> op = movieRepository.findById(movieId);
	    
	    if(op.isPresent())
	    {
	    
	    	Optional<List<Rating>> op2 =  ratingRepository.findByUserId(userId);
	    	List<Rating> ratingObjList = op2.get();
	    	int flag=0;
	    	for(Rating obj:ratingObjList)
	    	{
	    		if(obj.getMovieId()==movieId)
			    	{
				    obj.setRating(ratingDto.getRating());
				    obj.setMessage(ratingDto.getMessage());
				    flag=1;
				    ratingRepository.save(obj);
			    	}
	    	}
	    	if(flag==0)
	    	{
	    		 Rating ratingObj = new Rating();
	    		    ratingObj.setMovieId(movieId);
	    		    ratingObj.setUserId(userId);
	    		    ratingObj.setRating(ratingDto.getRating());
	    		    ratingObj.setMessage(ratingDto.getMessage());
	    		    ratingRepository.save(ratingObj);
	    	}
	    	
	    }
	    else
	    {
	    return false;
	    }
	    Movie movie = op.get();
	    List<Rating> list = ratingRepository.findAllByMovieId(movieId);
	    AtomicReference<Double> overallRate = new AtomicReference<>(0.0);
	    list.forEach((e) -> overallRate.updateAndGet(currentRate -> currentRate + e.getRating()));
	    overallRate.set(overallRate.get() / list.size());
	    movie.setOverallRate(overallRate.get());
	    movieRepository.save(movie);
	    return true;
	    }
	    
	}


