package movie.database;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MovieDatabaseTest {

    @Test
    public void main() throws Exception {
        // should add movies from text file
        MovieDatabase database = new MovieDatabase();
        String [] args = {};
        database.main(args);
        assertEquals(121, database.getActorList().size());
        assertEquals(315, database.getMovieList().size());
        assertEquals("Sleepers", database.getMovieList().get(0).getName());
        assertEquals(3, database.getMovieList().get(0).getActors().size());
        assertEquals("Dustin Hoffman", database.getMovieList().get(0).getActors().get(1).getName());
        // check if added films to Dustin Hoffman
        assertEquals(5, database.getMovieList().get(0).getActors().get(1).getMovies().size());
        // should add ratings to movies
        // assertEquals(74, database.getMovieList().get(0).getRating(), 0);

    }

    @Test
    /**
     * should add new movie
     */
    public void addMovie() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String moveName = "Sleepers";
        String testActorName0 = "Brad Pitt";
        String testActorName1 = "Dustin Hoffman";
        String[] testActorsArr = {testActorName0, testActorName1};
        ArrayList<Movie> testMoves = new ArrayList<>();
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(moveName, newActors);
        testMoves.add(newMovie);
        Actor newActor1 = new Actor();
        newActor1.setName("Brad Pitt");
        newActor1.setMovies(testMoves);
        newActors.add(newActor1);
        Actor newActor2 = new Actor();
        newActor2.setName("Dustin Hoffman");
        newActor2.setMovies(testMoves);
        newActors.add(newActor2);
        database.addMovie("Sleepers", testActorsArr);
        ArrayList<Movie> resultList = database.getMovieList();
        assertEquals(resultList.get(0).getName(), testMoves.get(0).getName());
        assertEquals(resultList.get(0).getRating(), testMoves.get(0).getRating(), 0);
        assertEquals(resultList.get(0).getActors().size(), testMoves.get(0).getActors().size());
    }

    @Test
    /**
     * should ignore movie if in database
     */
    public void ignoreAddMovie() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String moveName = "Sleepers";
        String testActorName0 = "Brad Pitt";
        String testActorName1 = "Dustin Hoffman";
        String[] testActorsArr = {testActorName0, testActorName1};
        ArrayList<Movie> testMoves = new ArrayList<>();
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(moveName, newActors);
        testMoves.add(newMovie);
        database.addMovie("Sleepers", testActorsArr);
        database.addMovie("Sleepers", testActorsArr);
        assertEquals(1, database.getMovieList().size());
    }

    @Test
    /**
     * should add new actor in new movie
     */
    public void addMovieNewActor() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String moveName = "Sleepers";
        String testActorName0 = "Brad Pitt";
        String testActorName1 = "Dustin Hoffman";
        String[] testActorsArr = {testActorName0, testActorName1};
        ArrayList<Movie> testMoves = new ArrayList<>();
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(moveName, newActors);
        testMoves.add(newMovie);
        database.addMovie("Sleepers", testActorsArr);
        assertEquals(2, database.getActorList().size());
    }

    @Test
    /**
     * should add actors to new Movie
     */
    public void addActorsMovie() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String moveName = "Sleepers";
        String testActorName0 = "Brad Pitt";
        String testActorName1 = "Dustin Hoffman";
        String[] testActorsArr = {testActorName0, testActorName1};
        ArrayList<Movie> testMoves = new ArrayList<>();
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(moveName, newActors);
        testMoves.add(newMovie);
        database.addMovie("Sleepers", testActorsArr);
        Movie databaseMovie = database.getMovieList().get(0);
        assertEquals(2, databaseMovie.getActors().size());
    }


    @Test
    public void addRating() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String moveName = "Sleepers";
        String testActorName0 = "Brad Pitt";
        String testActorName1 = "Dustin Hoffman";
        String[] testActorsArr = {testActorName0, testActorName1};
        ArrayList<Movie> testMoves = new ArrayList<>();
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(moveName, newActors);
        testMoves.add(newMovie);
        database.addMovie(moveName, testActorsArr);
        database.addRating(moveName, 80);
        Movie databaseMovie = database.getMovieList().get(0);
        assertEquals(80 ,databaseMovie.getRating(), 0);

    }

    @Test
    public void updateRating() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String moveName = "Sleepers";
        String testActorName0 = "Brad Pitt";
        String testActorName1 = "Dustin Hoffman";
        String[] testActorsArr = {testActorName0, testActorName1};
        ArrayList<Movie> testMoves = new ArrayList<>();
        ArrayList<Actor> newActors = new ArrayList<>();
        Movie newMovie = new Movie(moveName, newActors);
        testMoves.add(newMovie);
        database.addMovie(moveName, testActorsArr);
        database.addRating(moveName, 80);
        Movie databaseMovie = database.getMovieList().get(0);
        assertEquals(80 ,databaseMovie.getRating(), 0);
        database.updateRating(moveName, 99);
        assertEquals(99 ,databaseMovie.getRating(), 0);
    }

    @Test
    public void getBestMovie() throws Exception {
       MovieDatabase database = new MovieDatabase();
       String[] italianJobActors = {"Mark Wahlberg"};
       database.addMovie("Italian Job", italianJobActors);
       database.addRating("Italian Job", 73);
       String[] sinCityActors = {"Elijah Wood", "Mickey Rourke"};
       database.addMovie("Sin City", sinCityActors);
       database.addRating("Sin City", 78);
       assertEquals("Sin City", database.getBestMovie());
    }

    @Test
    public void getBestActor() throws Exception {
        MovieDatabase database = new MovieDatabase();
        String[] italianJobActors = {"Mark Wahlberg"};
        database.addMovie("Italian Job", italianJobActors);
        database.addRating("Italian Job", 73);
        String[] sinCityActors = {"Elijah Wood", "Mickey Rourke"};
        database.addMovie("Sin City", sinCityActors);
        database.addRating("Sin City", 78);
        String [] lordActors = {"Elijah Wood"};
        database.addMovie("The Lord of the Rings: The Return of the King", lordActors);
        database.addRating("The Lord of the Rings: The Return of the King", 95);
        String[] tedActors = {"Mark Wahlberg"};
        database.addMovie("Ted", tedActors);
        assertEquals("Elijah Wood", database.getBestActor());
    }

}