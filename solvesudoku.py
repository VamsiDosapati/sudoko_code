def solve_sudoku(board):
    def is_valid(row, col, num):
        for i in range(9):
            if board[row][i] == num or board[i][col] == num or board[3 * (row // 3) + i // 3][3 * (col // 3) + i % 3] == num:
                return False
        return True

    def find_empty_cell():
        for row in range(9):
            for col in range(9):
                if board[row][col] == ".":
                    return row, col
        return None, None

    def solve():
        r, c = find_empty_cell()
        if r is None:
            return True  # Puzzle solved
        for num in map(str, range(1, 10)):
            if is_valid(r, c, num):
                board[r][c] = num
                if solve():
                    return True
                board[r][c] = "."
        return False

    return solve()

def main():
    print("Enter the Sudoku puzzle (use '.' for empty cells):")
    sudoku_board = []
    for _ in range(9):
        row = input().strip()
        if len(row) != 9:
            print("Please enter exactly 9 characters per row.")
            return main()  # Restart input collection
        sudoku_board.append(list(row))
    if solve_sudoku(sudoku_board):
        print("Solved Sudoku:")
        for row in sudoku_board:
            print(" ".join(row))
    else:
        print("No solution found.")

if __name__ == "__main__":
    main()
