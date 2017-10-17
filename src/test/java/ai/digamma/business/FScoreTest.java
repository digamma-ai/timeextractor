package ai.digamma.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FScoreTest {
    FScore fScore;

    @Before
    public void before() {
        fScore = new FScore();
    }

    @Test
    public void precisionTest() {
        double precision = fScore.precision(10, 0);
        assertEquals(1.0, precision, 0.01);

        double precision2 = fScore.precision(10, 1);
        assertEquals(0.9, precision2, 0.01);

        double precision3 = fScore.precision(5, 5);
        assertEquals(0.5, precision3, 0.01);

    }

    @Test
    public void recallTest() {
        double recall = fScore.recall(10, 0);
        assertEquals(1.0, recall, 0.01);

        double recall2 = fScore.recall(5, 5);
        assertEquals(0.5, recall2, 0.01);

    }

    @Test
    public void accuracyTest() {
        double accuracy = fScore.accuracy(10, 10, 0, 0);
        assertEquals(1.0, accuracy, 0.01);

        double accuracy2 = fScore.accuracy(10, 10, 10, 10);
        assertEquals(0.5, accuracy2, 0.01);

    }

    @Test
    public void fScoreTest() {
        double score = fScore.f1Score(1.0, 1.0);
        assertEquals(1.0, score, 0.01);

    }

    @Test
    public void fScoreTest2() {
        double score = fScore.f1Score(1.0, 0.5);
        assertEquals(0.66, score, 0.01);

    }

}
