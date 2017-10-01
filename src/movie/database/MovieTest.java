package movie.database;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MovieTest {
    @Test
    public void getName() throws Exception {
        ArrayList<Actor> actorListTest = new ArrayList<>();
        Movie testMovie = new Movie("Test Name", actorListTest);
        assertEquals(testMovie.getName(), "Test Name");
    }

    @Test
    public void getActors() throws Exception {
        ArrayList<Actor> actorListTest = new ArrayList<>();
        Actor testActor = new Actor("Test Name", new ArrayList<Movie>());
        actorListTest.add(testActor);
        Movie testMovie = new Movie("Test Name", actorListTest);
        assertEquals(testMovie.getActors(), actorListTest);
    }

    @Test
    public void getRating() throws Exception {
        ArrayList<Actor> actorListTest = new ArrayList<>();
        Movie testMovie = new Movie("Test Name", actorListTest);
        assertEquals(testMovie.getRating(), 80, 0);
    }

}