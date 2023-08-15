// Programmer: Marcel Rodriguez
// Date: 8/05/2023
// Class: CS &145
// Lab 6: 20 Questions
// Purpose: Represents a Question Tree for the 20 Questions game. Handles game logic, user interactions,
// and file I/O for playing, saving, loading, and tracking game progress.

import java.io.*;
import java.util.*;

public class QuestionTree {
    private QuestionNode root; // Root node of question tree
    private UserInterface ui; // User interface for input/output
    private int gamesPlayed; // Number of games played
    private int gamesWon; // Number of games won
    
    //QuestionTree instance for 20 questions game
    public QuestionTree(UserInterface ui) {
        //Initialze instance variables
        this.ui = ui;
        this.root = new QuestionNode("computer");
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }
    //Calls play(QuestionNode current)
    public void play() {
        play(root);
    }
    //Recursively play 20 questions game
    private void play(QuestionNode current) {
    if (current.isAnswer()) {
        // Make a guess
        ui.print("Is your object " + current.data + "? ");
        if (ui.nextBoolean()) {
            ui.println("I win!");
            gamesWon++;
        } else {
            // Handle user's object
            ui.print("What is your object? ");
            String userAnswer = ui.nextLine();
            ui.print("Type a yes/no question to distinguish your item from " + current.data + ": ");
            String newQuestion = ui.nextLine();
            ui.print("And what is the answer for your object? ");
            boolean userAnswerYes = ui.nextBoolean();

            // Update tree based on user's input
            current.data = newQuestion;
            current.setAnswer(false);
            if (userAnswerYes) {
                current.yes = new QuestionNode(userAnswer);
                current.no = new QuestionNode(current.data);
            } else {
                current.yes = new QuestionNode(current.data);
                current.no = new QuestionNode(userAnswer);
            }
        }
    } else {
        // Traverse the tree
        ui.print(current.data + " ");
        String userInput = ui.nextLine();
        if (userInput.equalsIgnoreCase("y")) {
            play(current.yes);
        } else if (userInput.equalsIgnoreCase("n")) {
            play(current.no);
        } else {
            ui.println("Invalid input. Please enter 'y' or 'n'.");
            play(current);
        }
    }
    gamesPlayed++;
}

    //Save current state of the question tree to file
    public void save(PrintStream output) {
        save(output, root);
    }
    //recursively save the state of the question tree
    private void save(PrintStream output, QuestionNode current) {
        if (current.isAnswer()) {
            output.println("A:" + current.data);
        } else {
            output.println("Q:" + current.data);
            save(output, current.yes);
            save(output, current.no);
        }
    }
    //Loads new questiion tree state from file
    public void load(Scanner input) {
        root = load(input, null);
    }
    //Recursively loads a question tree state from input
    private QuestionNode load(Scanner input, QuestionNode parent) {
    String line = input.nextLine();
    
    // Check if the line has a minimum length of 2 characters
    if (line.length() < 2) {
        // Handle unexpected line format
        return null; // Skip this line and move to the next one
    }
    
    String type = line.substring(0, 2);
    String data = line.substring(2);

    QuestionNode current;
    if (type.equals("A:")) {
        current = new QuestionNode(data);
    } else {
        current = new QuestionNode(data);
        current.yes = load(input, current);
        current.no = load(input, current);
    }
    return current;
}
    // return total number of games played
    public int totalGames() {
        return gamesPlayed;
    }
    //return the games won by the computer
    public int gamesWon() {
        return gamesWon;
    }
}






    




