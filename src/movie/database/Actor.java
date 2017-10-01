package movie.database;

import java.util.ArrayList;

public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    public Actor(String actorName, ArrayList<Movie> movies) {
        setName(actorName);
        setMovies(movies);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

}
