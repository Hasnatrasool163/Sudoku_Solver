package sudoku;

public class Sudoku_Solver {
    private static  final int Grid_Size =9;

    public static  void main(String[]args){

        // 9 * 9 grid we have to validate and apply algorithm so that
        // computer could solve the board
        // we need to check individual row validation
        // then column validation
        // then we could check certain box validation and combine them .

        int [][]board =
                {
                        {7,0,2,   0,5,0,   6,0,0},
                        {0,0,0,   0,0,3,   0,0,0},
                        {1,0,0,   0,0,9,   5,0,0},

                        {8,0,0,   0,0,0,   0,9,0},
                        {0,4,3,   0,0,0,   7,5,0},
                        {0,9,0,   0,0,0,   0,0,8},

                        {0,0,9,   7,0,0,   0,0,5},
                        {0,0,0,   2,0,0,   0,0,0},
                        {0,0,7,   0,4,0,   2,0,3}
                };

        System.out.println("Original Board");
        printBoard(board);

        if (solveBoard(board)){ // if board is valid it would do quick
            System.out.println("Solved successfully!");

        }  // some initial board could cause issue sometimes like impossible to solve

        else {
            System.out.println("Unsolvable Board :(");
        }
        printBoard(board);
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

    // this method has ability to backtrack and check previous number it placed but could not solve
    // full board later on !
    // it can set number back to 0 if failed to solve board by recursion

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

