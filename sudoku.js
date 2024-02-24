import java.util.Scanner;

public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = collectInput();
        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution found.");
        }
    }

    public static char[][] collectInput() {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[9][9];
        System.out.println("Enter the Sudoku puzzle (9x9 grid):");
        for (int i = 0; i < 9; i++) {
            String input = scanner.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        return board;
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = '.'; // Backtrack
                        }
                    }
                    return false; // No valid move found, backtrack
                }
            }
        }
        return true;
    }

    public static boolean isValidMove(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

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
}
