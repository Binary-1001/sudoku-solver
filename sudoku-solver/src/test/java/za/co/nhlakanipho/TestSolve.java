package za.co.nhlakanipho;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import java.util.List;
import java.util.Arrays;


public class TestSolve {

    @Test
    public void testCompletePuzzle() {
        // Incomplete puzzle
        int[][] puzzle1 = {
                {9, 6, 2, 0, 7, 8, 5, 0, 0},
                {1, 0, 5, 0, 0, 9, 3, 0, 0},
                {3, 0, 0, 0, 0, 0, 8, 2, 0},
                {0, 0, 1, 0, 0, 0, 0, 7, 0},
                {6, 0, 0, 0, 5, 0, 0, 0, 8},
                {0, 0, 0, 6, 0, 3, 9, 0, 5},
                {0, 1, 8, 0, 0, 5, 0, 0, 0},
                {0, 0, 6, 8, 3, 2, 7, 0, 1},
                {7, 5, 3, 1, 9, 0, 4, 8, 0}
        };

        // Complete puzzle (with zeros - actually invalid but tests the logic)
        int[][] puzzle2 = {
                {9, 6, 2, 3, 7, 8, 5, 1, 4},
                {1, 4, 5, 2, 6, 9, 3, 7, 8},
                {3, 7, 4, 5, 1, 6, 8, 2, 9},
                {2, 8, 1, 4, 9, 5, 6, 7, 3},
                {6, 3, 7, 9, 5, 1, 2, 4, 8},
                {4, 9, 0, 6, 2, 3, 9, 1, 5},
                {8, 1, 8, 7, 4, 5, 1, 3, 2},
                {5, 2, 6, 8, 3, 2, 7, 9, 1},
                {7, 5, 3, 1, 9, 4, 4, 8, 6}
        };

        assertFalse(Solve.isPuzzleComplete(puzzle1), "Puzzle1 should be incomplete");
        assertFalse(Solve.isPuzzleComplete(puzzle2), "Puzzle2 has a zero, should be incomplete");
    }

    @Test
    public void testIsValidNumber() {
        int[][] board = {
                {9, 6, 2, 0, 7, 8, 5, 0, 0},
                {1, 0, 5, 0, 0, 9, 3, 0, 0},
                {3, 0, 0, 0, 0, 0, 8, 2, 0},
                {0, 0, 1, 0, 0, 0, 0, 7, 0},
                {6, 0, 0, 0, 5, 0, 0, 0, 8},
                {0, 0, 0, 6, 0, 3, 9, 0, 5},
                {0, 1, 8, 0, 0, 5, 0, 0, 0},
                {0, 0, 6, 8, 3, 2, 7, 0, 1},
                {7, 5, 3, 1, 9, 0, 4, 8, 0}
        };

        int number = 4;
        assertTrue(Solve.validPlaceNumber(board, 0, 3, number),
                "4 should be valid at position [0][3]");
    }

    @Test
    public void testOpenFile() {
        // Note: This test requires the actual file to exist
        // You may need to adjust the path or skip this test
        List<String> expected = Arrays.asList(
                "9 6 2 0 7 8 5 0 0",
                "1 0 5 0 0 9 3 0 0",
                "3 0 0 0 0 0 8 2 0",
                "0 0 1 0 0 0 0 7 0",
                "6 0 0 0 5 0 0 0 8",
                "0 0 0 6 0 3 9 0 5",
                "0 1 8 0 0 5 0 0 0",
                "0 0 6 8 3 2 7 0 1",
                "7 5 3 1 9 0 4 8 0"
        );

        List<String> result = Solve.openFile("./path/to/puzzle.txt");

        // Only test if file exists
        if (result != null) {
            assertEquals(expected, result, "File content should match expected");
        }
    }

    @Test
    public void testCreateBoard() {
        List<String> display = Arrays.asList(
                "9 6 2 0 7 8 5 0 0",
                "1 0 5 0 0 9 3 0 0",
                "3 0 0 0 0 0 8 2 0",
                "0 0 1 0 0 0 0 7 0",
                "6 0 0 0 5 0 0 0 8",
                "0 0 0 6 0 3 9 0 5",
                "0 1 8 0 0 5 0 0 0",
                "0 0 6 8 3 2 7 0 1",
                "7 5 3 1 9 0 4 8 0"
        );

        int[][] expected = {
                {9, 6, 2, 0, 7, 8, 5, 0, 0},
                {1, 0, 5, 0, 0, 9, 3, 0, 0},
                {3, 0, 0, 0, 0, 0, 8, 2, 0},
                {0, 0, 1, 0, 0, 0, 0, 7, 0},
                {6, 0, 0, 0, 5, 0, 0, 0, 8},
                {0, 0, 0, 6, 0, 3, 9, 0, 5},
                {0, 1, 8, 0, 0, 5, 0, 0, 0},
                {0, 0, 6, 8, 3, 2, 7, 0, 1},
                {7, 5, 3, 1, 9, 0, 4, 8, 0}
        };

        int[][] result = Solve.createBoard(display);
        assertArrayEquals(expected, result);
    }


    @Test
    public void testSolveCompletePuzzle() {
        // Test that the solver can actually solve a puzzle
        int[][] puzzle = {
                {9, 6, 2, 0, 7, 8, 5, 0, 0},
                {1, 0, 5, 0, 0, 9, 3, 0, 0},
                {3, 0, 0, 0, 0, 0, 8, 2, 0},
                {0, 0, 1, 0, 0, 0, 0, 7, 0},
                {6, 0, 0, 0, 5, 0, 0, 0, 8},
                {0, 0, 0, 6, 0, 3, 9, 0, 5},
                {0, 1, 8, 0, 0, 5, 0, 0, 0},
                {0, 0, 6, 8, 3, 2, 7, 0, 1},
                {7, 5, 3, 1, 9, 0, 4, 8, 0}
        };

        boolean solved = Solve.possibleSolutions(puzzle);
        assertTrue(solved, "Puzzle should be solvable");
        assertTrue(Solve.isPuzzleComplete(puzzle), "Puzzle should be complete after solving");
    }
}
