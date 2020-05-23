package sudokustuff;

import java.util.ArrayList;

public class SudokuStuff {

    public static int[][] board = new int[9][9]; //array for the board
    public static boolean boardFilled = false; //boolean to check if the array is full

    public static void main(String[] args) {
        resetBoard(); //calls a method to fill the board with zeroes 
        fillBoard(0, 0); //calls the first fillBoard method at the top left corner of the board
        findGaps(); // Calls the method to find the gaps after the initial board was made
        randomRemoval();
        printBoard(); //prints out the board 

    } //Main function to start everything

    public static boolean fillBoard(int rowNum, int columnNum) {
        int number; //the number that will be random
        ArrayList<Integer> numsTried = new ArrayList<Integer>(); //an arraylist of numbers tried for a specific slot
        number = (int) (Math.random() * ((9 - 1) + 1) + 1); //a random number is generated from 1-9
        while (boardFilled == false) { //while the board still isn't full
            if (checkRows(number, columnNum) && checkColumns(number, rowNum) && check3x3(number, rowNum, columnNum)) {
                //It will check if the number generated can properly fit the slot
                //If it can, it will put it into that board slot 
                board[rowNum][columnNum] = number;
                if (checkIfFull()) {// checks if the board is full
                    boardFilled = true;
                    break;
                }
                //Calls the method again to fill the next board
                if (columnNum != 8) { //if it's not at the end of the board
                    fillBoard(rowNum, columnNum + 1); //call the function for the next column over
                } else { //if it is at the end of the board 
                    fillBoard(rowNum + 1, 0); //call the function for the first column at the next row
                }
            }
            if (!numsTried.contains(number)) { //if the number isn't in the called method, numsTried array list then it adds it to it
                //Then it adds it to it
                numsTried.add(number);
            }
            if (numsTried.size() == 9) { //if the size of the arraylist is 9 meaning that every number has been tried
                board[rowNum][columnNum] = 0; //it sets the slot in the board back to zero
                numsTried.clear(); //clears the array list
                return (false); //and returns back to the previous function call
                //this way I'm able to backtrack through the board and find numbers that will work together
            }
            number = (int) (Math.random() * ((9 - 1) + 1) + 1); //generates a new random number at the end of this while loop
        }
        return (true); //returns true when this is done
    } //Recursive method that is used to fill the board

    public static boolean checkRows(int number, int column) {
        for (int rowScanner = 0; rowScanner < board.length; rowScanner++) { //for every row at the column
            if (number == board[rowScanner][column]) { //if the number is the same as any other
                return (false); //return false
            }
        }
        return (true); //otherwise this will return true
    } //Method to check the rows at the indexed column

    public static boolean checkColumns(int number, int row) {
        for (int columnScanner = 0; columnScanner < board[row].length; columnScanner++) { //for every column at the row
            if (number == board[row][columnScanner]) { //if the number is the same as any other in the row
                return (false); //return false
            }
        }
        return (true); //otherwise this will return true
    } //Method to check the columns at the indexed row 

