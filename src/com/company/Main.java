package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // First, we get the input
        // then we pass the input to the solver
        // then we print the output of the solver (given required algorithm)

        int[][] puzzle = new int[][] { {1,2,5},
                                       {3,4,0},
                                       {6,7,8} };

        PuzzleSolver puzzleSolver = new PuzzleSolver(puzzle);
        while(true) {
            int choice;
            System.out.println("1 for BFS");
            System.out.println("2 for DFS");
            System.out.println("3 for A* Manhattan");
            System.out.println("4 for A* Euclidean");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            if(choice == 0)
                break;
            else if (choice == 1)
                System.out.println("Cost of BFS to solve the puzzle = " + puzzleSolver.bfsSolve());
            else if (choice == 2)
                System.out.println("Cost of DFS to solve the puzzle = " + puzzleSolver.dfsSolve());
            else if (choice == 3)
                System.out.println("Cost of A* Manhattan to solve the puzzle = " + puzzleSolver.aStarManhattan());
            else if (choice == 4)
                System.out.println("Cost of A* Euclidean to solve the puzzle = " + puzzleSolver.aStarEuclidean());
            else
                System.out.println("Choose 1,2,3 or 4");
        }
        main(args);
    }

}
