package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Node {
    private int[][] puzzle;
    private Node parent;
    private int aStarCost;


    public int[][] getPuzzle() {
        return puzzle;
    }


    public Node(int[][] puzzle) {
        this.puzzle = puzzle;
        this.parent = null;

    }
    public Node() {

    }
    public Node(int[][] puzzle, Node parent) {
        this.puzzle = puzzle;
        this.parent = parent;
    }

    private int[][] copyPuzzle(int zeroI, int zeroJ, int moveI, int moveJ) {
        int[][] newPuzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == zeroI && j == zeroJ) {
                    newPuzzle[i][j] = this.puzzle[moveI][moveJ];
                } else if (i == moveI && j == moveJ) {
                    newPuzzle[i][j] = this.puzzle[zeroI][zeroJ];
                } else {
                    newPuzzle[i][j] = this.puzzle[i][j];
                }
            }
        }
        return newPuzzle;
    }

    public List<Node> getChildren() {
        // returns a list of current node's children
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.puzzle[i][j] == 0) {
                    // found 0
                    if (i - 1 >= 0)
                    {
                        Node child = new Node(copyPuzzle(i, j, i - 1, j), this);
                        //child.setCost(child.getParent().getCost()+1);
                        children.add(child);
                    }
                    if (i + 1 < 3)
                    {
                        Node child = new Node(copyPuzzle(i, j, i + 1, j), this);
                        //child.setCost(child.getParent().getCost()+1);
                        children.add(child);
                    }
                    if (j - 1 >= 0)
                    {
                        Node child = new Node(copyPuzzle(i, j, i, j - 1), this);
                        //child.setCost(child.getParent().getCost()+1);
                        children.add(child);
                    }
                    if (j + 1 < 3)
                    {
                        Node child = new Node(copyPuzzle(i, j, i, j + 1), this);
                        //child.setCost(child.getParent().getCost()+1);
                        children.add(child);
                    }
                    return children;
                }
            }
        }
        return children;
    }

    public Node getParent() {
        return this.parent;
    }

    public boolean isGoal() {
        return Arrays.deepEquals(this.puzzle, new int[][]{{0,1,2},{3,4,5},{6,7,8}});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Arrays.deepEquals(this.puzzle, node.puzzle);
    }



    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.puzzle[i][j] + " ");
            }
    //        if(i != 2)
            System.out.println();
        }
    }

    public int getaStarCost() {
        return aStarCost;
    }

    public void setaStarCost(int aStarCost) {
        this.aStarCost = aStarCost;
    }
}
