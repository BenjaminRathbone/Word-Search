//Programmer: Ben Rathbone
//CS 145
//Date: 7-24-23
//Assignment: Assignment 1 - Word Search
//Purpose: A program that allows the user to create and view a word search.
//         The user will be prompted for a list of words, and the word search will be created.
//         The user can then view their word search, view the solution,
//                                         or create a new word search.

import java.util.*;

public class WordSearchMain
{
   public static void main(String[] args)
   {
      Scanner console = new Scanner(System.in); //creates Scanner object
      
      programIntro();   //calls the programIntro method to explain the program
      //calls the create method to create a new word search
      WordSearch search = create(console);
      
      //user menu
      while (true)
      {
         menuText(); //calls the menuText method to explain commands
         
         //accepts user input
         char userInput;
         try 
         {
            userInput = console.nextLine().charAt(0);   //saves input as char "userInput"
            userInput = Character.toUpperCase(userInput);  //converts char to uppercase
         }
         catch (StringIndexOutOfBoundsException e) //if input is invalid
         {
            userInput = 'X';  //set input to X (invalid)
         }
         
         if (userInput == 'C')   //create
         {
            search = create(console);
         }//end of create
         
         else if (userInput == 'V') //view
         {
            search.printSearch();
         }//end of view
         
         else if (userInput == 'S') //solution
         {
            search.printSolution();
         }//end of solution
         
         else if (userInput == 'Q') //quit program
         {
            System.out.println("Have a nice day!");
            break;
         }//end of quit
         
         else  //invalid input
         {
            System.out.println("Invalid input.  Please try again.");
         }//end of invalid input
      }//end of user menu while loop
   }//end of main method
   
   public static WordSearch create(Scanner console)
   {
      ArrayList<String> words = new ArrayList<>(); //initializes ArrayList
      
      //prompts user for input
      System.out.println("Please enter a list of words.");
      System.out.println("Type \"end\" when you are finished.");
      
      while (true)
      {
         String userWord = console.nextLine();  //saves user input as string
         
         if (userWord.isEmpty()) //if input is empty
         {} //do nothing
         else if (userWord.toUpperCase().equals("END")
                  && words.size() == 0) //if string is "end", but no words have been entered
         {
            System.out.println("You need to enter some words first!");
         }
         else if (userWord.toUpperCase().equals("END"))  //if string is "end"
         {
            break;
         }
         else  //otherwise
         {
            words.add(userWord); //add it to the ArrayList
         }
      }
      
      WordSearch search = new WordSearch(words); //create a new WordSearch from the ArrayList
      System.out.println("Your word search has been created!");
      return search;
   }
   
   //explains the purpose of the program
   public static void programIntro()
   {
      System.out.println("_______________");
      System.out.println("| WORD SEARCH |");
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
      System.out.println("Welcome!");
      System.out.println("This program allows you to create");
      System.out.println("and view a word search puzzle.");
      System.out.println("Let's get started, shall we?\n");
   }//end of gameIntro method
   
   //displays menu text explaining the functions to the user
   public static void menuText()
   {
      System.out.println();
      System.out.println("What would you like to do?\n" +
                "  _______________________________________________\n" +
                " | \"C\" | create   | create a new word search     |\n" +
                " | \"V\" | view     | view your word search        |\n" +
                " | \"S\" | solution | show the solution            |\n" +
                " | \"Q\" | quit     | quit the program             |\n" +
                "  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }//end of menuText method

}//end of program