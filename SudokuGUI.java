package sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SudokuGUI extends JFrame {
    private static final int GRID_SIZE = 9;
    private static final Random random = new Random();
    private final JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private final JButton solveButton = new JButton("Solve");
    private final JButton resetButton = new JButton("Reset");
    private final int[][] board = new int[GRID_SIZE][GRID_SIZE];
    private final JPanel buttonPanel = new JPanel();

    public SudokuGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                cells[i][j].setOpaque(true);
                gridPanel.add(cells[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        solveButton.addActionListener(e -> new Thread(() -> {
            if (solveBoard(board, true)) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Sudoku Solved!"));
            }
        }).start());

        resetButton.addActionListener(e -> resetBoard());

        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = 0;
                cells[i][j].setBackground(Color.WHITE);
            }
        }
        fillBoard(board);
        makePuzzle(board);
        updateBoard();
    }

    private boolean fillBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int number = 1; number <= GRID_SIZE; number++) {
                        int numToTry = (number + random.nextInt(GRID_SIZE)) % GRID_SIZE + 1;
                        if (isValidPlacement(board, numToTry, row, col)) {
                            board[row][col] = numToTry;
                            if (fillBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void makePuzzle(int[][] board) {
        int steps = GRID_SIZE * 3;
        while (steps > 0) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                steps--;
            }
        }
    }

    private boolean solveBoard(int[][] board, boolean firstCall) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int numToTry = 1; numToTry <= GRID_SIZE; numToTry++) {
                        if (isValidPlacement(board, numToTry, row, col)) {
                            board[row][col] = numToTry;
                            updateCell(row, col, numToTry);
                            if (solveBoard(board, false)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                                updateCell(row, col, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        if (firstCall) {
            SwingUtilities.invokeLater(this::updateBoard);
        }
        return true;
    }

    private void updateCell(int row, int col, int numToTry) {
        SwingUtilities.invokeLater(() -> {
            cells[row][col].setText(numToTry > 0 ? Integer.toString(numToTry) : "");
            if (numToTry > 0) {
                cells[row][col].setBackground(Color.CYAN);
            } else {
                cells[row][col].setBackground(Color.WHITE);
            }
            cells[row][col].repaint();
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void updateBoard() {
        SwingUtilities.invokeLater(() -> {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    cells[row][col].setText(board[row][col] > 0 ? Integer.toString(board[row][col]) : "");
                }
            }
        });
    }

    private boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI().setVisible(true));
    }
}
