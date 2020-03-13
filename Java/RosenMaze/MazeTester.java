import java.util.*;
import java.io.*;

/**
 * MazeTester uses recursion to determine if a maze can be traversed.
 *
 * @author (Joseph Rosen) Lewis and Chase
 * @version 4.1
 */
public class MazeTester
{
    /**
     * Creates a new maze, prints its original form, attempts to
     * solve it, and prints out its final form.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        String[] tokens;
        String userInput = "";
        Integer startX, startY, endX, endY;
        
        System.out.print("Enter the name of the file containing the maze: ");
        String filename = scan.nextLine();
        
        System.out.print("Enter the starting position (two integers seperated by a comma): ");
        userInput = scan.nextLine();
        userInput += ","; // used to separate values in the tokens array
        
        System.out.print("Enter the end position (two integers seperated by a comma): ");
        userInput += scan.nextLine();
        scan.close();
        tokens = userInput.split(",");
        
        startX = Integer.valueOf(tokens[0]);
		startY = Integer.valueOf(tokens[1]);
		endX = Integer.valueOf(tokens[2]);
		endY = Integer.valueOf(tokens[3]);
        
        
        System.out.println("Start position is: x: " + startX + " y: " + startY );
        System.out.println("End position is: x: " + endX + " y: " + endY );
        
        Maze labyrinth = new Maze(filename);
      
        System.out.println(labyrinth);
        
        MazeSolver solver = new MazeSolver(labyrinth);
        
        if(solver.findPath(startX, startY, endX, endY)) {
            System.out.println("The maze was successfully traversed!");
            System.out.println(labyrinth);
            System.out.println("Solution path:\n");
            System.out.println(solver.getPath());
        }
            else {
            	System.out.println("There is no possible path.");
            	 System.out.println(labyrinth);
            }
    }
}