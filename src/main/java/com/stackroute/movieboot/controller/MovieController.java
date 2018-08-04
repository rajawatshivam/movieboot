package com.stackroute.movieboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieboot.domain.Movie;
import com.stackroute.movieboot.exceptions.MovieNotFoundException;
import com.stackroute.movieboot.services.MovieService;


@RestController
@RequestMapping("/api/v1/movieboot")
public class MovieController {
	
	private MovieService movieservice;
	

	
	@Autowired
	public MovieController(@Qualifier("MovieServiceImpl") MovieService movieservice) {
		super();
		
		this.movieservice = movieservice;
		
	}
	@RequestMapping(value = "/movie", method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		Movie savedmovie=movieservice.saveMovie(movie);
		return new ResponseEntity<Movie>(savedmovie, HttpStatus.CREATED);


	}
	
	@RequestMapping(value = "/movies", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<?>  getallMovie() {
		List<Movie> movies=movieservice.getallMovie();
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}
	@RequestMapping(value = "/movie/{movieId}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<?> deleteNote(@PathVariable int movieId){
		
		boolean flag = movieservice.deleteMovie(movieId);
		if (flag) {

			return new ResponseEntity<String>("{ \"message\": \"" + "deleted" + "\"}", HttpStatus.OK);
		} else
			
			throw new MovieNotFoundException ("movie not found in this database");

	}
	@RequestMapping(value = "/movie", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<?> updateNoteToDB(@RequestParam int movieId, @RequestBody Movie movie) {

		boolean bool = movieservice.updateMovie(movie);
		if (bool) {
			return new ResponseEntity<String>("{ \"message\": \"" + "verified" + "\"}", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("{ \"message\": \"" + " not verified" + "\"}", HttpStatus.CONFLICT);

	}
	@RequestMapping(value = "/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNoteById(@RequestParam int movieId) {
		Movie movie =movieservice.getbyId(movieId);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}
	@RequestMapping(value = "/title/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNoteByTitle(@RequestParam String movieTitle) {
		
		Movie movie =movieservice.getbyTitle(movieTitle);
		
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}
	@RequestMapping(value = "/rating/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNoteByRating(@RequestParam int movieRating) {
		Movie movie =movieservice.getbyRating(movieRating);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}
	@RequestMapping(value = "/year/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNoteByYear(@RequestParam int releaseYear) {
		Movie movie =movieservice.getbyYear(releaseYear);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}
	@RequestMapping(value = "/bychar/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNoteByYear(@RequestParam("term") String searchTerm) {
		List<Movie> movie =movieservice.getByMovieAlpha(searchTerm);
		return new ResponseEntity<List<Movie>>(movie, HttpStatus.OK);

	}
	
}
