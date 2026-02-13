package za.co.nhlakanipho;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Solve {
    public static void main(String[] args){
        if (args.length != 3){
            System.out.println("ERROR: Usage: java Solve <input_file> <flag> <output_file>");
            return;
        }

        String filePath = args[0];
        String outputPath = args[2];

        List<String> display = openFile(filePath);
        if (display == null){
            System.out.println("ERROR 404 : FILE NOT FOUND");
            return;
        }

        int[][] board = createBoard(display);

        if (!isPuzzleComplete(board)) {
            System.out.println("Solving puzzle........");

            if (possibleSolutions(board)){
                System.out.println("Puzzle solved. View solved puzzle at " + outputPath);
                displayBoard(board,outputPath);
            }else{
                System.out.println("Puzzle unsolvable :(");
            }
        }else{
            System.out.println("Puzzle already solved");
        }
    }
    //1.openfile
    public static List<String> openFile(String filePath){
        try{
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            return null;
        }
    }
    //2.createBoard
    public static int[][] createBoard(List<String> display){
        int[][] board = new int[9][9];
        for (int x = 0 ; x < display.size(); x++){
            String[] nums = display.get(x).trim().split("\\s+");
            for (int y = 0 ; y < nums.length; y++){
                board[x][y] = Integer.parseInt(nums[y]);
            }

        }
        return board;
    }
    //3.isPuzzleComplete
    public static boolean isPuzzleComplete(int[][] board){
        for (int[] row : board){
            for (int cell : row){
                if (cell == 0){
                    return false;
                }
            }
        }
        return true;
    }
    //4.validPlaceNumber
    public static boolean validPlaceNumber(int[][] board, int row , int col , int placedNum){
        for(int x = 0 ; x < 9 ; x++){
            if (board[row][x] == placedNum){
                return false;
            }
        }

        for (int y = 0 ; y < 9 ; y ++){
            if (board[y][col] == placedNum){
                return false;
            }
        }

        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int x = 0; x < 3 ; x++){
            for (int y = 0 ; y < 3; y++){
                if (board[boxRow + x][boxCol + y] == placedNum){
                    return false;
                }
            }
        }

        return true;
    }
    //5.possibleSolution
    public static boolean possibleSolutions(int[][] board){
        for (int row = 0 ; row < 9 ; row++){
            for (int col = 0; col < 9; col++){
                if (board[row][col] == 0){
                    for (int placedNum = 1; placedNum <= 9 ; placedNum++){
                        if (validPlaceNumber(board,row,col,placedNum)){
                            board[row][col] = placedNum;

                            if (possibleSolutions(board)){
                                return true;
                            }
                            board[row][col] = 0;
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }
    //6.displayBoard
    public static void displayBoard(int[][] board,String outputPath){
        try{
            List<String> lines = Arrays.stream(board)
                    .map(row -> Arrays.stream(row)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" ")))
                    .toList();

            Files.write(Paths.get(outputPath), lines);
        }
        catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
