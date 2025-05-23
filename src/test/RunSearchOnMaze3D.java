package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(10 ,10, 10);
        System.out.println(String.format("Start Position: %s", maze.getStartPosition())); // format "{row,column}"
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition())); // format "{row,column}"
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }



    private static <AState> void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        long before=System.currentTimeMillis();
        Solution solution = searcher.solve(domain); System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        long after=System.currentTimeMillis();
        long diff= after-before;
        System.out.println(diff);
//Printing Solution Path
        System.out.println("Solution path:");

        ArrayList<AState> solutionPath = (ArrayList<AState>) solution.getSolutionPath();
        if(solutionPath==null){
            System.out.println("solution is null");
            return;
        }
        int size=solutionPath.size();
        System.out.println(size);
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}
