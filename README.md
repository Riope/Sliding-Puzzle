# Sliding-Puzzle
### Solves the n*n sliding puzzle game for minimum number of moves using different algorithms.

This project solves the sliding puzzle through breadth-first search (bfs), iterative deepening depth-first search (iddfs), A-star (A*) algorithm and iterative deepening A-star (IDA*) algorithm. The various algorithms can be seen as improvements over the previous ones as listed above, being able to solve more complex problems than the preceding one.

## Code
<p align="center">
  <img src="https://github.com/Riope/Sliding-Puzzle/blob/main/visualization.jpg?raw=true">
</p>

* Each of the four algorithm files can be independently run. They would check for the solvability of the state and find the minimum number of moves to solve the given state along with the time taken to run that algorithm. They also print the states through each move taken to reach the goal state.
* All these functions mark the visited states using unique code for each state. These codes are then stored in a set to quickly find if the state has already been checked or not. This saves time (from the conventional way of storing states in a set) on the cases where an already considered state is being checked for its presence in the set.

### stage.java
The objects of this class would represent the states or stages in the puzzle.
  * This class contains the relevant data for the state.
  * It contains general functions related to the states.

### Solvability.java
This file contains the function that checks the solvability of the given state and its auxillary functions which are used by it.

### SlidingPuzzle.java
This file binds and uses all the functions together to show the comparison between the time taken by each of the algorithm. It also prints the minimum number of moves and each state through the moves taken by the IDA* algorithm to reach the goal state. It prints the moves taken by the other algorithm in a new file created in the same folder as the rest of the files.

### bfs.java
This file contains the code to solve the puzzle through breadth-first search. It is the first approach that one thinks of while solving such a problem of minimum number of steps. This has a problem of storage as it can run out of memory space in states with higher minimum number of moves to solve.

### Iddfs.java
This file contains the code to solve the puzzle through iterative deepening depth-first search. It takes greater time in comparison to bfs but does not need to store the states while traversing. It is basically dfs with increasing number of levels to look upto. This takes the same order of time as bfs and the same order of space as dfs. This is thus able to solve the states which are out of range for bfs because of memory requirement.

### Astar.java
This file contains the code to solve the puzzle through A-star algorithm. This algorithm uses a heuristic function to figure out priority in between states. This improves on the time to solve the problem but takes the same order of space as bfs. It is considered as a basic method of Artificial Intelligence as it estimates the final answer through heuristic.

### IDAstar.java
This file contains the code to solve the puzzle through iterative deepening A-star algorithm. This algorithm uses both iddfs and A* (through using heuristic). Iddfs takes lesser memory but greater time, so to reduce the time taken, the heuristic is used to determine the levels to which the dfs would run in each iteration of the iddfs. This reduces the time taken in A* and is the best method among the four used in this project.
