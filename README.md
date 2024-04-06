# Sudoku_Solver
This repository contains cli and gui app to check backtracking and sudoku map generation and solution.

# Sudoku Solver GUI

A simple, interactive Sudoku puzzle solver built with Java Swing. This application allows users to visually interact with the Sudoku board, offering features to reset the board to a new puzzle and solve the displayed puzzle automatically.

## Features

- **Interactive Sudoku Board**: A 9x9 grid where users can view the puzzle.
- **Solve Puzzle**: Automatically solves the displayed Sudoku puzzle.
- **Reset Puzzle**: Resets the board to a new, randomly generated Sudoku puzzle.
- **Visual Updates**: Real-time visual feedback during the solving process.

## Getting Started

### Prerequisites

Ensure you have Java installed on your system. You can check your Java version by running:

```shell
java -version

If Java is not installed, follow the official Java Installation Guide.

Running the Application
Clone the repository or download the source code.
Navigate to the directory containing the SudokuGUI.java file.
Compile the Java program:
shell
Copy code
javac SudokuGUI.java
Run the compiled Java program:
shell
Copy code
java SudokuGUI
The Sudoku GUI should now open, displaying a randomly generated puzzle ready for interaction.

How It Works
The Sudoku Solver utilizes a backtracking algorithm to fill the Sudoku board with valid numbers.
The Puzzle Generator randomly fills the board with numbers and then removes a certain number of elements to create a solvable Sudoku puzzle.
Users can reset the board at any time to get a new puzzle or solve the current puzzle on the board.


Screenshots

![Screenshot (799)](https://github.com/Hasnatrasool163/Sudoku_Solver/assets/153990457/c00359f7-db00-4e20-a834-1d80fa0df90b)


![Screenshot (800)](https://github.com/Hasnatrasool163/Sudoku_Solver/assets/153990457/4db0cbc4-9357-4b2e-a7ac-4a84c8ba2fc5)



Contributing
Contributions are welcome! Please feel free to submit pull requests, suggest features, or report bugs.

Acknowledgments
Thanks to the Java Swing toolkit for making GUI development accessible and straightforward.
Appreciation for the Sudoku puzzle community for continuous inspiration and challenges.
