package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Repository
public class MovieRepository {
    HashMap<String, Movie> moviemap;
    HashMap<String, Director> directormap;
    HashMap<String, List<String>> pairmap;

    public MovieRepository()
    {
        moviemap = new HashMap<>();
        directormap = new HashMap<>();
        pairmap = new HashMap<>();
    }

    public void addMovie(Movie movie)
    {
        moviemap.put(movie.getName(), movie);
    }
    public void addDirector(Director director)
    {
        directormap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie , String director)
    {
         List<String> list = null;
         if(!pairmap.containsKey(director))
         {
             list = new ArrayList<>();
             list.add(movie);
             pairmap.put(director,list);
             return;
         }
         list = pairmap.get(director);
         for(String strmovie : list)
         {
             if(strmovie.equalsIgnoreCase(movie))
             {
                 return;
             }
         }
         list.add(movie);
         pairmap.put(director,list);
    }
    public Movie getMovieByName(String movie)
    {
        return moviemap.getOrDefault(movie,null);
    }
    public Director getDirectorByName(String director)
    {
        return directormap.getOrDefault(director,null);
    }
    public List<String> getMoviesByDirectorName(String director)
    {
        return pairmap.getOrDefault(director,null);
    }
    public List<String> findAllMovies()
    {
        List<String> list = new ArrayList<>();
        for(String movie : moviemap.keySet())
        {
            list.add(movie);
        }
        return list;
    }
    public void deleteDirectorByName(String director)
    {
        if(directormap.containsKey(director))
        {
            directormap.remove(director);
            if(pairmap.containsKey(director))
            {
                List<String> list = pairmap.get(director);
                for(String movie : list)
                {
                    moviemap.remove(movie);
                }
                pairmap.remove(director);
            }
        }
    }
    public void deleteAllDirectors()
    {
        directormap = new HashMap<>();
        for(String director : pairmap.keySet())
        {
            List<String> list = pairmap.get(director);
            for(String movie : list)
            {
                moviemap.remove(movie);
            }

        }
        pairmap = new HashMap<>();
    }

}
