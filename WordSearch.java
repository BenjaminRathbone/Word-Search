//Programmer: Ben Rathbone
//CS 145
//Date: 7-24-23
//Assignment: Assignment 1 - Word Search
//Purpose: A class that represents a word search.  When provided an array of words,
//         it will generate a word search and a solution containing those words.

import java.util.*;

public class WordSearch
{
   private int minSize = 15;  //the minimum x/y value of the word search
   
   //---fields---
   private char[][] search;
   private char[][] solution;
   private ArrayList<String> words;
   private int dimensions;
   
   //---constructor---
   //takes an array of words, and constructs a word search containing those words
   public WordSearch(ArrayList<String> words)
   {
      this.words = words;
      
      //finds the length of the largest word
      int maxWord = 0;
      for (int i = 0; i < words.size(); i++) //for each word
      {
         if (words.get(i).length() > maxWord) //if its length is > the current max
         {
            maxWord = words.get(i).length();  //it becomes the max
         }
      }
      
      //initializes arrays
      dimensions = maxWord + 5;  //sets dimensions to 5 more than maxWord
      if (dimensions < minSize)  //if it's smaller than the minSize
      {
         dimensions = minSize;   //set it = to minSize
      }
      search = new char[dimensions][dimensions];   //initialize search
      solution = new char[dimensions][dimensions]; //initialize solution
      
      //places words
      for (int i = 0; i < words.size(); i++) //for every word
      {
         //randomly picks a number, 1-3
         Random rng = new Random();
         int orientation = rng.nextInt(3);
         
         //places word in the corresponding orientation
         switch (orientation)
         {
            case 0:  placeHorizontal(words.get(i).toUpperCase());
                     break;
            case 1:  placeVertical(words.get(i).toUpperCase());
                     break;
            case 2:  placeDiagonal(words.get(i).toUpperCase());
                     break;
         }
      }
      
      fill();  //fills the rest of the word search
   }//end of constructor
   
   //---public methods---
   
   //prints the word search to the console
   public void printSearch()
   {
      //print search
      for (int i = 0; i < search.length; i++)   //for every row
      {
         for (int j = 0; j < search[i].length; j++)   //for every char within that row
         {
            System.out.print(search[i][j] + " ");  //print the char and a space
         }
         System.out.println();   //move to next line
      }
      System.out.println();
      
      //print word list
      System.out.println("Find the following words:");
      for (int i = 0; i < words.size(); i++) //for each word
      {
         System.out.println("  -" + words.get(i));
      } 
   }//end of printSearch method
   
   //prints the solution to the console
   public void printSolution()
   {
      for (int i = 0; i < solution.length; i++) //for every row
      {
         for (int j = 0; j < solution[i].length; j++) //for every char within that row
         {
            System.out.print(solution[i][j] + " ");   //print the char and a space
         }
         System.out.println();   //move to next line
      }
   }//end of printSearch method
   
   //---private methods---
   
   //places a word in the search horizontally
   //accepts a String, which is the word to be placed
   private void placeHorizontal(String word)
   {
      int x = 0;
      int y = 0;
      boolean fail = true;
      int tries = 0;
      
      //checks random coordinates until a valid pair is found
      //tries 100 times before giving up
      while (fail && tries <= 100)
      {
         //resets fail
         fail = false;
         
         //sets random coordinates
         int[] coords = randomCoords();
         x = coords[0];
         y = coords[1];
         
         //checks to make sure coordinates are in-bounds
         if (word.length() + x > dimensions)
         {
            fail = true;
         }
         
         //checks for other chars in this word's path
         for (int i = 0; i < word.length(); i++)   //for each space
         {
            try
            {
               //if the space is empty
               if (search[y][x + i] == 0)
               {}
               //or if the space is already the correct letter
               else if (search[y][x + i] == word.charAt(i))
               {}
               else  //otherwise
               {
                  fail = true;
               }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
               fail = true;
            }
         }
         tries++; //increase tries count
      }//end of while
      
      //if a valid coordinate pair was found, place the word
      if (!fail)
      {
         for (int i = 0; i < word.length(); i++)   //for each space
         {
            search[y][x + i] = word.charAt(i); //place a letter in search
            solution[y][x + i] = word.charAt(i); //place a letter in solution
         }
      }
   }//end of placeHorizontal method
   
