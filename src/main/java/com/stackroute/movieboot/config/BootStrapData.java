package com.stackroute.movieboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.stackroute.movieboot.domain.Movie;
import com.stackroute.movieboot.repository.MovieRepository;



@Configuration
public class BootStrapData implements ApplicationListener<ContextRefreshedEvent> {
    private MovieRepository movieRepository;
    private Movie movie;

  
    @Autowired
    public BootStrapData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	Movie movie = new Movie();
		movie.setMovieId(1);
		movie.setMovieTitle("Logan");
		movie.setMovieRating(5);
		movie.setReleaseYear(2018);
		movieRepository.save(movie);
    	Movie testMovie = Movie.builder()
							.movieId(2)
				 			.movieTitle("foo")
				 			.movieRating(5)
				 		    .releaseYear(2018)
				 			.build();
		movieRepository.save(testMovie);
  }

}