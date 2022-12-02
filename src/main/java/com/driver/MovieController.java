package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController
{

    @Autowired
    MovieService service;


    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody(required = true) Movie movie)
    {
        service.addMovie(movie);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody(required = true) Director director)
    {
        service.addDirector(director);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director)
    {
        service.addMovieDirectorPair(movie, director);
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movie)
    {
        return new ResponseEntity<>(service.getMovieByName(movie),HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String director)
    {
        return new ResponseEntity<>(service.getDirectorByName(director),HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director)
    {
        return new ResponseEntity<>(service.getMoviesByDirectorName(director),HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies()
    {
        return new ResponseEntity<>(service.findAllMovies(),HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String director)
    {
        service.deleteDirectorByName(director);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        service.deleteAllDirectors();
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}
