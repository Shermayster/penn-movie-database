package movie.database;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ActorTest {
    @Test
    public void getName() throws Exception {
        String actorName = "Tom Hanks";
        ArrayList<Movie> testMovies = new ArrayList<>();
        Actor testActor = new Actor();
        testActor.setName(actorName);
        testActor.setMovies(testMovies);
        String testResult = testActor.getName();
        assertEquals(testResult, actorName);
    }


    @Test
    public void getMovies() throws Exception {
        String actorName = "Tom Hanks";
        Movie testMovie = new Movie("", new ArrayList<Actor>());
        ArrayList <Movie> testMovies = new ArrayList<>();
        testMovies.add(testMovie);
        Actor testActor = new Actor();
        testActor.setName(actorName);
        testActor.setMovies(testMovies);
        ArrayList<Movie> resultTest = testActor.getMovies();
        assertEquals(resultTest, testMovies);
    }

}