package sudoku;

import java.util.Random;

public class Sudoku {
    private static final int Grid_Size = 9;
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[][] board = new int[Grid_Size][Grid_Size];

        if (fillBoard(board)) {
            System.out.println("Initial solution:");
            printBoard(board);
            makePuzzle(board);
            System.out.println("\nPuzzle to solve:");
            printBoard(board);
            if(solveBoard(board)){
                System.out.println("Solved generated map");
                printBoard(board);
            }
        } else {
            System.out.println("Failed to generate a solution.");
        }

    }

    private static boolean fillBoard(int[][] board) {
        for (int row = 0; row < Grid_Size; row++) {
            for (int column = 0; column < Grid_Size; column++) {
                if (board[row][column] == 0) {
                    for (int number = 1; number <= Grid_Size; number++) {
                        int numToTry = (number + random.nextInt(Grid_Size)) % Grid_Size+ 1; // Add randomness
                        if (isValidPlacement(board, numToTry, row, column)) {
                            board[row][column] = numToTry;
                            if (fillBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false; // backtrack
                }
            }
        }
        return true; // puzzle filled
    }
    private static void printBoard(int[][] board) {
        for ( int row =0 ; row < Grid_Size ; row  ++){
            if ( row % 3 ==0 & row !=0 ){
                System.out.println("---------------");
            }
            for (int column =0 ; column <Grid_Size ; column ++){
                if (column % 3 ==0 & column!=0){
                    System.out.print(" | ");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int [][]board,int number , int row){
        for (int i=0 ; i <Grid_Size; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board,int number , int column){
        for (int i=0; i < Grid_Size ;i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;

    }
    // so we need to get the row we could use the mod and get reminder
    // like row (that's passed ) -> % 3
    // more real case could be row - row % 3 (e.g) row 1
    // 1- 1 %3
    // =0
    // we got at row 0
    // that's what we need coordinate of top left corner inside the box


    private static boolean isNumberInBox (int [][]board , int number , int row , int column){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i= localBoxRow;i<localBoxRow+3;i++){
            for (int j =localBoxColumn ; j<localBoxColumn +3 ;j++){
                if(board[i][j]==number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int [][]board,int number , int row , int column){
        return !isNumberInRow(board,number,row)&&
                !isNumberInColumn(board,number,column)&&
                !isNumberInBox(board,number,row,column) ;
    }

    private static void makePuzzle(int[][] board) {
        int steps = Grid_Size * 3; // Adjust this for difficulty
        while (steps > 0) {
            int row = random.nextInt(Grid_Size);
            int col = random.nextInt(Grid_Size);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                steps--;
            }
        }
    }

    private static boolean solveBoard(int[][]board){
        for (int row =0 ; row <Grid_Size ;row++){ // all rows
            for (int column =0 ; column <Grid_Size ;column++){ // all columns in row
                if(board[row][column] ==0){ // taking 0 as a blank to tell computer
                    for (int numberToTry =1 ; numberToTry<=Grid_Size ; numberToTry ++){ // check number 1 to 9
                        // already have isValidPlacement method
                        if(isValidPlacement(board,numberToTry,row,column)){ // if it's a valid placement we place
                            // the number there

                            board[row][column]=numberToTry;

                            // now recursion  of this algorithm

                            // method would start again and check for next blank spots means 0 to place to valid number there

                            if(solveBoard(board)){
                                return true;
                            }
                            else {
                                board[row][column]=0; // set back to zero if it  could not solve
                                // even if a number is valid, but we could get a case board could not be solved
                                // if recursion does not work we set it back to 0;

                            }
                        }
                    }
                    return false; // return if it could not place any valid number after checking all numbers
                }
            }
        }
        return true; // solved finally after checking and doing all steps!
    }


}
