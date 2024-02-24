import java.util.Scanner;

public class SS {

    public static void main(String[] args) {
        int[][] inputBoard = collectInput();
        if (solveSudoku(inputBoard)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(inputBoard);
        } else {
            System.out.println("\nNo solution found.");
        }
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                
            }
            System.out.println();
            
        }
    }

    public static boolean isSafeMove(int[][] board, int row, int col, int num) {
        // Check if the number is already in the row or column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 subgrid
        int subgridRow = 3 * (row / 3);
        int subgridCol = 3 * (col / 3);
        for (int i = subgridRow; i < subgridRow + 3; i++) {
            for (int j = subgridCol; j < subgridCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Find an empty cell
                if (board[row][col] == 0) {
                    // Try placing each number from 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isSafeMove(board, row, col, num)) {
                            // If the move is safe, place the number and recurse
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            // If the recursion doesn't lead to a solution, backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid move found, backtrack
                }
            }
        }
        return true;
    }

    public static int[][] collectInput() {
        int[][] board = new int[9][9];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Sudoku puzzle (9x9 grid):");
        for (int i = 0; i < 9; i++) {
            String input = scanner.nextLine();
            String[] row = input.split(" ");
            for (int j = 0; j < 9; j++) {
                if (row[j].trim().isEmpty() || row[j].equals(".")) {
                    board[i][j] = 0; // Replace empty cells or dots with 0 for empty cells
                } else {
                    board[i][j] = Integer.parseInt(row[j]);
                }
            }
        }
        return board;
    }
}