    public static boolean check3x3(int number, int row, int column) {
        if (row <= 2) { //figures out the row and column that the point it is in
            if (column <= 2) { //A1
                for (int rowNum = 0; rowNum < 3; rowNum++) { //loops throught the 3x3 block to check if the number exists there
                    for (int columnNum = 0; columnNum < 3; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false); //if so, return false
                        }
                    }
                }
            } else if (column >= 3 && column <= 5) { //A2
                for (int rowNum = 0; rowNum < 3; rowNum++) {
                    for (int columnNum = 3; columnNum < 6; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            } else if (column >= 6 && column <= 8) { //A3
                for (int rowNum = 0; rowNum < 3; rowNum++) {
                    for (int columnNum = 6; columnNum < 9; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            }
        } else if (row >= 3 && row <= 5) {
            if (column <= 2) { //B1
                for (int rowNum = 3; rowNum < 6; rowNum++) {
                    for (int columnNum = 0; columnNum < 3; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            } else if (column >= 3 && column <= 5) { //B2
                for (int rowNum = 3; rowNum < 6; rowNum++) {
                    for (int columnNum = 3; columnNum < 6; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            } else if (column >= 6 && column <= 8) { //B3
                for (int rowNum = 3; rowNum < 6; rowNum++) {
                    for (int columnNum = 6; columnNum < 9; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            }
        } else if (row >= 6 && row <= 8) {
            if (column <= 2) { //C1
                for (int rowNum = 6; rowNum < 9; rowNum++) {
                    for (int columnNum = 0; columnNum < 3; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            } else if (column >= 3 && column <= 5) { //C2
                for (int rowNum = 6; rowNum < 9; rowNum++) {
                    for (int columnNum = 3; columnNum < 6; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            } else if (column >= 6 && column <= 8) { //C3
                for (int rowNum = 6; rowNum < 9; rowNum++) {
                    for (int columnNum = 6; columnNum < 9; columnNum++) {
                        if (board[rowNum][columnNum] == number) {
                            return (false);
                        }
                    }
                }
            }
        }
        return (true); //otherwise this will return true
    } //Method to check the 3x3 box at the index row and column

    public static void resetBoard() {
        for (int rowNum = 0; rowNum < board.length; rowNum++) { //for every index in the board
            for (int columnNum = 0; columnNum < board[rowNum].length; columnNum++) {
                board[rowNum][columnNum] = 0; //make it zero
            }
        }
    } //Method to reset the board and fill it with zeores

    public static boolean checkIfFull() {
        for (int rowNum = 0; rowNum < board.length; rowNum++) { //for every index in the board
            for (int columnNum = 0; columnNum < board[rowNum].length; columnNum++) {
                if (board[rowNum][columnNum] == 0) { //if there exists a zero that means the board isn't full
                    return (false); //return false
                }
            }
        }
        return (true); //other return true
    } //Method to check if the board is full

    public static void findGaps() {
        for (int rowNum = 0; rowNum < board.length; rowNum++) { //for every index in the board
            for (int columnNum = 0; columnNum < board[rowNum].length; columnNum++) {
                if (board[rowNum][columnNum] == 0) {  //if there is a zero
                    fillGap(rowNum, columnNum); //calls the fillgap method passing through the row and column number
                }
            }
        }
    } //Method to find the gaps that the above functions may have left

    public static void fillGap(int row, int column) {
        int r = row; //gets the row and column that was passed through
        int c = column;
        ArrayList<Integer> numsUsed = new ArrayList<Integer>(); //creates an array list of ints used
        for (int i = 1; i < 10; i++) {
            numsUsed.add(i); //adds 1-9
        }
        for (int columnNum = 0; columnNum < board[r].length; columnNum++) { //check the row at that index
            if (numsUsed.contains(board[r][columnNum])) {  //if a number exists in the array list 
                numsUsed.remove(Integer.valueOf(board[r][columnNum])); //it gets removed
            }
        }
        for (int rowNum = 0; rowNum < board.length; rowNum++) { //check the column at that index
            if (numsUsed.contains(board[rowNum][c])) { //if a number exists in the array list
                numsUsed.remove(Integer.valueOf(board[rowNum][c])); //it gets removed
            }
        }
        board[row][column] = numsUsed.get(0); //adds the last remaining number to that slot
    } //Method to fill gaps 
    
    public static void randomRemoval(){
        int numsToRemove = (int)(Math.random() * ((40 - 30) + 1) + 30);
        int numsRemoved = 0;
        int row, column ;
        while (numsRemoved != numsToRemove){
            row = (int)(Math.random() * ((8) + 1));
            column = (int)(Math.random() * ((8) + 1));
            board[row][column] = 0;
            numsRemoved++;
        }
    }

    public static void printBoard() {
        for (int rowNum = 0; rowNum < board.length; rowNum++) { //for every index
            for (int columnNum = 0; columnNum < board[rowNum].length; columnNum++) {
                System.out.print(board[rowNum][columnNum]); //print it
            }
            System.out.println(""); //print a new line when finished printing the row
        }
    } //Method to print the board

}
