package com.company;

import java.util.*;

public class PuzzleSolver {
    private Node root;
    long startTime = System.nanoTime();
    public PuzzleSolver(int[][] puzzle) {
        this.root = new Node(puzzle);
    }

    public int bfsSolve() {
        int ExpandedNodes = 0;
        Queue<Node> frontier = new LinkedList<>();
        Set<int[][]> visited = new HashSet<>();
        List<Node> solutionPath = new ArrayList<>();

        frontier.add(root);
        while (!frontier.isEmpty()) {
            // poll node in the front of the queue
            // then, add to visited
            // then check if node is the goal
            // if not, iterate over all possible children, add them to the frontier, if not already visited
            Node currentNode = frontier.poll();
            visited.add(currentNode.getPuzzle());

            ExpandedNodes++;
            if (currentNode.isGoal()) {
                // trace path to goal and return
                System.out.println("Goal is found!");
                while (currentNode.getParent() != null) {
                    solutionPath.add(currentNode);
                    currentNode.print();
                    System.out.println("----------------");
                    currentNode = currentNode.getParent();
                }
                root.print();
                System.out.println("Expanded Nodes: " + ExpandedNodes);
                System.out.println("Search Depth: " + solutionPath.size());
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Running time is: " + totalTime/Math.pow(10, 9) + " Seconds");
                return solutionPath.size();
            }

           // System.out.println("Expanded Nodes");
            //currentNode.print();
           // System.out.println("=================");
            //System.out.println(currentNode.getChildren().size());
            for (Node child : currentNode.getChildren()) {
                if (!Contains(visited, child)) {
                    //System.out.println("Adding to frontier");
                    frontier.add(child);
                }
            }
        }
        System.out.println("Goal wasn't reached and we're returning false");
        return 0;
    }

    public int dfsSolve() {
        int ExpandedNodes = 0;
        Stack<Node> frontier = new Stack<>();
        Set<int[][]> visited = new HashSet<>();
        List<Node> solutionPath = new ArrayList<>();

        frontier.add(root);
        while (!frontier.isEmpty()) {

            // pop node at the top of the stack
            // then, add to visited
            // then check if node is the goal
            // if not, iterate over all possible children, add them to the frontier, if not already visited
            Node currentNode = frontier.pop();
            visited.add(currentNode.getPuzzle());

            ExpandedNodes++;
            if (currentNode.isGoal()) {

                // trace path to goal and return
                System.out.println("Goal is found!");
                while (currentNode.getParent() != null) {
                    solutionPath.add(currentNode);
                    currentNode.print();
                    System.out.println("----------------");
                    currentNode = currentNode.getParent();
                }
                root.print();
                System.out.println("Expanded Nodes: " + ExpandedNodes);
                System.out.println("Search Depth: " + solutionPath.size());
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Running time is: " + totalTime/Math.pow(10, 9) + " Seconds");
                return solutionPath.size();
            }

            // System.out.println("Expanded Nodes");
            //currentNode.print();
            // System.out.println("=================");
            //System.out.println(currentNode.getChildren().size());
            for (Node child : currentNode.getChildren()) {
                if (!Contains(visited, child)) {
                    //System.out.println("Adding to frontier");
                    frontier.add(child);
                }
            }
        }
        System.out.println("Goal wasn't reached and we're returning false");
        return 0;
    }
    public int aStarEuclidean() {
        //add the root node to the priority queue as the priority is the least cost of all the nodes in it
        //then add the Euclidean cost ((h(n)+g(n))**2 to the root
        //then pop the least cost and add its children(with their Euclidean cost) to the priority queue

        PriorityQueue<Node> frontier = new PriorityQueue<>(adjacencyComparator);
        int ExpandedNodes = 0;
        Set<int[][]> visited = new HashSet<>();
        List<Node> solutionPath = new ArrayList<>();
        root.setaStarCost(Euclidean(root));
        root.setaStarCost(Euclidean(root));
        frontier.add(root);

        ExpandedNodes++;
        while (!frontier.isEmpty()) {
            Node currentNode = getmin(frontier);
            frontier.remove(currentNode);

            visited.add(currentNode.getPuzzle());

            if(currentNode.isGoal())
            {
                while(currentNode.getParent() != null)
                {
                    solutionPath.add(currentNode);
                    currentNode.print();
                    System.out.println("-----------");
                    currentNode = currentNode.getParent();
                }
                System.out.println("Expanded Nodes: " + ExpandedNodes);
                System.out.println("Search Depth:  " + solutionPath.size());
                System.out.println("Euclidean Cost: " + Euclidean(currentNode));
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Running time is: " + totalTime/Math.pow(10, 9) + " Seconds");
                return solutionPath.size();
            }

            ExpandedNodes++;
            for(Node child : currentNode.getChildren())
            {
                if (!Contains(visited, child) && !ContainsPQ(frontier, child)) {
                    child.setaStarCost(Manhattan(child) + distanceFromRoot(child.getParent()) + 1);
                    frontier.add(child);
                } else {
                    decreaseKey(frontier, child);

                }
            }
        }
        return 0;
    }