   //places a word in the search vertically
   //accepts a String, which is the word to be placed
   private void placeVertical(String word)
   {
      int x = 0;
      int y = 0;
      boolean fail = true;
      int tries = 0;
   
      //checks random coordinates until a valid pair is found
      //tries 100 times before giving up
      while (fail && tries <= 100)
      {
         //resets fail
         fail = false;
         
         //sets random coordinates
         int[] coords = randomCoords();
         x = coords[0];
         y = coords[1];
         
         //checks to make sure coordinates are in-bounds
         if (word.length() + y > dimensions)
         {
            fail = true;
         }
         
         //checks for other chars in this word's path
         for (int i = 0; i < word.length(); i++)   //for each space
         {
            try
            {
               //if the space is empty
               if (search[y + i][x] == 0)
               {}
               //or if the space is already the correct letter
               else if (search[y + i][x] == word.charAt(i))
               {}
               else  //otherwise
               {
                  fail = true;
               }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
               fail = true;
            }
         }
         tries++; //increase tries count
      }//end of while

   
      //if a valid coordinate pair was found, place the word
      if (!fail)
      {
         for (int i = 0; i < word.length(); i++)   //for each space
         {
            search[y + i][x] = word.charAt(i); //place a letter in search
            solution[y + i][x] = word.charAt(i); //place a letter in solution
         }
      }
   }//end of placeVertical method
   
   //places a word in the search diagonally
   //accepts a String, which is the word to be placed
   private void placeDiagonal(String word)
   {
      int x = 0;
      int y = 0;
      boolean fail = true;
      int tries = 0;
   
      //checks random coordinates until a valid pair is found
      //tries 100 times before giving up
      while (fail && tries <= 100)
      {
         //resets fail
         fail = false;
         
         //sets random coordinates
         int[] coords = randomCoords();
         x = coords[0];
         y = coords[1];
         
         //checks to make sure coordinates are in-bounds
         if (word.length() + x > dimensions && word.length() + y > dimensions)
         {
            fail = true;
         }
         
         //checks for other chars in this word's path
         for (int i = 0; i < word.length(); i++)   //for each space
         {
            try
            {
               //if the space is empty
               if (search[y + i][x + i] == 0)
               {}
               //or if the space is already the correct letter
               else if (search[y + i][x + i] == word.charAt(i))
               {}
               else  //otherwise
               {
                  fail = true;
               }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
               fail = true;
            }
         }
         tries++; //increase tries count
      }//end of while

   
      //if a valid coordinate pair was found, place the word
      if (!fail)
      {
         for (int i = 0; i < word.length(); i++)   //for each space
         {
            search[y + i][x + i] = word.charAt(i); //place a letter in search
            solution[y + i][x + i] = word.charAt(i); //place a letter in solution
         }
      }
   }//end of placeDiagonal method
   
   //fills every empty space in the search with a random letter
   //fills every empty space in the solution with a -
   private void fill()
   {
      //fills arrays with x's
      for (int i = 0; i < search.length; i++)
      {
         for (int j = 0; j < search[i].length; j++)
         {
            if (search[i][j] == 0)
            {
               //generate a random letter
               Random rng = new Random();
               int randNum = rng.nextInt(26) + 65;
               char randChar = (char) randNum;
               
               search[i][j] = randChar;   //place letter in search
               solution[i][j] = '-';      //place - in solution
            }
         }
      }
   }//end of fill method
   
   //generates a random coordinate pair that fit within the dimensions of the word search
   //returns an array of 2 ints, which are the coordinates
   private int[] randomCoords()
   {
      Random rng = new Random();
      int[] coords = {rng.nextInt(dimensions), rng.nextInt(dimensions)};
      return coords;
   }//end of randomCoords method
}//end of class