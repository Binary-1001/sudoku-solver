# sudoku-solver
Sudoku Solver
📋 Project Structure
sudoku-solver/
├── src/
│   └── main/
│       ├── java/
│       │   └── za/
│       │       └── co/
│       │           └── nhlakanipho/
│       │               ├── Solve.java       # Main solver
│       │               └── TestSolve.java   # JUnit tests
│       └── resources/
│           └── path/
│               └── to/
│                   ├── puzzle.txt        # Input puzzle
│                   └── solved_puzzle.txt # Output (created after running)
🎯 How the Algorithm Works
1. Backtracking Algorithm (The Core)
The solver uses recursive backtracking to find the solution:

Find an empty cell (marked with 0)
Try numbers 1-9 in that cell
For each number:

Check if it's valid (row, column, 3x3 box rules)
If valid, place it and recurse to solve the rest
If recursion succeeds → Solution found! ✅
If recursion fails → Backtrack (remove the number) and try the next one


If no number works → Return false (dead end)
If all cells filled → Return true (puzzle solved!)