    public Node getmin(PriorityQueue<Node> queue)
    {
        Node min = new Node();
        min.setaStarCost(1000000);
        for(Node node : queue)
        {
            if(node.getaStarCost() < min.getaStarCost())
                min = node;
        }
        return min;
    }
    public int aStarManhattan()
    {
        //add the root node to the priority queue as the priority is the least cost of all the nodes in it
        //then add the Manhattan cost ((h(n)+g(n)) to the root
        //then pop the least cost and add its children(with their Manhattan cost) to the priority queue
        PriorityQueue<Node> frontier = new PriorityQueue<>(adjacencyComparator);
        int ExpandedNodes = 0;
        Set<int[][]> visited = new HashSet<>();
        List<Node> solutionPath = new ArrayList<>();
        root.setaStarCost(Manhattan(root));
        frontier.add(root);
        //root.setCost(Manhattan(root));

        ExpandedNodes++;
        while (!frontier.isEmpty()) {
            Node currentNode = getmin(frontier);
            frontier.remove(currentNode);

            visited.add(currentNode.getPuzzle());
            System.out.println(currentNode.getaStarCost());


            if(currentNode.isGoal())
            {
                while(currentNode.getParent() != null)
                {
                    solutionPath.add(currentNode);
                    currentNode.print();
                    System.out.println("-----------");
                    currentNode = currentNode.getParent();
                }
                System.out.println("Expanded Nodes: " + ExpandedNodes);
                System.out.println("Search Depth:  " + solutionPath.size());
                System.out.println("Manhattan Cost: " + Manhattan(currentNode));
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Running time is: " + totalTime/Math.pow(10, 9) + "Seconds");
                return solutionPath.size();
            }

            ExpandedNodes++;
            for(Node child : currentNode.getChildren()) {

                if (!Contains(visited, child) && !ContainsPQ(frontier, child)) {
                    child.setaStarCost(Manhattan(child) + distanceFromRoot(child.getParent()) + 1);
                    frontier.add(child);
                } else {
                    decreaseKey(frontier, child);

                }

            }
        }
        return 0;
    }

    public boolean compareArrays(int[][] a, int[][] b)
    {
        for(int i =0 ; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }
    public boolean Contains(Set<int[][]> list, Node child)
    {
        for(int[][] node : list)
        {
            if(compareArrays(node ,child.getPuzzle()))
                return true;
        }
        /*if(list.contains(child.getPuzzle()))
            return true;
            */

        return false;
    }

    public int Manhattan(Node state)
    {
        int mistiles = 0;
        int[][] goal = {{0,1,2},{3,4,5},{6,7,8}};
        for(int i = 0; i < 3; i++) {
            for(int j = 0;j < 3; j++) {
                for(int l = 0 ;l < 3; l++) {
                    for(int g = 0; g < 3; g++) {
                        if(goal[l][g] == state.getPuzzle()[i][j])
                        {
                            mistiles+=(Math.abs(l-i) + Math.abs(g-j));
                            break;
                        }
                    }
                }
            }
        }
        //System.out.println(mistiles);
        return mistiles;
    }
    /*public int Manhattan(Node state)
    {
        int[][] goal = {{0,1,2},{3,4,5},{6,7,8}};
        int cost = 0;
        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if(state.getPuzzle()[i][j] != goal[i][j])
                    cost++;
            }
        }
        return cost;
    }*/
    public int Euclidean(Node state)
    {
        int mistiles = 0;
        int[][] goal = {{0,1,2},{3,4,5},{6,7,8}};
        for(int i = 0; i < 3; i++) {
            for(int j = 0;j < 3; j++) {
                for(int l = 0 ;l < 3; l++) {
                    for(int g = 0; g < 3; g++) {
                        if(goal[l][g] == state.getPuzzle()[i][j])
                            mistiles+=((Math.abs(l-i) + Math.abs(g-j))*(Math.abs(l-i) + Math.abs(g-j)));
                    }
                }
            }
        }
        return (int) Math.floor(Math.sqrt(mistiles));
    }

    public boolean decreaseKey(PriorityQueue<Node> pq , Node state)
    {
        for(Node node : pq) {
            if(compareArrays(node.getPuzzle(),state.getPuzzle()) && node.getaStarCost() > state.getaStarCost())
            {
                node.setaStarCost(state.getaStarCost());
            }

        }
        return false;
    }
    public boolean ContainsPQ(PriorityQueue<Node> pq , Node state)
    {
        for(Node node : pq) {
            if(compareArrays(node.getPuzzle(),state.getPuzzle()))
                return true;
        }
        return false;
    }
    Comparator<Node> adjacencyComparator = new Comparator<Node>() {

        @Override
        public int compare(Node left, Node right) {
            if(left.getaStarCost() < right.getaStarCost())
                return left.getaStarCost();
            return right.getaStarCost();
        }
    };

    public int distanceFromRoot(Node node)
    {
        int dist = 0;
        while(node != root)
        {
            dist++;
            node = node.getParent();
        }
        return dist;
    }


}
