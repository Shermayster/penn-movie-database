package movie.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieDatabase {
    private static ArrayList<Movie> movieList;
    private static ArrayList<Actor> actorList;

    public MovieDatabase() {
        movieList = new ArrayList<>();
        actorList = new ArrayList<>();
    }

    public static void main(String[] args) {
        // write your code here
        MovieDatabase movieDatabase = new MovieDatabase();
        readMoviesTextFile();
        readRatingsTextFile();
        System.out.println(getBestMovie());
        System.out.println(getBestActor());
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }


    public static void addMovie(String name, String[] actors) {
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(name, newActors);
        ArrayList<Actor> movieActors = new ArrayList<>();
        if (getMovieIndex(name) > -1) {
            return;
        }
        // add actors to movie
        for (int i = 0; i < actors.length; i++) {
            int ind = getActorIndex(actors[i]);
            if (ind > -1) {
                newActors.add(actorList.get(ind));
                movieActors.add(actorList.get(ind));
            } else {
                Actor newActor = addNewActor(actors[i], newMovie);
                actorList.add(newActor);
                movieActors.add(newActor);

            }
        }
        newMovie.setActors(movieActors);
        movieList.add(newMovie);
    }

    private static int getMovieIndex(String name) {
        int inDatabase = -1;
        for (int i = 0; i < movieList.size(); i++) {
            String listMovie = movieList.get(i).getName();
            if (listMovie.equals(name))
                inDatabase = i;
        }
        return inDatabase;
    }

    private static int getActorIndex(String actorName) {
        int actorInd = -1;
        for (int i = 0; i < actorList.size(); i++) {
            if (actorList.get(i).getName() == actorName)
                actorInd = i;
        }
        return actorInd;
    }

    private static Actor addNewActor(String actorName, Movie newMovie) {
        ArrayList<Movie> newActorMovies = new ArrayList<>();
        newActorMovies.add(newMovie);
        Actor newActor = new Actor();
        newActor.setName(actorName);
        newActor.setMovies(newActorMovies);
        return newActor;
    }

    public static void addRating(String name, double rating) {
        int actIndex = getMovieIndex(name);
        if (actIndex > -1) {
            movieList.get(actIndex).setRating(rating);
        }

    }

    public void updateRating(String name, double newRating) {
        addRating(name, newRating);
    }

    public static String getBestActor() {
        Actor result = actorList.get(0);
        for (int i = 1; i < actorList.size(); i++) {
            result = getBetterActor(result, actorList.get(i));
        }
        return result.getName();
    }

    ;

    public static String getBestMovie() {
        Movie result = movieList.get(0);
        for (int i = 1; i < movieList.size(); i++) {
            result = getBetterMovie(result, movieList.get(i));
        }
        return result.getName();
    }

    private static Movie getBetterMovie(Movie prevMovie, Movie currMovie) {
        if (prevMovie.getRating() > currMovie.getRating()) {
            return prevMovie;
        } else {
            return currMovie;
        }
    }

    private static Actor getBetterActor(Actor prevActor, Actor currActor) {
        if (isCurrentActorBetter(prevActor, currActor)) {
            return currActor;
        } else {
            return prevActor;
        }
    }

    private static boolean isCurrentActorBetter(Actor prevActor, Actor currActor) {
        return getActorAvrRating(currActor) > getActorAvrRating(prevActor);
    }

    private static int getActorAvrRating(Actor actor) {
        int allRatings = 0;
        for (int i = 0; i < actor.getMovies().size(); i++) {
            allRatings += actor.getMovies().get(i).getRating();
        }
        return allRatings / actor.getMovies().size();
    }

    private static void readMoviesTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/movie/database/movies.txt"))) {
            String sCurrentLine;
            ArrayList<String> movies = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) { // line by line
                Actor actor;
                String actorString = sCurrentLine;
                String[] actorArray = actorString.split(", "); // create array of string
                String actorName = actorArray[0]; // get Name of Actor
                ArrayList<Movie> actorMovieList = new ArrayList<>(); // get Films of actor
                actor = new Actor(); // create new Actor
                actor.setName(actorName);
                actor.setMovies(actorMovieList);
                actorList.add(actor); // add actor to actor list
                String[] actorsFilms = Arrays.copyOfRange(actorArray, 1, actorArray.length); // create list of
                for (int i = 0; i < actorsFilms.length; i++) {
                    Movie movie;
                    ArrayList<Actor> movieActorList = new ArrayList<>();
                    int movieIndex = findMovieIndex(actorsFilms[i]);
                    movie = new Movie(actorsFilms[i], movieActorList);
                    if (movieIndex == -1) {
                        movieActorList.add(actor);
                        movieList.add(movie);
                    } else {
                        ArrayList<Actor> movieActors = movieList.get(movieIndex).getActors();
                        movieActors.add(actor);
                        movieList.get(movieIndex).setActors(movieActors);
                    }
                    addMovieToActor(movie, actor);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readRatingsTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/movie/database/ratings.txt"))) {
            String sCurrentLine;
            boolean isFirstRound = true;
            while ((sCurrentLine = br.readLine()) != null) { // line by line
                if (!isFirstRound) {
                    String[] ratingArr = sCurrentLine.split("\\t");
                    addRating(ratingArr[0], Double.parseDouble(ratingArr[1]));
                } else {
                    isFirstRound = false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findMovieIndex(String mName) {
        int resultInd = -1;
        for (int i = 0; i < movieList.size(); i++) {
            String currentMovieName = movieList.get(i).getName();
            if (currentMovieName.equals(mName)) {
                resultInd = i;
            }
        }
        return resultInd;
    }

    private static void addMovieToActor(Movie movie, Actor actor) {
        ArrayList<Movie> actorMovies = actor.getMovies();
        int movieIndex = -1;
        if (actorMovies.size() > 0) {
            for (int i = 0; i < actorMovies.size(); i++) {
                if (actorMovies.get(i).getName().equals(movie.getName())) {
                    movieIndex = i;
                }
            }
        }
        if (movieIndex == -1) {
            actorMovies.add(movie);
            actor.setMovies(actorMovies);
        }


    }
}
