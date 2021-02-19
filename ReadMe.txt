NAME: Mahmooda Ali
UIN: 672890252
NETID: mali69

COMPILER: 
    Java 1.8

ABOUT: 
    15 Puzzle Game that uses BFS to solve the puzzle

USER INPUT: 
    An unordered string of number from [0, 15] separated by a space
    Example: 1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15 

OUTPUT:
    - Moves made to get to final state 
        - R --> Right
        - L --> Left
        - U --> Up
        - D --> Down
    - Number of Nodes expanded
    - Time Taken for complete program to run
    - Memory Used by program

    Example:
        Moves made: RDLDDRR
        Number of Nodes expanded: 479
        Time taken: 1.544 seconds
        Memory Used: 2100.144 KB

TO RUN IN TERMINAL:
    - place all files in same directory
    - open terminal to directory files are placed in
    - compile code using the command: 
        >> javac PlayPuzzleBFS.java
    - run code with command:
        >> java PlayPuzzleBFS

TEST CASES (Initial Configuration | Moves): 
    1 0 3 4 5 2 6 8 9 10 7 11 13 14 15 12 | DRDRD    
    1 2 3 4 5 6 8 0 9 11 7 12 13 10 14 15 | LDLDRR
    1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15 | RDLDDRR
    1 2 0 4 6 7 3 8 5 9 10 12 13 14 11 15 | DLLDRRDR 
    1 3 4 8 5 2 0 6 9 10 7 11 13 14 15 12 | RULLDRDRD

