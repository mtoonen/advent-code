package nl.meine.adventofcode._2021;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board instance;

    @BeforeEach
    void setUp() {
        instance = new Board(new Integer[][]{
                {3, 15, 0, 2, 22},
                {9, 18, 13, 17, 5},
                {19, 8, 7, 25, 23},
                {20, 11, 10, 24, 4},
                {14, 21, 16, 12, 6}
        });
    }
    @Test
    void checkBoard() {
        assertFalse(instance.checkBoard());
        instance.call(3);
        instance.call(9);
        instance.call(19);
        instance.call(20);
        instance.call(14);
        assertTrue(instance.checkBoard());
    }

    @Test
    void testCall(){
        instance.call(18);
        assertTrue(instance.called[1][1]);

    }

    @Test
    void rowCorrect() {
        assertEquals(-1,instance.rowCorrect());
        instance.call(3);
        instance.call(15);
        instance.call(0);
        instance.call(2);
        instance.call(22);
        assertEquals(0,instance.rowCorrect());
    }

    @Test
    void columnCorrect() {
        assertEquals(-1,instance.rowCorrect());
        instance.call(3);
        instance.call(9);
        instance.call(19);
        instance.call(20);
        instance.call(14);
        assertEquals(0,instance.columnCorrect());
    }

    @Test
    void sumRow() {
        assertEquals(42, instance.sumRow(0));
    }

    @Test
    void sumCol() {
        assertEquals(65, instance.sumCol(0));
    }
}
